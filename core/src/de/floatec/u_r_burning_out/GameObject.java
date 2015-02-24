package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    public static final int SPEED = 80;
    public static final int GRAVITY = 10;
    protected Vector2 position;
    protected Vector2 direction = new Vector2(0,0);
    protected Texture texture;
    protected GameScreen screen;
    protected float scale=1;


    public GameObject(GameScreen screen, String textur, Vector2 position, float scale){
        this.screen = screen;
        this.texture = new Texture(Gdx.files.internal(textur));
        this.position = position;
        this.scale = scale;
    }

    public boolean isColliding(float x, float y, float w, float h) {
        return x <= position.x +texture.getWidth() * scale
                && x + w >= position.x
                && y <= position.y + texture.getHeight() * scale
                && y + h >= position.y;
    }

    public float getWidth() {
        return this.texture.getWidth();
    }

    public float getHeight() {
        return this.texture.getHeight();
    }

    public void draw(SpriteBatch batch) {

        batch.draw(texture,position.x, position.y,texture.getWidth()*scale,texture.getHeight()*scale);

    }
    public void update(float dt) {

        Vector2 move = direction.nor().scl(SPEED *dt);
        position = position.add(move);
    }

}
