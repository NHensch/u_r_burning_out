package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by johannesheiler on 25.02.15.
 */

public class MenuScreen implements Screen {

        private URBurningOut parent;
        protected Texture texture = new Texture(Gdx.files.internal("block.png"));
        private SpriteBatch spriteBatch = new SpriteBatch();
        private float scale = 1f;

    public MenuScreen(URBurningOut parent){
        this.parent = parent;
        }

    //WICHTIG!!!!
    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                this.parent.setScreen(parent.game);
        }
    }

    public float getWidth() {
        return this.texture.getWidth();
    }

    public float getHeight() {
        return this.texture.getHeight();
    }


    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        this.spriteBatch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
    }

        @Override
        public void render (float delta){
            update(delta);
            draw();
        }



        @Override
        public void resize(int width, int height) {
        }

        @Override
        public void show() {
        }

        @Override
        public void hide() {
            dispose();
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void dispose() {
        }
    }
