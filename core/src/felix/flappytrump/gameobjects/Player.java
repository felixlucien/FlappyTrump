package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import felix.flappytrump.actors.*;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject;
import felix.flappytrump.gamestate.*;

/**
 * Created by Felix McCuaig on 15/08/2017.
 */

public class Player extends GameObject {

    //Player y velocity
    public float velocity = 0;

    private float gravity = -1400;

    private PlayState parent;

    //Position and size of the simple gameobject
    public Rectangle bounds;
    //The objects texture
    public Texture texture;
    private TextureRegion textureRegion;

    public Circle hitbox;

    private float rotation;

    public Player(State parent, String tag, Rectangle bounds, Texture texture) {
        super(tag, GameObject.UNDEFINED, parent, false);
        this.parent = (PlayState) parent;
        this.texture = texture;
        this.bounds = bounds;

        textureRegion = new TextureRegion(texture);
        hitbox = new Circle(bounds.x + bounds.getWidth() / 2, bounds.y + bounds.getHeight() / 2, bounds.getHeight() / 3);
    }

    @Override
    public void create() {
    }


    @Override
    public void update() {
        hitbox.setPosition(bounds.x + bounds.getWidth() / 2, bounds.y + bounds.getHeight() / 2);

        //sprite.setPosition(bounds.getX(), bounds.getY());


        if(parent.isPlaying()) {
            //Adds velocity to gravity
            velocity += gravity * Gdx.graphics.getDeltaTime();
        } else {
            oscillateBird();
        }

        rotation = velocity * Gdx.graphics.getDeltaTime() * 2;

        //adds velocity to position
        bounds.y += velocity * Gdx.graphics.getDeltaTime();

    }

    @Override
    public void updateWhenDead() {


        //add gravity
        velocity += gravity * Gdx.graphics.getDeltaTime();

        //set rotation of texture
        rotation = velocity * Gdx.graphics.getDeltaTime() * 2;

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


    //called onClick and adds velocity to player
    @Override
    public void processInput() {
        if (bounds.y < parent.stage.getViewport().getWorldHeight() - bounds.getHeight() && !parent.isDead) {
            velocity = 400;
        }
    }

    @Override
    public void render(Batch batch) {
        batch.draw(textureRegion,  bounds.x, bounds.y, bounds.width / 2, bounds.height / 2, bounds.width, bounds.height, 1, 1, rotation);
        //public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation);

    }

    @Override
    public void dispose() {
        textureRegion.getTexture().dispose();
    }
}