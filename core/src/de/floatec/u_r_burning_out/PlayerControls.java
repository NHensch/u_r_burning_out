package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.HashMap;
import java.util.Map;
public class PlayerControls {

    enum Keys {
        LEFT, RIGHT, JUMP
    }

    private static final long LONG_JUMP_PRESS 	= 150l;
    private static final float ACCELERATION 	= 20f;
    private static final float GRAVITY 			= -5f;
    private static final float MAX_JUMP_SPEED	= 7f;
    private static final float DAMP 			= 0.90f;
    private static final float MAX_VEL 			= 4f;

    private World 	world;
    private Player 	player;
    private long	jumpPressedTime;
    private boolean jumpingPressed;
    private boolean grounded = false;

    // This is the rectangle pool used in collision detection
    // Good to avoid instantiation each frame
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    // Blocks that player can collide with any given frame
    private Array<Block> collidable = new Array<Block>();


    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
    };

    public PlayerControls(World world) {
        this.world = world;
        this.player = world.getPlayer();
        this.collidable = world.getBlocks();
    }

    // ** Key presses //

    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
        jumpingPressed = false;
    }

    /** The main update method **/
    public void update(float delta) {
        // Processing the input - setting the states of player
        processInput();

        // If Player is grounded then reset the state to IDLE
        if (grounded && player.getState().equals(Player.State.JUMPING)) {
            player.setState(Player.State.IDLE);
        }

        // Setting initial vertical acceleration
        player.getAcceleration().y = GRAVITY;

        // Convert acceleration to frame time
        player.getAcceleration().scl(delta);

        // apply acceleration to change velocity
        player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y);

        // checking collisions with the surrounding blocks depending on player's velocity
        checkCollisionWithBlocks(delta);

        // apply damping to halt player nicely
        player.getVelocity().x *= DAMP;

        // ensure terminal velocity is not exceeded
        if (player.getVelocity().x > MAX_VEL) {
            player.getVelocity().x = MAX_VEL;
        }
        if (player.getVelocity().x < -MAX_VEL) {
            player.getVelocity().x = -MAX_VEL;
        }

        // simply updates the state time
        player.update(delta);
    }

    /** Collision checking **/
    private void checkCollisionWithBlocks(float delta) {
        //System.out.println("Checking collision " + player.getAcceleration());
        // scale velocity to frame units
        player.getVelocity().scl(delta);

        // Obtain the rectangle from the pool instead of instantiating it
        Rectangle playerRect = rectPool.obtain();
        // set the rectangle to player's bounding box
        playerRect.set(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);

        // first check the movement on the horizontal X ax

        // simulate player's movement on the X
        playerRect.x += player.getVelocity().x;

        // if player collides, make his horizontal velocity 0
        for (Block block : collidable) {
            if (block == null) continue;
            if (false) {
                System.out.println("is overlapping");
                player.getVelocity().x = 0;
                world.getCollisionRects().add(block.getBounds());
                break;
            }
        }

        // reset the x position of the collision box
        playerRect.x = player.getPosition().x;

        // the same thing but on the vertical Y axis
        playerRect.y += player.getVelocity().y;

        for (Block block : collidable) {
            if (block == null) continue;
            if (block.getPosition().x >= playerRect.x && block.getPosition().x + block.getBounds().width
                    <= playerRect.x + playerRect.width
                    && playerRect.y < block.getBounds().y + block.getBounds().height) {
                if (player.getVelocity().y < 0) {
                    System.out.println("Player has grounded");
                    grounded = true;
                }
                player.getVelocity().y = 0;


            }
        }
        // reset the collision box's position on Y
        playerRect.y = player.getPosition().y;

        // update player's position
        player.getPosition().add(player.getVelocity());
        player.getBounds().x = player.getPosition().x;
        player.getBounds().y = player.getPosition().y;

        // un-scale velocity (not in frame time)
        player.getVelocity().scl(1 / delta);

    }

    /** Change Player's state and parameters based on input controls **/
    private void processInput() {
        if (keys.get(Keys.LEFT)) {
            // left is pressed
            player.setFacingLeft(true);
            player.setState(Player.State.MOVING);
            player.getVelocity().x = -Player.SPEED;
        }
        if (keys.get(Keys.RIGHT)) {
            // left is pressed
            player.setFacingLeft(false);
            player.setState(Player.State.MOVING);
            player.getVelocity().x = Player.SPEED;
        }
        // need to check if both or none direction are pressed, then Player is idle
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
            player.setState(Player.State.IDLE);
            // acceleration is 0 on the x
            player.getAcceleration().x = 0;
            // horizontal speed is 0
            player.getVelocity().x = 0;
        }
    }
}
