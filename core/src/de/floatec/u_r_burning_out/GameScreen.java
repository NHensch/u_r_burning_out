package de.floatec.u_r_burning_out;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private Game parent;
    private SpriteBatch batch;
	private BitmapFont font;

    private Player player;

	private float w;
	private float h;

	private Random randGenerator = new java.util.Random(System.currentTimeMillis());

	
	public float getH() {
		return h;
	}

	public float getW() {
		return w;
	}

	public GameScreen(Game parent) {
        this.parent=parent;
		font = new BitmapFont();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

        player = new Player(this);

	}

	@Override
	public void render(float dt) {
		update(dt);
		draw();
	}

	private void update(float dt) {

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.direction.x=(1);
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.direction.x=(-1);
		} else {
            player.direction.x=(0);
		}

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.direction.y= (1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.direction.y=(-1);
        } else {
            player.direction.y=(0);
        }

        player.update(dt);

	}

	private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        player.draw(batch);


		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
		batch.dispose();
        this.dispose();
	}

}
