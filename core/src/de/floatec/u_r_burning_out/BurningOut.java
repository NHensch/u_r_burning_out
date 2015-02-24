package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Game;

public class BurningOut extends Game {
	
	@Override
	public void create() {		
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
	
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
