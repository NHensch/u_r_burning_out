package de.floatec.u_r_burning_out;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by johannesheiler on 25.02.15.
 */

public class MenuScreen implements Screen {

        private URBurningOut parent;

    private Music music;
    private Sound fire;
    private Sound musicGame;
    private Sound lighting;
    private Sound crack;
    private Sound footsteps;
    private Music blueslick;
    private Music jazzlick;
    private Music.OnCompletionListener endListener = new Music.OnCompletionListener() {
       @Override
       public void onCompletion(Music music) {
           Gdx.app.exit();
       }
   };

    protected Texture texture = new Texture(Gdx.files.internal("menuScreen/menue_background_neu.png"));
    private SpriteBatch spriteBatch = new SpriteBatch();
    private Button startbutton = new Button(new Vector2(4,4),"menuScreen/button1.png","menuScreen/button1_ausgewaehlt.png","menuScreen/button1_push.png" );
    private Button exitbutton = new Button(new Vector2(4,4),"menuScreen/button4.png","menuScreen/button4_ausgewaelt.png","menuScreen/button4_push.png");

    private float waitTime = 100f;

    private boolean gameIsExiting= false;



    public MenuScreen(URBurningOut parent){
        this.parent = parent;
        startbutton.setSelected(true);

        music = Gdx.audio.newMusic(Gdx.files.internal("menuScreenMusic/MenuGuitar.wav"));
        blueslick = Gdx.audio.newMusic(Gdx.files.internal("menuScreenMusic/blueslick.mp3"));
        jazzlick = Gdx.audio.newMusic(Gdx.files.internal("menuScreenMusic/jazzlick.mp3"));


        fire = Gdx.audio.newSound(Gdx.files.internal("menuScreenMusic/Fireplace.mp3"));
        musicGame = Gdx.audio.newSound(Gdx.files.internal("menuScreenMusic/GameGuitar.mp3"));
        lighting = Gdx.audio.newSound(Gdx.files.internal("menuScreenMusic/match.wav"));
        crack = Gdx.audio.newSound(Gdx.files.internal("menuScreenMusic/crack.wav"));
        footsteps = Gdx.audio.newSound(Gdx.files.internal("menuScreenMusic/footsteps.mp3"));

        blueslick.play();
        fire.loop();
        footsteps.loop();



    }


    //Aktionen
    public void update(float delta) {
        waitTime -= 10f; //verzögerung tastendruck

        //MenuScreen music is played after opening GuitarLick
        if(!blueslick.isPlaying()&&!music.isPlaying()) {
            music.play();
        }

        //Auswahl unter Buttons
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (waitTime < 0f) {
                startbutton.setSelected();
                exitbutton.setSelected();
                crack.play();
                waitTime = 100f;
            }
        }
        //Escape=Exit
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            exitbutton.setPressed(true);

        }

        //Startbutton
        if (startbutton.isSelected()) {
            startbutton.setIndex(1);
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                startbutton.setPressed(true);
            }
        }
        if (startbutton.isPressed()) {
            startbutton.setIndex(2);
            crack.play();
            music.stop();
            lighting.play();
            musicGame.loop();
            this.parent.setScreen(parent.game);

        }
        if (!startbutton.isSelected()) {
            startbutton.setIndex(0);
        }

        //Exitbutton
        if (exitbutton.isSelected()) {
            exitbutton.setIndex(1);
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                exitbutton.setPressed(true);
            }
        }
        if (exitbutton.isPressed()) {
            if(!gameIsExiting) {
                exitGame();
            }
        }
        if (!exitbutton.isSelected()) {
            exitbutton.setIndex(0);
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
        this.spriteBatch.draw(startbutton.getTexture(), 237, 360, startbutton.getWidth(), startbutton.getHeight());
        this.spriteBatch.draw(exitbutton.getTexture(), 237, 200, exitbutton.getWidth(), exitbutton.getHeight());
        spriteBatch.end();
    }

    public void exitGame(){
        gameIsExiting = true;
        exitbutton.setIndex(2);
        music.stop();
        crack.play();
        jazzlick.play();

        jazzlick.setOnCompletionListener(endListener);
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
