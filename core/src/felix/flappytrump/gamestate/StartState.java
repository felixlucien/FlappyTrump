package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

import java.util.ArrayList;

import felix.flappytrump.actors.Renderer2D;

/**
 * Created by Felix McCuaig on 25/08/2017.
 */

public class StartState extends State{

    private float width, height;
    private ImageButton startButton, settingsButton, optionsButton, removeAdsButton, soundButton;
    private Button test;
    private Stage stage;
    private GameStateManager gameStateManager;
    private Texture background;

    public StartState(GameStateManager gsm) {


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        gameStateManager = gsm;

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/playbutton.png"))));
        startButton.setSize(width, height / 4);
        startButton.setPosition(width / 2 - (startButton.getWidth() / 2), height / 2);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameStateManager.push(new PlayState(gameStateManager));
            }
        });


        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        Sprite sprite = new Sprite(new Texture("images/background.png"));
        sprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprites.add(sprite);
        stage.addActor(new Renderer2D(sprites));
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

    }

    @Override
    public void destroy() {

    }
}
