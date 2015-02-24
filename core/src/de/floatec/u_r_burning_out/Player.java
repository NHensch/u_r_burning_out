package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{
    public Player(GameScreen screen) {
        super(screen, "player.png", new Vector2(100f,100f), 0.2f);

    }
}
