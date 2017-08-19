package felix.flappytrump.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;

import felix.flappytrump.gameobjects.GameObject;
import felix.flappytrump.gamestate.GameStateManager;

/**
 * Created by Felix McCuaig on 18/08/2017.
 */

public class Renderer extends Actor {

    ArrayList<GameObject> gameObjects;

    public Renderer(ArrayList<GameObject> gameObjects) {
        super();
        this.gameObjects = gameObjects;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(GameObject object: gameObjects) {
            object.render(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public boolean fire(Event event) {
        return super.fire(event);
    }

    @Override
    public boolean notify(Event event, boolean capture) {
        return super.notify(event, capture);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }
}
