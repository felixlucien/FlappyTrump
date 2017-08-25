package felix.flappytrump.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

import java.util.ArrayList;

import felix.flappytrump.gameobjects.gameobjectframework.GameObject;

/**
 * Created by Felix McCuaig on 18/08/2017.
 */

public class Renderer extends Actor {

    //This list of objects are the ones that need to be rendered
    ArrayList<GameObject> gameObjects;

    public Renderer(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    //This actually renders all the gameobjects
    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(GameObject object: gameObjects) {
            object.render(batch);
        }
    }

    //This is called on the stage.act() method
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //The rest wont be used
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
