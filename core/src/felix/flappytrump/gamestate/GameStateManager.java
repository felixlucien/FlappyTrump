package felix.flappytrump.gamestate;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;
import java.util.Stack;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class GameStateManager {
    Stack<State> gameStates;
    State currentState;

    public GameStateManager(Stack<State> gameStates) {
        this.gameStates = gameStates;
        //currentState = gameStates.get(0);
    }

    public GameStateManager() {

    }

    public void create() {
        for(State s: gameStates) {
            s.create();
        }
    }

    public void render(Batch sb) {
        gameStates.peek().render(sb);
    }

    public void update() {
        gameStates.peek().update();
    }

    public void destroy() {
        for(State s: gameStates) {
            s.destroy();
        }
    }

    public void push(State gameState) {
        gameStates.pop();
        gameStates.push(gameState);
    }

    public void pop() {
        gameStates.push(gameStates.pop());
    }

    public void peek() {
        gameStates.peek();
    }

    public void setGameStates(Stack<State> gameStates) {
        this.gameStates = gameStates;
    }

}
