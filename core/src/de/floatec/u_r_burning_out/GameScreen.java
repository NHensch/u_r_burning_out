package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen, InputProcessor {

    private World world;
    private WorldRenderer renderer;
    private PlayerControls controls;
    private int height;
    private int width;


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controls.update(delta);
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void show() {
        world = new World();
        renderer = new WorldRenderer(world);
        controls = new PlayerControls(world);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT)
            controls.leftPressed();
        if (keycode == Input.Keys.RIGHT)
            controls.rightPressed();
        if (keycode == Input.Keys.UP)
            controls.jumpPressed();
        return true;

    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT)
            controls.leftReleased();
        if (keycode == Input.Keys.RIGHT)
            controls.rightReleased();
        if (keycode == Input.Keys.UP)
            controls.jumpReleased();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
