package felix.flappytrump.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import felix.flappytrump.states.State;
import java.util.Stack;

public class GameStateManager {
    public Stack<State> states = new Stack();

    public GameStateManager() {
    }

    public void pop() {
        ((State)this.states.pop()).dispose();
    }

    public void push(State var1) {
        this.states.push(var1);
    }

    public void render(SpriteBatch var1) {
        ((State)this.states.peek()).render(var1);
    }

    public void set(State var1) {
        this.states.pop();
        this.states.push(var1);
    }

    public void update(float var1) {
        ((State)this.states.peek()).update(var1);
    }
}
