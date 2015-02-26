package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Game;

public class URBurningOut extends Game {

    public GameScreen game;
    public MenuScreen menu;

    @Override
    public void create() {
        game = new GameScreen();
        menu = new MenuScreen(this);
        setScreen(menu);
    }
   public void switchToMenu(){
        setScreen(menu);
    }
    public void switchToGame() {
        setScreen(game);
    }
}

