package felix.flappytrump.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject;
import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;

/**
 * Created by Felix McCuaig on 26/08/2017.
 */

public class Text extends GameObject {

    public PlayState parent;
    public BitmapFont font;
    public Vector2 position;
    public String text;

    public Text(String tag, State parent, BitmapFont font, Vector2 position, String text) {
        super(tag, GameObject.UNDEFINED, parent, false);
        this.parent = (PlayState) parent;
        this.font = font;
        this.position = position;
        this.text = text;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void updateWhenDead() {

    }

    @Override
    public void processInput() {

    }

    @Override
    public void render(Batch batch) {
        font.draw(batch, text, position.x, position.y);
    }

    @Override
    public void dispose() {
        font.dispose();
    }


}




