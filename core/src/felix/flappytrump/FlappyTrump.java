package felix.flappytrump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Stack;

import felix.flappytrump.gameobjects.GameObject;
import felix.flappytrump.gameobjects.Player;
import felix.flappytrump.gameobjects.Wall;
import felix.flappytrump.gamestate.GameStateManager;
import felix.flappytrump.gamestate.MenuState;
import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;
import sun.rmi.runtime.Log;

public class FlappyTrump extends ApplicationAdapter {
	private SpriteBatch batch;

	private GameStateManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();


		gsm = new GameStateManager();
		Stack<State> gameStates = new Stack<State>();
		gameStates.add(new PlayState(gsm));
		gsm.setGameStates(gameStates);
		gsm.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gsm.render(batch);
		batch.end();
		gsm.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gsm.destroy();
	}
}
