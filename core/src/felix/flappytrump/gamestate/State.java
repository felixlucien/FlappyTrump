package felix.flappytrump.gamestate;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public abstract class State {

    public State() {
        create();
    }

    public abstract void create();

    public abstract void render(Batch sb);

    public abstract void update();

    public abstract void destroy();
}
