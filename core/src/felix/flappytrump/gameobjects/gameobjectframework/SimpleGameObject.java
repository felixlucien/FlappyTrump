package felix.flappytrump.gameobjects.gameobjectframework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public abstract class SimpleGameObject extends felix.flappytrump.gameobjects.gameobjectframework.GameObject {

    public Rectangle bounds;
    public Texture texture;

    public SimpleGameObject(String tag, State parent, Rectangle bounds, Texture texture) {
        super(tag, parent);
        this.bounds = bounds;
        this.texture = texture;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.getWidth(), bounds.getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
