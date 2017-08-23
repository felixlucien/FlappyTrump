package felix.flappytrump.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Felix McCuaig on 19/08/2017.
 */

public class ResLoader {

    public static Texture getTexture(String url) {
        return new Texture(Gdx.files.internal(url));
    }

}
