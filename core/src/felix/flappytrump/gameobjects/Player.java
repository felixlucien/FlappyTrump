package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import felix.flappytrump.gamestate.State;
import sun.rmi.runtime.Log;

/**
 * Created by Felix McCuaig on 15/08/2017.
 */

public class Player extends GameObject implements InputProcessor{

    Texture texture;

    public int velocity = 0;

    public Player(Texture texture, int x, int y, int width, int height, boolean collidable) {
        super(x, y, width, height, TYPE_PLAYER, collidable);
        this.texture = texture;
    }


    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(Batch sb) {
        sb.draw(texture, x, y, width, height);
    }

    @Override
    public void update() {
        if(velocity < 0 && y < 0) {
            velocity = 0;
        } else {
            y = y + velocity;
        }

        velocity--;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        velocity = 20;
        Gdx.app.log("TOUCH", velocity + "");
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}