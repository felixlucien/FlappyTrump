package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import felix.flappytrump.FlappyTrump;
import felix.flappytrump.actors.Renderer;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.Background;
import felix.flappytrump.gameobjects.Ground;
import felix.flappytrump.gameobjects.StartText;
import felix.flappytrump.gameobjects.Wall;
import felix.flappytrump.gameobjects.gameobjectframework.ComplexGameObject;
import felix.flappytrump.gameobjects.gameobjectframework.GameObject;
import felix.flappytrump.gameobjects.Player;
import felix.flappytrump.gameobjects.gameobjectframework.SimpleGameObject;
import felix.flappytrump.utils.FontUtils;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */




public class PlayState extends State implements InputProcessor {
    //The below arraylist has all the objects that have to be rendered, updated and are in some cases collidable
    private CopyOnWriteArrayList<GameObject> gameObjects;
    /*
        This object below is the gamestate manager, this reference will
         be used to communicate between gamestates and send data between them
     */
    private GameStateManager gsm;
    //this is the game score, incremented when the bird (trump) passes the walls
    public int score = 0;
    //OrthographicCamera renders the world as a 2d scene
    private OrthographicCamera cam;
    //The stage facilitates smooth rendering of both UI and game elements simultaneously
    public Stage stage;
    //this text is a UI object that renders the text that displays the games score
    private Text scoreText;
    //The viewport params will be set according to this
    private float aspectRatio;

    //this is so walls dont come immediatley
    private boolean isPlaying = false;

    private Player player;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;

        Gdx.input.setInputProcessor(this);

        //create camera
        cam = new OrthographicCamera();

        //The viewport is an object that the camera uses to "look through"
        Viewport viewport;

        //This calculates the aspect ratio of the device
        float height = Gdx.app.getGraphics().getHeight();
        float width = Gdx.app.getGraphics().getWidth();
        aspectRatio = height / width;

        //sets viewport so scaling looks good
        if(aspectRatio < 2 && aspectRatio > 1.6) {
            //viewport is 16:9 or close
            viewport = new FillViewport(480, 800, cam);
        } else if(aspectRatio < 1.6 && aspectRatio > 1){
            //viewport is 1:1
            viewport = new FitViewport(800, 800, cam);
        } else {
            viewport = new FitViewport(width, height, cam);
        }


        //Sets camera position
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        //This sets the viewport for the stage
        stage = new Stage(viewport);


        gameObjects = new CopyOnWriteArrayList<GameObject>();


        //Game background
        //NOTHING SHOULD BE RENDERED BEFORE THIS
        float worldWidth = stage.getViewport().getWorldWidth();
        float worldHeight = stage.getViewport().getWorldHeight();

        Texture bg = new Texture(Gdx.files.internal("images/background.png"));

        float imageAspectRatio = bg.getHeight() / bg.getWidth();



        gameObjects.add(new Background("BACKGROUND", this, new Rectangle(0, 0, worldWidth, worldHeight * imageAspectRatio), bg));
        //Sets walls
        gameObjects.add(new Wall(this, "WALL", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 1000));
        gameObjects.add(new Wall(this, "WALL", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 1300));
        gameObjects.add(new Wall(this, "WALL 3", new Texture(Gdx.files.internal("images/topwall.png")), new Texture(Gdx.files.internal("images/bottomwall.png")), 1600));

        //Ground object
        Texture groundTexture = new Texture(Gdx.files.internal("images/ground.jpg"));
        gameObjects.add(new Ground("GROUND", this, new Rectangle(0, (float) (groundTexture.getHeight() / 1.5 * -1), groundTexture.getWidth(), groundTexture.getHeight()), groundTexture));
        gameObjects.add(new Ground("GROUND LAST", this, new Rectangle(groundTexture.getWidth(), (float) (groundTexture.getHeight() / 1.5 * -1), groundTexture.getWidth(), groundTexture.getHeight()), groundTexture));
        //creates player object
        Texture trumpTexture = new Texture(Gdx.files.internal("trump.png"));
        gameObjects.add(player = new Player(this, "PLAYER", new Rectangle((stage.getViewport().getWorldWidth() / 2) - 50, stage.getViewport().getWorldHeight() / 2, 100, 100), trumpTexture));
        //Start text
        BitmapFont startTextFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        startTextFont.getData().setScale(2);
        startTextFont.setColor(Color.WHITE);
        String text =  "Tap to start";
        gameObjects.add(new StartText("START TEXT", this, startTextFont, new Vector2(stage.getViewport().getWorldWidth() / 2 - (18 *text.length()), 300), text));

        //This creates a font for the score text to render
        BitmapFont scoreFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        scoreFont.getData().setScale(3);
        scoreFont.setColor(Color.BLACK);
        scoreText = new Text(scoreFont, score + "", (int) (stage.getViewport().getWorldWidth() / 2 - (18 * 1)), (int) ((int) stage.getViewport().getWorldHeight() - (stage.getViewport().getWorldHeight() / 6)));

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


    public boolean isDead = false;
    private float timer;
    private float maxTime = 1;

    @Override
    public void update() {



        if(isDead) {
            timer += Gdx.graphics.getDeltaTime();

            for(GameObject object : gameObjects) {
                object.updateWhenDead();
            }

            if(timer > maxTime) {
                gsm.push(new DeadState(this, gsm));
            }
        } else {
            checkCollisions();
            //Updates all of the gameobjects
            for(GameObject object : gameObjects) {
                object.update();
            }
            //Makes sures the scoretext is the same as the score int
            scoreText.setText(score + "");
        }


    }

    public void updateScore() {
        score++;
        scoreText.setPosX((int) (stage.getViewport().getWorldWidth() / 2 - (18 * scoreText.getText().length())));
    }

    private void checkCollisions() {
        for(GameObject object : gameObjects) {
            if(object.isCollidable()) {
                if(object.type == GameObject.TYPE_SIMPLE_GAME_OBJECT) {
                    if(Intersector.overlaps(player.hitbox, ((SimpleGameObject) object).bounds)) {
                        die();
                    }
                } else if(object.type == GameObject.TYPE_WALL) {
                    Wall gameObject = (Wall) object;
                    if(Intersector.overlaps(player.hitbox, gameObject.getBoundsTop()) || Intersector.overlaps(player.hitbox, gameObject.getBoundsBot())) {
                        die();
                    }
                }
            }
        }
    }

    private void die() {
        if(score > FlappyTrump.HIGH_SCORE) {
            FlappyTrump.HIGH_SCORE = score;
        }
        isDead = true;
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

    //allows removal of gameobjects such as when one dies
    public void deleteGameObject(GameObject object) {
        object.dispose();
        gameObjects.remove(object);
    }

    public boolean isPlaying() {
        return isPlaying;
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
        isPlaying = true;
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
