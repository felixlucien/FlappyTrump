package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class Wall extends felix.flappytrump.gameobjects.gameobjectframework.GameObject {

    //How much the walls can fluctuate their Y
    private static final int FLUCTUATION = 400;
    //Makes sure walls are at least 120 high
    private static final int LOWEST_OPENING = 120;
    //Makes sure the gap in the tube is 200 y
    private static final int TUBE_GAP = 200;
    //Makes sure walls are 300 x apart
    private static final int WALL_SPACING_X = 300;

    //Collision boxed
    private Rectangle boundsTop, boundsBot;
    //A random int generator to make the wall position random
    private Random rand = new Random();
    //wall textures
    private Texture topTexture, bottomTexture;

    //X position of the wall
    private float x;

    //parent gamestate
    private PlayState parent;

    //Has the score been incremented this "Cycle"
    private boolean isScore;

    public Wall(State parent, String tag, Texture top, Texture bottom, float x) {
        super(tag, parent);
        this.parent = (PlayState) parent;
        this.x = x;
        topTexture = top;
        bottomTexture = bottom;
        boundsTop = new Rectangle();
        boundsBot = new Rectangle();
        create();
    }


    @Override
    public void create() {
        boundsTop.set(x, (float)(this.rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING), topTexture.getWidth() , topTexture.getHeight());
        boundsBot.set(x, this.boundsTop.y - TUBE_GAP - topTexture.getHeight(), bottomTexture.getWidth(), bottomTexture.getHeight());

    }

    @Override
    public void render(Batch sb) {
        sb.draw(topTexture, boundsTop.x, boundsTop.y, boundsTop.getWidth(), boundsTop.getHeight());
        sb.draw(bottomTexture, boundsBot.x, boundsBot.y, boundsBot.getWidth(), boundsBot.getHeight());
    }



    @Override
    public void update() {
        //if this walls position is smaller that 0 x, then this walls x is equal to the wall with the largest x + WALL_SPACING_X
        if(boundsBot.getX() < 0) {
            Wall lastWall = (Wall) parent.findObjectByTag("WALL 3");
            tag = lastWall.tag;
            x = lastWall.getBoundsBot().getX() + WALL_SPACING_X;
            lastWall.tag = "WALL";
            create();
            isScore = false;
        } else {
            //move wall left
            boundsBot.x -= 1.5;
            boundsTop.x -= 1.5;
        }

        if(!isScore && boundsTop.getX() < ((parent.stage.getViewport().getWorldWidth() / 2) - bottomTexture.getWidth() / 2)) {
            parent.updateScore();
            Gdx.app.log("SCORE: ", "SCORE UPDATED" + Gdx.graphics.getDeltaTime());
            isScore = true;
        }

    }

    @Override
    public void processInput() {

    }

    @Override
    public void dispose() {
        topTexture.dispose();
        bottomTexture.dispose();
    }

    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    public Rectangle getBoundsBot() {
        return boundsBot;
    }
}
