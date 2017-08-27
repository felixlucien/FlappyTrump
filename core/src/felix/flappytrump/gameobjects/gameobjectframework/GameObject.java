package felix.flappytrump.gameobjects.gameobjectframework;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public abstract class GameObject implements GameObjectFramework {

    //gameobject types
    public static final int TYPE_SIMPLE_GAME_OBJECT = 0, TYPE_COMPLEX_GAME_OBJECT = 1, TYPE_WALL = 2, UNDEFINED = -1;

    //The unique id tag for the object
    public String tag;
    //How we know if its a simple of complex object
    public int type;
    //This is the parent gamestate and allows use of the findObjectByID() method
    public State parent;
    //is the gameObject collidable
    private boolean isCollidable;

    public GameObject(String tag, int type, State parent, boolean isCollidable) {
        this.tag = tag;
        this.type = type;
        this.parent = parent;
        this.isCollidable = isCollidable;
    }

    public boolean isCollidable() {
        return isCollidable;
    }
}
