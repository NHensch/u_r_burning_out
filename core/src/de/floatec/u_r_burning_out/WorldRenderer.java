package de.floatec.u_r_burning_out;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Animation;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WorldRenderer {

    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private static final float RUNNING_FRAME_DURATION = 0.0833f;
    private static final int FRAME_COLS = 7;
    private static final int FRAME_ROWS = 5;

    private World world;
    //private OrthographicCamera cam;

    /** Textures **/
    private Texture blockTexture;
    private Texture run_left;
    private Texture run_right;
    private Texture idle_left;
    private Texture idle_right;

    private TextureRegion[] runLeftFrames;
    private TextureRegion[] runRightFrames;
    private TextureRegion[] idleLeftFrames;
    private TextureRegion[] idleRightFrames;

    /** Animations **/
    private Animation runLeftAnimation;
    private Animation runRightAnimation;
    private Animation idleLeftAnimation;
    private Animation idleRightAnimation;

    private TextureRegion playerFrame;


    private SpriteBatch spriteBatch;
    private int width;
    private int height;
    private float ppuX;	// pixels per unit on the X axis
    private float ppuY;	// pixels per unit on the Y axis
    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    public WorldRenderer(World world) {
        this.world = world;
        //this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        //this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        //this.cam.update();
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    private void loadTextures() {

        System.out.println("Loading textures");
        blockTexture = new Texture(Gdx.files.internal("block.png"));
        run_left = new Texture("player/run_left.png");
        run_right = new Texture("player/run_right.png");
        idle_left = new Texture("player/idle_left.png");
        idle_right = new Texture("player/idle_right.png");

        runLeftFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        idleLeftFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        runRightFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        idleRightFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        TextureRegion[][] r_left = TextureRegion.split(run_left, run_left.getWidth()/FRAME_COLS, run_left.getHeight()/FRAME_ROWS);
        TextureRegion[][] i_left = TextureRegion.split(idle_left, idle_left.getWidth()/FRAME_COLS, idle_left.getHeight()/FRAME_ROWS);
        TextureRegion[][] r_right = TextureRegion.split(run_right, run_right.getWidth()/FRAME_COLS, run_right.getHeight()/FRAME_ROWS);
        TextureRegion[][] i_right = TextureRegion.split(idle_right, idle_right.getWidth()/FRAME_COLS, idle_right.getHeight()/FRAME_ROWS);

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                runLeftFrames[index] = r_left[i][j];
                idleLeftFrames[index] = i_left[i][j];
                runRightFrames[index] = r_right[i][j];
                idleRightFrames[index] = i_right[i][j];
                index++;
            }
        }
        runLeftAnimation = new Animation(RUNNING_FRAME_DURATION, runLeftFrames);
        idleLeftAnimation = new Animation(RUNNING_FRAME_DURATION, idleLeftFrames);
        runRightAnimation = new Animation(RUNNING_FRAME_DURATION, runRightFrames);
        idleRightAnimation = new Animation(RUNNING_FRAME_DURATION, idleRightFrames);

    }

    public void render() {
        spriteBatch.begin();
        drawBlocks();
        drawPlayer();
        spriteBatch.end();
    }

    private void drawBlocks() {

        for (Block block : world.getBlocks()) {
            spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
        }
    }

    private void drawPlayer() {
        Player player = world.getPlayer();

        //System.out.println("Players position: " + player.getPosition());
        if(player.getState().equals(Player.State.IDLE)) {
            playerFrame = player.isFacingLeft() ? idleLeftAnimation.getKeyFrame(player.getStateTime(), true) : idleRightAnimation.getKeyFrame(player.getStateTime(), true);
        }
        if(player.getState().equals(Player.State.MOVING)) {
            playerFrame = player.isFacingLeft() ? runLeftAnimation.getKeyFrame(player.getStateTime(), true) : runRightAnimation.getKeyFrame(player.getStateTime(), true);
        }
        spriteBatch.draw(playerFrame, player.getPosition().x * ppuX, player.getPosition().y * ppuY, Player.SIZE * ppuX, Player.SIZE * ppuY);

    }
}
