package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class Wall extends GameObject{

    Texture texture;
    boolean isScore = false;

    public Wall(Texture texture, int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height, TYPE_PLAYER, collidable);
        this.texture = texture;
    }

    @Override
    public void create() {

    }


    @Override
    public void render(Batch sb) {
        sb.draw(texture, x, y, width, height);
    }

    @Override
    public void update() {
        if(x < (Gdx.graphics.getWidth() / 2) && !isScore) {
            isScore = true;
            parentState.updateScore();
        }

        if(x < 0) {
            x = Gdx.graphics.getWidth();
            isScore = false;
        } else {
            x--;
        }
    }

    @Override
    public void destroy() {
        texture.dispose();
    }
}
