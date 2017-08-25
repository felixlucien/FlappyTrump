package felix.flappytrump.gamestate;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import felix.flappytrump.actors.Text;
import felix.flappytrump.utils.FontUtils;

/**
 * Created by Felix McCuaig on 16/08/2017.
 */

public class MenuState extends State {
    private Text mainMenuText;

    //this stage is just for rendering the menu GUI
    private Stage stage;

    public MenuState() {
        stage = new Stage();
        //creates new text and sets font size to 5
        stage.addActor(mainMenuText = new Text(FontUtils.createFont(5), "GAME OVER", (int) (200 - (mainMenuText.getScaleX() * 20)), 400));
    }

    @Override
    public void create() {

    }

    @Override
    public void render(Batch sb) {
        stage.act();
        stage.draw();
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }
}
