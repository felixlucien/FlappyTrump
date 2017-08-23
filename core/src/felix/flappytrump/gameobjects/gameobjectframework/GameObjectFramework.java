package felix.flappytrump.gameobjects.gameobjectframework;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public interface GameObjectFramework {

    void create();

    void update();

    void processInput();

    void render(Batch batch);

    void dispose();
}
