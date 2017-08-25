package felix.flappytrump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import felix.flappytrump.gamestate.GameStateManager;
import felix.flappytrump.gamestate.PlayState;
import felix.flappytrump.gamestate.State;
public class FlappyTrump extends ApplicationAdapter {

	/*
		Sprite batch is the object that LibGdx (the backend rendering framework), uses to render objects such as
		Texture; an object in which a image file (such as a jpg, gif, png) can be loaded into and rendered onto the screen
	 */
	private SpriteBatch batch;

	/*
		The GameStateManager is an object used to manage game states such as play state, pause state, "dead state" etc.
		This framework will facilitate adding more gamestates such as pause state.
	 */
	private GameStateManager gsm;


	//The create method is called by libGdx when the project is ran.
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Stack<State> gameStates = new Stack<State>();
		gameStates.add(new PlayState(gsm));
		gsm.setGameStates(gameStates);
		gsm.create();
	}

	/*
		The render method is called lots of times per second and
		is when all of the textures are rendered and then also when the game is updated.
	 */
	@Override
	public void render () {
		//The line below clears the screen so it can be rendered to again
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//this tells the batch that its ready to be rendered to again
		batch.begin();
		//this renders gamestate manager therefor rendering the current gamestate
		gsm.render(batch);
		//closing the batch makes sure there are no memory leaks
		batch.end();
		//this updates the gamestate manager
		gsm.update();
	}

	//this method is called on the close of the program and disposes all resources so there are no memory leaks
	@Override
	public void dispose () {
		batch.dispose();
		gsm.destroy();
	}
}
