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

public abstract class ComplexGameObject extends GameObject {

    //This is used for the position of the gameobject
    Rectangle bounds;
    //This facillitates more than one collision box for the game object, and polygon means they can be complex
    List<Polygon> collisionBoxes;
    //The gameobject texture
    Texture texture;

    public ComplexGameObject(String tag, State parent) {
        super(tag, parent);
    }

    //This renderes the gameobject
    @Override
    public void render(Batch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    //No memory leaks
    @Override
    public void dispose() {
        texture.dispose();
    }
}
