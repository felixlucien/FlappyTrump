package felix.flappytrump.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 15/08/2017.
 */

public class Player extends felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject {

    private int gravity = -1;
    public int velocity = 0;

    public Player(State parent, String tag, Rectangle bounds, Texture texture) {
        super(tag, parent, bounds, texture);
    }

    @Override
    public void create() {

    }


    @Override
    public void update() {
        if(velocity > -10) {
            velocity--;
        }

        if(bounds.y < 0) {
            velocity = 0;
            bounds.y = 0;
        }

        bounds.y = bounds.y + velocity;
    }

    @Override
    public void processInput() {
        velocity = 10;
    }

    @Override
    public void dispose() {

    }
}