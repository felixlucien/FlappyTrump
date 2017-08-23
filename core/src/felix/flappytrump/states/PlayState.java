package felix.flappytrump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import felix.flappytrump.FlappyTrump2;
import felix.flappytrump.gameobjects.Wall;
import felix.flappytrump.resmanager.LoadAudio;
import felix.flappytrump.resmanager.LoadTexture;
import felix.flappytrump.sprites.Bird;
import java.util.Iterator;

public class PlayState extends State {
    private static final int GROUND_Y_OFFSET = -50;
    public static int SCORE = 0;
    private static final int TUBE_COUNT = 4;
    private static final int TUBE_SPACING = 125;
    public float backgroundX;
    Bird bird;
    public Array<Wall> walls;

    public PlayState(GameStateManager var1) {
        super(var1);
        FlappyTrump2.STATE = 2;
        SCORE = 0;
        LoadAudio.getRandomSound();
        this.cam.setToOrtho(false, 240.0F, 400.0F);
        this.bird = new Bird(50, 200);
        this.walls = new Array();

        for(int i = 1; i <= 4; ++i) {
            //this.walls.add(new Wall((float)(i * 177 + 200)));
        }

        if(!LoadAudio.CURRENT_SOUND.isPlaying()) {
            LoadAudio.getRandomSound().play();
            LoadAudio.CURRENT_SOUND.setLooping(false);
        }

        if(!LoadAudio.PS_BG_MUSIC.isPlaying()) {

        }

    }

    public void dispose() {
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            this.bird.jump();
        }

    }

    public void render(SpriteBatch var1) {
        var1.setProjectionMatrix(this.cam.combined);
        var1.begin();
        var1.draw(LoadTexture.BACKGROUND, this.cam.position.x - this.cam.viewportWidth / 2.0F, 0.0F, 240.0F, 400.0F);
        var1.draw(LoadTexture.NORMAL_TRUMP, this.bird.getPosition().x, this.bird.getPosition().y, 50.0F, 50.0F);
        Iterator var2 = this.walls.iterator();

        while(var2.hasNext()) {
            Wall var3 = (Wall)var2.next();
            var1.draw(LoadTexture.TOP_WALL, var3.getBoundsTop().x, var3.getBoundsTop().y);
            var1.draw(LoadTexture.BOTTOM_WALL, var3.getBoundsBot().x, var3.getBoundsBot().y);
        }

        var1.end();
    }

    @Override
    public void update(float var1) {

    }
    /*
    public void update(float var1) {
        this.handleInput();
        this.bird.update(var1);
        this.cam.position.x = this.bird.getPosition().x + 80.0F;

        for(int var2 = 0; var2 < this.walls.size; ++var2) {
            Wall wall = (Wall)this.walls.get(var2);
            if(this.cam.position.x - this.cam.viewportWidth / 2.0F > var3.getPosTopTube().x + (float)LoadTexture.TOP_WALL.getWidth()) {
                wall.reposition(wall.getBoundsTop().x + 708.0F);
            }

            if(wall.collides(this.bird.getBounds())) {
                LoadAudio.CURRENT_SOUND.dispose();
                this.gsm.set(new DeadState(this.gsm, this));
                if(FlappyTrump2.TOP_SCORE < SCORE) {
                    FlappyTrump2.TOP_SCORE = SCORE;
                }
            }

            if(wall.getPosBotTube().x < this.bird.getBounds().getX() + 25.0F && var3.boundsTop.getX() > this.bird.getBounds().getX() + 23.0F) {
                ++SCORE;
                if(!LoadAudio.CURRENT_SOUND.isPlaying()) {
                    LoadAudio.getRandomSound().play();
                    LoadAudio.CURRENT_SOUND.setLooping(false);
                }
            }
        }

        this.backgroundX = this.cam.position.x - this.cam.viewportWidth / 2.0F;
        this.cam.update();
    }
    */
}
