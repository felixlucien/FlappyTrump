package felix.flappytrump.actors;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

/**
 * Created by Felix McCuaig on 18/08/2017.
 */

public class Text extends Actor {

    //The font that will render the text
    private BitmapFont bitmapFont;
    //The text itself
    private String text;
    //position of text
    private float x, y;

    public Text(BitmapFont bitmapFont, String text, float x, float y) {
        this.bitmapFont = bitmapFont;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    //Renders text
    @Override
    public void draw(Batch batch, float parentAlpha) {
        bitmapFont.draw(batch, text, x, y);
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

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }

    public void setBitmapFont(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getPosX() {
        return x;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public float getPosY() {
        return y;
    }

    public void setPosY(int y) {
        this.y = y;
    }
}
