package felix.flappytrump.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 24/08/2017.
 */

public class Background extends felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject {


    //This class is used to render the background of the scene

    public Background(String tag, State parent, Rectangle bounds, Texture texture) {
        super(tag, parent, bounds, texture);
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void processInput() {

    }
}
