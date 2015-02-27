package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;
public class PlayerControls {

    enum Keys {
        LEFT, RIGHT, JUMP
    }

    private static final float ACCELERATION 	= 10f;
    private static final float GRAVITY 			= -10f;
    private static final float MAX_JUMP_SPEED	= 7f;
    private static final float MAX_VEL 			= 3f;

    private World 	world;
    private Player 	player;
    private boolean grounded = false;
    private Rectangle pos;

    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
    };

    public PlayerControls(World world) {
        this.world = world;
        this.player = world.getPlayer();
        pos = new Rectangle();
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
    }

    public void update(float delta) {
        processInput();
        // ensure terminal velocity is not exceeded
        /*
        if (player.getVelocity().x > MAX_VEL) {
            player.getVelocity().x = MAX_VEL;
        }
        if (player.getVelocity().x < -MAX_VEL) {
            player.getVelocity().x = -MAX_VEL;
        }*/
        player.getAcceleration().scl(delta);
        //player.getAcceleration().y = GRAVITY;
        player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y + GRAVITY * delta);
        player.getVelocity().scl(delta);
        // update player's position
        checkCollisions(delta);
        player.getPosition().add(player.getVelocity());
        player.getBounds().x = player.getPosition().x;
        player.getBounds().y = player.getPosition().y;
        // un-scale velocity (not in frame time)
        player.getVelocity().scl(1 / delta);
        player.update(delta);
    }

    private void checkCollisions(float delta) {
        pos.x = player.getPosition().x;
        pos.y = player.getPosition().y;
        pos.y += player.getVelocity().y;
        if(pos.y <= 0f) {
            player.getVelocity().y = 0f;
        }

        //if(player.getVelocity().y < 0)
        //world.tiledMap.getLayers().g
    }
    /** Change Player's state and parameters based on input controls **/
    private void processInput() {
        // System.out.println(System.currentTimeMillis()+ " State: "+ player.getState() );
        if (keys.get(Keys.JUMP)) {
            if (!player.getState().equals(Player.State.JUMPING)) {
                player.setState(Player.State.JUMPING);
                player.getVelocity().y = MAX_JUMP_SPEED;
                grounded = false;
            }
        }
        if (keys.get(Keys.LEFT)) {
            player.setFacingLeft(true);
            player.setState(Player.State.MOVING);
            player.getVelocity().x = -ACCELERATION;
        }
        if (keys.get(Keys.RIGHT)) {
            player.setFacingLeft(false);
            player.setState(Player.State.MOVING);
            player.getVelocity().x = ACCELERATION;
        }
        // need to check if both or none direction are pressed, then Player is idle
        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
                (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
            player.setState(Player.State.IDLE);
            player.getAcceleration().x = 0;
            player.getVelocity().x = 0;
        }
    }
}
