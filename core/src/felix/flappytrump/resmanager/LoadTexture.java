package felix.flappytrump.resmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class LoadTexture {
    public static Texture BACKGROUND;
    public static Texture BOTTOM_WALL;
    public static BitmapFont FLAPPY_FONT_LARGE;
    public static BitmapFont FLAPPY_FONT_MEDIUM;
    public static Texture NORMAL_TRUMP;
    public static Texture START_BUTTON;
    public static Texture TOP_WALL;

    public LoadTexture() {
    }

    public static void loadTextures() {
        FLAPPY_FONT_LARGE = new BitmapFont(Gdx.files.internal("fonts/fontLarge/font.txt"));
        FLAPPY_FONT_LARGE.setColor(Color.BLACK);
        FLAPPY_FONT_MEDIUM = new BitmapFont(Gdx.files.internal("fonts/fontMedium/font.txt"));
        FLAPPY_FONT_MEDIUM.setColor(Color.BLACK);
        BACKGROUND = new Texture("images/background.png");
        BOTTOM_WALL = new Texture("images/bottomwall.png");
        TOP_WALL = new Texture("images/topwall.png");
        NORMAL_TRUMP = new Texture("images/trump.png");
        START_BUTTON = new Texture("images/playbutton.png");
    }
}
