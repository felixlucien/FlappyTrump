package felix.flappytrump.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import felix.flappytrump.FlappyTrump2;
import felix.flappytrump.resmanager.LoadTexture;
import felix.flappytrump.sprites.Wall;
import felix.flappytrump.sprites.Bird;
import felix.flappytrump.states.GameStateManager;
import felix.flappytrump.states.PlayState;
import felix.flappytrump.states.State;
import java.util.Iterator;

public class DeadState extends State {
    PlayState ps;

    public DeadState(GameStateManager var1, PlayState var2) {
        super(var1);
        this.ps = var2;
        FlappyTrump2.STATE = 3;
    }

    public void dispose() {
    }

    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            this.gsm.set(new PlayState(this.gsm));
        }

    }

    public void render(SpriteBatch var1) {
        if(FlappyTrump2.STATE == 3) {
            var1.begin();
            var1.draw(LoadTexture.BACKGROUND, this.ps.backgroundX - 2.0F, this.cam.position.y, 242.0F, 400.0F);
            var1.draw(LoadTexture.NORMAL_TRUMP, this.ps.bird.getPosition().x, this.ps.bird.getPosition().y, 50.0F, 50.0F);
            Iterator var2 = this.ps.walls.iterator();

            while(var2.hasNext()) {
                Wall var3 = (Wall)var2.next();
                var1.draw(LoadTexture.TOP_WALL, var3.getPosTopTube().x, var3.getPosTopTube().y);
                var1.draw(LoadTexture.BOTTOM_WALL, var3.getPosBotTube().x, var3.getPosBotTube().y);
            }

            var1.end();
        }

    }

    public void update(float var1) {
        this.handleInput();
    }
}
