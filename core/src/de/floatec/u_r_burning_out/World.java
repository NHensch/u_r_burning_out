package de.floatec.u_r_burning_out;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class World {

    private int width;
    private int height;
    /** The blocks making up the world **/
    private Array <Block> blocks = new Array<Block>();

    /** Player controlled hero **/
    private Player player;

    /** The collision boxes **/
    private Array<Rectangle> collisionRects = new Array<Rectangle>();

    // Getters
    public Array<Block> getBlocks() {

        return blocks;
    }

    public Player getPlayer() {
        return player;
    }
    public Array<Rectangle> getCollisionRects() {
        return collisionRects;
    }

    public World() {
        createDemoWorld();
        System.out.println("Demoworld created");
    }

    private void createDemoWorld() {
        player = new Player(new Vector2(7, 5));

        System.out.println("Player created");
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(new Vector2(i, 0)));
            blocks.add(new Block(new Vector2(i, 7)));
            if (i > 2) {
                blocks.add(new Block(new Vector2(i, 1)));
            }
        }
        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(9, 3)));
        blocks.add(new Block(new Vector2(9, 4)));
        blocks.add(new Block(new Vector2(9, 5)));

        blocks.add(new Block(new Vector2(4, 3)));
        blocks.add(new Block(new Vector2(5, 3)));
        blocks.add(new Block(new Vector2(6, 3)));
    }
}