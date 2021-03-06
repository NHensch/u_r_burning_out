package de.floatec.u_r_burning_out;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {

    public static final float SIZE = 1f;

    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Block(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
