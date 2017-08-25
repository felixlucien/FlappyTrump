package felix.flappytrump.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Felix McCuaig on 18/08/2017.
 */

public class FontUtils {


    //This is used to create fonts faster and more efficiently
    public static BitmapFont createFont(int scale) {
        BitmapFont font = new BitmapFont();
        font.getData().setScale(scale);
        return font;
    }


}
