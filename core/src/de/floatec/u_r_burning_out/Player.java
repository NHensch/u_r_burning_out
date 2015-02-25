package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Player {

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public enum State {
        IDLE, MOVING, JUMPING
    }

    public static final float SPEED = 2f;
    public static final float J_SPEED = 1f;
    public static final float SIZE = 0.5f;

    Vector2 position = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 velocity = new Vector2();
    Rectangle bounds = new Rectangle();
    State state = State.IDLE;
    boolean facingLeft = true;


    public Player(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }

    /*
    public void jump() {
        if (canJump && force <= 0) {
            force = 2;
            canJump = false;
        }
    }

    @Override
    public void update(float dt) {
        force = force - GRAVITY * dt;

        System.out.println(force);
        direction.y = force;
        if (position.y < 50 && force < 0) {
            direction.y = 0;
            force = 0;
        }

        Vector2 move = direction.scl(SPEED * dt);
        position = position.add(move);
    }
    */
}
