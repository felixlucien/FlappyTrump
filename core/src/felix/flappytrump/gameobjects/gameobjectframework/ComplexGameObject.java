package felix.flappytrump.gameobjects.gameobjectframework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public class ComplexGameObject extends GameObject {

    Rectangle bounds;
    List<Polygon> collisionBoxes;
    Texture texture;

    public ComplexGameObject(String tag, State parent) {
        super(tag, parent);
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

    @Override
    public void render(Batch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void dispose() {

    }
}
