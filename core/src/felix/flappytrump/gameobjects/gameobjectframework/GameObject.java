package felix.flappytrump.gameobjects.gameobjectframework;

import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public abstract class GameObject implements GameObjectFramework {

    public String tag;
    public State parent;

    public GameObject(String tag, State parent) {
        this.tag = tag;
        this.parent = parent;
    }
}
