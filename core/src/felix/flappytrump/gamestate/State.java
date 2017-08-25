package felix.flappytrump.gamestate;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public abstract class State {

    /*
        This class is the framework of the gamestates and implements the methods create, render,
        update and destroy. This means that no matter what gamestate is the current state, these methods
        these methods can be called. All of the gamestate classes extend this class
     */

    public State() {
        create();
    }

    public abstract void create();

    public abstract void render(Batch sb);

    public abstract void update();

    public abstract void destroy();
}
