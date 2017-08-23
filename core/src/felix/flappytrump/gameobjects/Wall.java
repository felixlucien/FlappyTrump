package felix.flappytrump.gameobjects;

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

    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120;
    private static final int TUBE_GAP = 200;
    private Rectangle boundsTop, boundsBot;
    private Random rand = new Random();
    private Texture topTexture, bottomTexture;
    private boolean isScore = false;
    private float x;
    private PlayState parent;


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
        if(boundsBot.getX() < 0) {
            Wall lastWall = (Wall) parent.findObjectByTag("WALL 3");
            tag = lastWall.tag;
            x = lastWall.getBoundsBot().getX() + 200;
            lastWall.tag = "WALL";
            create();
            isScore = false;
        } else {
            boundsBot.x--;
            boundsTop.x--;
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
