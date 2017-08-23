package felix.flappytrump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import felix.flappytrump.FlappyTrump2;
import felix.flappytrump.resmanager.LoadAudio;
import felix.flappytrump.resmanager.LoadTexture;
import felix.flappytrump.states.GameStateManager;
import felix.flappytrump.states.PlayState;
import felix.flappytrump.states.State;

public class MenuState extends State {
    public MenuState(GameStateManager var1) {
        super(var1);
        this.cam.setToOrtho(false, 240.0F, 400.0F);
        FlappyTrump2.STATE = 1;
        LoadAudio.playMusic();
    }

    public void dispose() {
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            this.gsm.set(new PlayState(this.gsm));
            LoadAudio.MUSIC.dispose();
        }

    }

    public void render(SpriteBatch var1) {
        var1.setProjectionMatrix(this.cam.combined);
        var1.begin();
        var1.draw(LoadTexture.BACKGROUND, 0.0F, 0.0F, 240.0F, 400.0F);
        var1.draw(LoadTexture.START_BUTTON, this.cam.position.x - (float)(LoadTexture.START_BUTTON.getWidth() / 2), this.cam.position.y / 2.0F);
        var1.end();
    }

    public void update(float var1) {
        this.handleInput();
    }
}
