package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 15/08/2017.
 */

public class Player extends SimpleGameObject {

    //Player y velocity
    public float velocity = 0;

    private float gravity = -1400;

    public Player(State parent, String tag, Rectangle bounds, Texture texture) {
        super(tag, parent, bounds, texture);
    }

    @Override
    public void create() {

    }


    @Override
    public void update() {
        //Adds velocity to gravity
        velocity += gravity * Gdx.graphics.getDeltaTime();


        //Makes sure bird dosent go through the ground
        if(bounds.y < 0) {
            velocity = 0;
            bounds.y = 0;
        }

        //adds velocity to position
        bounds.y += velocity * Gdx.graphics.getDeltaTime();
    }

    //called onClick and adds velocity to player
    @Override
    public void processInput() {
        velocity = 400;
    }
}