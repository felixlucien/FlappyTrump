package felix.flappytrump.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public abstract class GameObject {
    public static int TYPE_WALL = 0, TYPE_PLAYER = 1;

    int x, y, width, height, type;

    boolean isCollidable;

    PlayState parentState;

    public GameObject(int x, int y, int width, int height, int type, boolean isCollidable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.isCollidable = isCollidable;
        create();
    }

    public abstract void create();

    public abstract void render(Batch sb);

    public abstract void update();

    public abstract void destroy();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType() {
        return type;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setParentState(PlayState parentState) {
        this.parentState = parentState;
    }
}
