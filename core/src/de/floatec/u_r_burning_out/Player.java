package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Player {

    public enum State {
        IDLE, MOVING, JUMPING
    }

    public static final float SIZE = 1f;

    private Vector2 position = new Vector2();
    private Vector2 acceleration = new Vector2();
    private Vector2 velocity = new Vector2();
    private Rectangle bounds = new Rectangle();
    private State state = State.IDLE;
    private boolean facingLeft = true;
    private float stateTime = 0f;

    public Player(Vector2 position) {
        this.position = position;
        this.bounds.x = position.x;
        this.bounds.y = position.y;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }

    public void setState(State newState) {
        this.state = newState;
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setFacingLeft(boolean facing) {
        this.facingLeft = facing;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public State getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.bounds.setX(position.x);
        this.bounds.setY(position.y);
    }


}
