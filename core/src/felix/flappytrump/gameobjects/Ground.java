package felix.flappytrump.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject;
import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 26/08/2017.
 */

public class Ground extends SimpleGameObject {

    private PlayState parent;

    public Ground(String tag, State parent, Rectangle bounds, Texture texture) {
        super(tag, parent, true, bounds, texture);
        this.parent = (PlayState) parent;

        this.parent = (PlayState) parent;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {
        if(bounds.x + bounds.getWidth() < 0) {
            Ground g = (Ground) parent.findObjectByTag("GROUND LAST");
            g.tag = "GROUND";
            bounds.x = g.bounds.x + g.bounds.getWidth();
            tag = "GROUND LAST";
        }
        bounds.x -= Wall.SPEED;
    }

    @Override
    public void updateWhenDead() {

    }

    @Override
    public void processInput() {

    }
}
