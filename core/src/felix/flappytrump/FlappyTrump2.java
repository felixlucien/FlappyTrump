package felix.flappytrump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import felix.flappytrump.gui.StageManager;
import felix.flappytrump.resmanager.LoadAudio;
import felix.flappytrump.resmanager.LoadTexture;
import felix.flappytrump.states.GameStateManager;
import felix.flappytrump.states.MenuState;

public class FlappyTrump2 extends ApplicationAdapter {
    public static final int HEIGHT = 800;
    public static int STATE;
    public static final String TITLE = "Flappy Trump";
    public static int TOP_SCORE;
    public static final int WIDTH = 480;
    SpriteBatch batch;
    double frames = 0.0D;
    GameStateManager gsm;
    StageManager sm;

    public FlappyTrump2() {
    }

    private void printFps() {
        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
        System.out.println("DT: " + Gdx.graphics.getDeltaTime());
    }

    public void create() {
        LoadAudio.loadRes();
        LoadTexture.loadTextures();
        LoadAudio.getRandomSound();
        this.batch = new SpriteBatch();
        this.gsm = new GameStateManager();
        this.gsm.push(new MenuState(this.gsm));
        Gdx.gl.glClearColor(1.0F, 0.0F, 0.0F, 1.0F);
        this.sm = new StageManager();
    }

    public void dispose() {
        super.dispose();
        this.batch.dispose();
    }

    public void render() {
        ++this.frames;
        if(this.frames * (double)Gdx.graphics.getDeltaTime() > 6.0D) {
            this.printFps();
            this.frames = 0.0D;
        }

        Gdx.gl.glClear(16384);
        this.gsm.update(Gdx.graphics.getDeltaTime());
        this.gsm.render(this.batch);
        this.sm.render();
    }
}
