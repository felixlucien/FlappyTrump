package felix.flappytrump.gameobjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gamestate.StartState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 27/08/2017.
 */

public class PlayerStart extends GameObject {

    StartState parent;

    //Player y velocity
    public float velocity = 0;

    public Texture texture;
    private TextureRegion textureRegion;

    //Position and size of the simple gameobject
    public Rectangle bounds;
    private float rotation = 0;


    public PlayerStart(State parent, String tag, Rectangle bounds, Texture texture) {
        super(tag, GameObject.UNDEFINED, parent, false);
        textureRegion = new TextureRegion(texture);
        this.parent = (StartState) parent;
        this.bounds = bounds;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {
        oscillateBird();

        rotation = velocity * Gdx.graphics.getDeltaTime() * 2;

        //adds velocity to position
        bounds.y += velocity * Gdx.graphics.getDeltaTime();
    }

    //used for when trump goes up and down at the start
    private boolean isUp = false;
    //move up and down
    private void oscillateBird() {
        if(isUp) {
            if(velocity < 30) {
                velocity+=2;
            } else {
                isUp = false;
            }
        } else if(!isUp){
            if(velocity > -30) {
                velocity-=2;
            } else {
                isUp = true;
            }
        }
    }

    @Override
    public void updateWhenDead() {

    }

    @Override
    public void processInput() {

    }

    @Override
    public void render(Batch batch) {
        batch.draw(textureRegion,  bounds.x, bounds.y, bounds.width / 2, bounds.height / 2, bounds.width, bounds.height, 1, 1, rotation);
    }

    @Override
    public void dispose() {

    }
}
