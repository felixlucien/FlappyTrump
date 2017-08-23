package felix.flappytrump.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import felix.flappytrump.resmanager.LoadTexture;
import felix.flappytrump.states.PlayState;

import felix.flappytrump.FlappyTrump2;

public class StageManager {
    SpriteBatch batch = new SpriteBatch();

    public StageManager() {
    }

    public void render() {
        this.batch.begin();
        if(FlappyTrump2.STATE == 2) {
            LoadTexture.FLAPPY_FONT_LARGE.draw(this.batch, PlayState.SCORE + "", 290.0F, 1200.0F);
        } else if(FlappyTrump2.STATE == 3) {
            LoadTexture.FLAPPY_FONT_MEDIUM.draw(this.batch, "your score " + PlayState.SCORE + "", 120.0F, 480.0F);
            LoadTexture.FLAPPY_FONT_MEDIUM.draw(this.batch, "your top score " + FlappyTrump2.TOP_SCORE + "", 18.0F, 320.0F);
            LoadTexture.FLAPPY_FONT_MEDIUM.draw(this.batch, "click to \nplay again", 160.0F, 160.0F);
        }

        this.batch.end();
    }
}
