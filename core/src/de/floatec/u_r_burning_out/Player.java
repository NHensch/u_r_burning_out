package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{


    private boolean canJump = true;
    float force =0 ;

    public Player(GameScreen screen) {
        super(screen, "player.png", new Vector2(100f,100f), 0.1f);

    }

    public void switchState() {
        if(canJump)
            canJump = false;
        else
            canJump = true;
    }

    public boolean canJump() {
        return canJump;
    }

    public void jump(){
        if (canJump && force<=0){
           force =2;
            canJump = false;
        }
    }

    @Override
    public void update(float dt) {
        force=force - GRAVITY *dt;
        System.out.println(force);
        direction.y=force;
        if(position.y < 50 && force < 0){
            direction.y=0;
            force=0;
        }
        Vector2 move = direction.scl(SPEED *dt);
        position = position.add(move);
    }
}
