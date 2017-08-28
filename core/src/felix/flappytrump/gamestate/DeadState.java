package felix.flappytrump.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import felix.flappytrump.FlappyTrump;
import felix.flappytrump.actors.Renderer2D;
import felix.flappytrump.actors.Text;
import felix.flappytrump.gameobjects.Background;
import felix.flappytrump.utils.FontUtils;


/**
 * Created by Felix McCuaig on 26/08/2017.
 */

public class DeadState extends State {

    private PlayState sender;
    private GameStateManager gsm;

    private Texture background, ground;

    private Stage stage;

    public DeadState(PlayState sender, final GameStateManager gsm) {
        this.sender = sender;
        this.gsm = gsm;

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

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();


        Sprite background = new Sprite(new Texture("images/background.png"));
        background.setBounds(0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        sprites.add(background);

        Sprite ground = new Sprite(new Texture("images/ground.jpg"));
        ground.setBounds(0, ground.getHeight() / 1.5F * -1F, ground.getWidth(), ground.getHeight());
        sprites.add(ground);

        Sprite scoreScreen = new Sprite(new Texture("images/scorescreen.png"));
        scoreScreen.setSize(scoreScreen.getWidth() * 2,  scoreScreen.getHeight() * 2);
        scoreScreen.setPosition(stage.getViewport().getWorldWidth() / 2 - scoreScreen.getWidth() / 2, (float) (stage.getViewport().getWorldHeight() / 2 - scoreScreen.getHeight() / 1.2));
        sprites.add(scoreScreen);





        Gdx.input.setInputProcessor(stage);

        stage.addActor(new Renderer2D(sprites));

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        String gameOver = "GAME OVER";
        stage.addActor(new Text(font, gameOver, stage.getViewport().getWorldWidth() / 2 - (18 * gameOver.length()), (stage.getViewport().getWorldHeight() / 4) * 3));


        BitmapFont smallFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        smallFont.setColor(Color.BLACK);
        smallFont.getData().setScale(1.3F);

        String currentScore = "Current Score: " + sender.score;
        stage.addActor(new Text(smallFont, currentScore, (int) (stage.getViewport().getWorldWidth() / 2 - (11.7 * currentScore.length())), (stage.getViewport().getWorldHeight() / 4) * 2));

        String topScore = "Top Score: " + FlappyTrump.HIGH_SCORE;
        stage.addActor(new Text(smallFont, topScore, (int) (stage.getViewport().getWorldWidth() / 2 - (11.7 * topScore.length())), (int) ((stage.getViewport().getWorldHeight() / 4) * 1.6F)));

        ImageButton startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/playagainbutton.png"))));
        //startButton.setSize(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 4);
        startButton.setPosition(stage.getViewport().getWorldWidth() / 2 - (startButton.getWidth() / 2),  (stage.getViewport().getWorldHeight()/ 4) - stage.getViewport().getWorldHeight() / 20);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gsm.push(new PlayState(gsm, gsm.sounds));
            }
        });
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
