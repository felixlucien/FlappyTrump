package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import felix.flappytrump.actors.Renderer;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.Background;
import felix.flappytrump.gameobjects.Wall;
import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gameobjects.Player;
/**
 * Created by Felix McCuaig on 16/08/2017.
 */




public class PlayState extends State implements InputProcessor {
    //The below arraylist has all the objects that have to be rendered, updated and are in some cases collidable
    private ArrayList<GameObject> gameObjects;
    /*
        This object below is the gamestate manager, this reference will
         be used to communicate between gamestates and send data between them
     */
    private GameStateManager gsm;
    //this is the game score, incremented when the bird (trump) passes the walls
    private int score = 0;
    //OrthographicCamera renders the world as a 2d scene
    private OrthographicCamera cam;
    //The stage facilitates smooth rendering of both UI and game elements simultaneously
    public Stage stage;
    //this text is a UI object that renders the text that displays the games score
    private Text scoreText;
    //The viewport params will be set according to this
    private float aspectRatio;


    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;



        Gdx.input.setInputProcessor(this);

        cam = new OrthographicCamera();

        //The viewport is an object that the camera uses to "look through"
        Viewport viewport;


        //This calculates the aspect ratio of the device
        float height = Gdx.app.getGraphics().getHeight();
        float width = Gdx.app.getGraphics().getWidth();
        aspectRatio = height / width;

        if(aspectRatio < 2 && aspectRatio > 1.6) {
            //viewport is 16:9 or close
            viewport = new FillViewport(480, 800, cam);
        } else {
            //viewport is 1:1
            viewport = new FitViewport(800, 800, cam);
        }


        //Sets camera position
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        //This sets the viewport for the stage
        stage = new Stage(viewport);


        gameObjects = new ArrayList<GameObject>();

        //Game background
        gameObjects.add(new Background("BACKGROUND", this, new Rectangle(0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight()), new Texture(Gdx.files.internal("images/background.png"))));
        //Sets walls
        gameObjects.add(new Wall(this, "WALL", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 600));
        gameObjects.add(new Wall(this, "WALL", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 900));
        gameObjects.add(new Wall(this, "WALL 3", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 1200));
        //creates player object
        gameObjects.add(new Player(this, "PLAYER", new Rectangle((stage.getViewport().getWorldWidth() / 2) - 50, stage.getViewport().getWorldHeight() / 2, 100, 100), new Texture(Gdx.files.internal("trump.png"))));

        //This creates a font for the score text to render
        BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("fonts/fontMedium/font.txt"));
        scoreFont.getData().setScale(1);
        scoreFont.setColor(Color.BLACK);
        scoreText = new Text(scoreFont, score + "", (int) (stage.getViewport().getWorldWidth() / 2 - (scoreFont.getScaleX())), (int) ((int) stage.getViewport().getWorldHeight() - (stage.getViewport().getWorldHeight() / 6)));

        //The renderer will render all the items it is given when stage.draw() is called
        stage.addActor(new Renderer(gameObjects));
        //This adds the text UI component
        stage.addActor(scoreText);

        //stage.addActor(new ImageButton(new TextureRegionDrawable(new TextureRegion(img))));
    }

    @Override
    public void create() {

    }

    @Override
    public void render(Batch sb) {
        //This updates the camera in case it has been moved in some way
        cam.update();
        //This ensures that the sprite batch knows its being viewed by the camera
        sb.setProjectionMatrix(cam.combined);
        //This updates stage components like UI and renderer
        stage.act();
        //This renders the stage
        stage.draw();
    }


    @Override
    public void update() {
        //Updates all of the gameobjects
        for(GameObject object : gameObjects) {
            object.update();
        }

        //Makes sures the scoretext is the same as the score int
        scoreText.setText(score + "");
    }

    public void updateScore() {
        score++;
    }


    /*
        This method is used by objects to get instances of other objects
     */
    public GameObject findObjectByTag(String tag) {
        for(GameObject object: gameObjects) {
            if(object.tag == tag) {
                return object;
            }
        }
        return null;
    }

    //Disposes all of the gameobjects to reduce memory leaks
    @Override
    public void destroy() {
        for(GameObject object: gameObjects) {
            object.dispose();
        }
    }

    //all of these are called when the user gives input such as keyPress and touchDown and mouseDrag
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
