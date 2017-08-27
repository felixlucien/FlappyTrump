package felix.flappytrump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import felix.flappytrump.gamestate.State;

public class StartText extends Text{

        public StartText(String tag, State parent, BitmapFont font, Vector2 position, String text) {
            super(tag, parent, font, position, text);
        }

        @Override
        public void update() {
            if(parent.isPlaying()) {
                position.x-=  4;
                if(position.x < 0 - font.getXHeight() * text.length()) {
                    parent.deleteGameObject(parent.findObjectByTag("START TEXT"));
                }
            }
        }
    }