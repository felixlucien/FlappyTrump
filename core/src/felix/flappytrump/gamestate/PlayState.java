package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import felix.flappytrump.actors.Renderer;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.GameObject;
import felix.flappytrump.gameobjects.Player;
import felix.flappytrump.gameobjects.Wall;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class PlayState extends State{
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> collidables;
    private GameStateManager gsm;
    private int score = 0;

    private Text scoreText;
    private Button test;

    private OrthographicCamera cam;
    private Stage stage;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;

        cam = new OrthographicCamera(480, 800);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        FitViewport viewport = new FitViewport(480, 800, cam);
        cam.update();
        stage = new Stage(viewport);


        Texture img = new Texture(Gdx.files.internal("badlogic.jpg"));
        img.dispose();
        gameObjects = new ArrayList<GameObject>();
        collidables = new ArrayList<GameObject>();
        gameObjects.add(new Player(new Texture(Gdx.files.internal("trump.png")), 200, 50, 100, 100, true));
        gameObjects.add(new Wall(img, 400, 0, 100, 100, true));
        gameObjects.add(new Wall(img, 400, 600, 100, 600, true));





        BitmapFont scoreFont = new BitmapFont();
        scoreFont.getData().setScale(4);

        scoreText = new Text(scoreFont, score + "", (int) (Gdx.graphics.getWidth() / 2 - (scoreFont.getScaleX())), 700);

        stage.addActor(new Renderer(gameObjects));
        stage.addActor(scoreText);

        stage.addActor(new ImageButton(new TextureRegionDrawable(new TextureRegion(img))));


        addCollidables();
    }

    private void addCollidables() {
        for(GameObject o : gameObjects) {
            if(o.isCollidable()) {
                collidables.add(o);
            }
            o.setParentState(this);
        }
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
        checkCollisions();

        for(GameObject object: gameObjects) {
            object.update();
        }

        scoreText.setText(score + "");
    }

    public void updateScore() {
        score++;
    }

    private void checkCollisions() {
        for(GameObject gameObject : collidables) {
            for(GameObject object : collidables) {
                if(isColliding(gameObject, object) && gameObject != object) {
                    //Gdx.app.log("COLLISION", gameObject + "  " + object);
                    if(gsm != null) {
                        gsm.push(new MenuState());
                    } else {
                        Gdx.app.log("GSM", "IS NULL");
                    }
                }
            }
        }
    }

    private boolean isColliding(GameObject a, GameObject b) {
        Rectangle aRect = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());
        Rectangle bRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        return aRect.overlaps(bRect);
    }

    @Override
    public void destroy() {
        for(GameObject object: gameObjects) {
            object.destroy();
        }
    }
}
