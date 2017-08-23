package felix.flappytrump.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import felix.flappytrump.resmanager.LoadTexture;
import java.util.Random;

public class Wall {
    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120;
    private static final int TUBE_GAP = 100;
    public static final int TUBE_WIDTH = 52;
    private Rectangle boundsTop, boundsBot;
    private Vector2 posTopTube, posBotTube;
    private Random rand = new Random();
    private Texture topTexture, bottomTexture;

    public Wall(float var1) {
        this.posTopTube = new Vector2(var1, (float)(this.rand.nextInt(130) + 100 + 120));
        this.posBotTube = new Vector2(var1, this.posTopTube.y - 100.0F - (float)LoadTexture.BOTTOM_WALL.getHeight());
        this.boundsTop = new Rectangle(this.posTopTube.x, this.posTopTube.y, (float)LoadTexture.TOP_WALL.getWidth(), (float)LoadTexture.TOP_WALL.getHeight());
        this.boundsBot = new Rectangle(this.posBotTube.x, this.posBotTube.y, (float)LoadTexture.TOP_WALL.getWidth(), (float)LoadTexture.TOP_WALL.getHeight());
    }

    public boolean collides(Rectangle var1) {
        return Intersector.overlaps(var1, this.boundsTop) || Intersector.overlaps(var1, this.boundsBot);
    }

    public Vector2 getPosBotTube() {
        return this.posBotTube;
    }

    public Vector2 getPosTopTube() {
        return this.posTopTube;
    }

    public void reposition(float var1) {
        this.posTopTube.set(var1, (float)(this.rand.nextInt(130) + 100 + 120));
        this.posBotTube.set(var1, this.posTopTube.y - 100.0F - (float)LoadTexture.BOTTOM_WALL.getHeight());
        this.boundsTop.setPosition(this.posTopTube.x, this.posTopTube.y);
        this.boundsBot.setPosition(this.posBotTube.x, this.posBotTube.y);
    }
}
