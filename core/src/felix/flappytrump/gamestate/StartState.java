package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import felix.flappytrump.actors.Renderer;
import felix.flappytrump.actors.Renderer2D;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.Player;
import felix.flappytrump.gameobjects.PlayerStart;
import felix.flappytrump.gameobjects.gameobjectframework.GameObject;

/**
 * Created by Felix McCuaig on 25/08/2017.
 */

public class StartState extends State{

    private ImageButton startButton, settingsButton, optionsButton, removeAdsButton, soundButton;
    private Stage stage;
    private GameStateManager gameStateManager;
    private Texture background;
    private CopyOnWriteArrayList<GameObject> gameObjects;
    private Music music;

    public StartState(GameStateManager gsm) {

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/startmusic.mp3"));
        music.play();
        //The viewport is an object that the camera uses to "look through"
        Viewport viewport;

        //This calculates the aspect ratio of the device
        float height = Gdx.app.getGraphics().getHeight();
        float width = Gdx.app.getGraphics().getWidth();
        float aspectRatio = height / width;

        //sets viewport so scaling looks good
        if(aspectRatio < 2 && aspectRatio > 1.6) {
            //viewport is 16:9 or close
            viewport = new FillViewport(480, 800);
        } else if(aspectRatio < 1.6 && aspectRatio > 1){
            //viewport is 1:1
            viewport = new FitViewport(800, 800);
        } else {
            viewport = new FitViewport(width, height);
        }

        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        gameStateManager = gsm;

        float gameWidth = stage.getViewport().getWorldWidth();
        float gameHeight = stage.getViewport().getWorldHeight();

        startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/playbutton.png"))));
        startButton.setSize(gameWidth, gameHeight / 4);
        startButton.setPosition(gameWidth / 2 - (startButton.getWidth() / 2), gameHeight / 5);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                music.stop();
                gameStateManager.push(new PlayState(gameStateManager, gameStateManager.sounds));
            }
        });

        gameObjects = new CopyOnWriteArrayList<GameObject>();
        Texture trumpTexture = new Texture(Gdx.files.internal("trump.png"));
        gameObjects.add(new PlayerStart(this, "PLAYER", new Rectangle((stage.getViewport().getWorldWidth() / 2) - 50, stage.getViewport().getWorldHeight() / 2, 100, 100), trumpTexture));



        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        Sprite sprite = new Sprite(new Texture("images/background.png"));
        sprite.setBounds(0, 0, gameWidth, gameHeight);
        sprites.add(sprite);

        stage.addActor(new Renderer2D(sprites));
        stage.addActor(new Renderer(gameObjects));

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        String startText = "Flappy Trump";
        stage.addActor(new Text(font, startText, stage.getViewport().getWorldWidth() / 2 - (18 * startText.length()), (stage.getViewport().getWorldHeight() / 4) * 3));

        stage.addActor(startButton);
    }

    @Override
    public void create() {



    }

    @Override
    public void render(Batch sb) {
        stage.draw();
        stage.act();
    }

    @Override
    public void update() {
        for(GameObject object : gameObjects) {
            object.update();
        }
    }

    @Override
    public void destroy() {

    }
}
