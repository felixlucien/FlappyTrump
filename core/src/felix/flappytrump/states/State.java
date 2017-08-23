package felix.flappytrump.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import felix.flappytrump.states.GameStateManager;

public abstract class State {
    protected OrthographicCamera cam;
    protected GameStateManager gsm;
    protected Vector3 mouse;

    public State(GameStateManager var1) {
        this.gsm = var1;
        this.cam = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    public abstract void dispose();

    protected abstract void handleInput();

    public abstract void render(SpriteBatch var1);

    public abstract void update(float var1);
}
