package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import felix.flappytrump.actors.Renderer;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.Background;
import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gameobjects.Player;
import felix.flappytrump.gameobjects.Wall;
import felix.flappytrump.utils.ResLoader;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class PlayState extends State implements InputProcessor{
    private ArrayList<GameObject> gameObjects;
    private GameStateManager gsm;
    private int score = 0;
    private Text scoreText;
    private OrthographicCamera cam;
    private Stage stage;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;

        Gdx.input.setInputProcessor(this);

        cam = new OrthographicCamera();
        FitViewport viewport = new FitViewport(480, 800, cam);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);

        cam.update();
        stage = new Stage(viewport);


        gameObjects = new ArrayList<GameObject>();

        gameObjects.add(new Background("BACKGROUND", this, new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), new Texture(Gdx.files.internal("images/background.png"))));
        gameObjects.add(new Wall(this, "WALL", ResLoader.getTexture("images/topwall.png"), ResLoader.getTexture("images/bottomwall.png"), 600));
        gameObjects.add(new Wall(this, "WALL", ResLoader.getTexture("images/topwall.png"), ResLoader.getTexture("images/bottomwall.png"), 800));
        gameObjects.add(new Wall(this, "WALL 3", ResLoader.getTexture("images/topwall.png"), ResLoader.getTexture("images/bottomwall.png"), 1000));
        gameObjects.add(new Player(this, "PLAYER", new Rectangle(115, 200, 75, 75), new Texture(Gdx.files.internal("trump.png"))));

        BitmapFont scoreFont = new BitmapFont();


        scoreFont.getData().setScale(4);

        scoreText = new Text(scoreFont, score + "", (int) (Gdx.graphics.getWidth() / 2 - (scoreFont.getScaleX())), 700);

        stage.addActor(new Renderer(gameObjects));
        stage.addActor(scoreText);

        //stage.addActor(new ImageButton(new TextureRegionDrawable(new TextureRegion(img))));
    }

    @Override
    public void create() {

    }

    @Override
    public void render(Batch sb) {
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        stage.act();
        stage.draw();
    }


    @Override
    public void update() {
        for(GameObject object : gameObjects) {
            object.update();
        }
        scoreText.setText(score + "");
    }

    public void updateScore() {
        score++;
    }

    public GameObject findObjectByTag(String tag) {
        for(GameObject object: gameObjects) {
            if(object.tag == tag) {
                return object;
            }
        }
        return null;
    }

    @Override
    public void destroy() {
        for(GameObject object: gameObjects) {
            object.dispose();
        }
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
        for(GameObject object : gameObjects) {
            object.processInput();
        }

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
