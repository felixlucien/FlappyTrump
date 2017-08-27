package felix.flappytrump.gameobjects.gameobjectframework;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public interface GameObjectFramework {

    //These methods are called by the parent gamestate
    void create();

    //Called onUpdate
    void update();

    void updateWhenDead();

    //Called when an input event happens
    void processInput();

    //Called onRender
    void render(Batch batch);

    //Called onDispose
    void dispose();
}
