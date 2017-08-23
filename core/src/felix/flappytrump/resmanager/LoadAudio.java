package felix.flappytrump.resmanager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import java.util.Random;

public class LoadAudio {
    public static Music CHINA_1;
    public static Music CHINA_2;
    public static Music CURRENT_SOUND;
    public static Music GREAT_RELATIONSHIP;
    public static Music I_LOVE_EM;
    public static Music LOAN;
    public static Music MEXICO;
    public static Music MEXICO_2;
    public static Music MEXICO_OR_JAPAN;
    public static Music MUSIC;
    public static Music PS_BG_MUSIC;
    private static ArrayList<Music> SOUNDS;
    public static Music START_WALL;
    public static Music YOU_GO_OVER_TO_CHINA;

    public LoadAudio() {
    }

    public static Music getRandomSound() {
        Random var0 = new Random();
        CURRENT_SOUND = (Music)SOUNDS.get(var0.nextInt(SOUNDS.size()));
        return CURRENT_SOUND;
    }

    public static void loadRes() {
        SOUNDS = new ArrayList();
        CHINA_1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/china1.wav"));
        CHINA_2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/china.wav"));
        I_LOVE_EM = Gdx.audio.newMusic(Gdx.files.internal("sounds/iloveem.wav"));
        LOAN = Gdx.audio.newMusic(Gdx.files.internal("sounds/loan.wav"));
        YOU_GO_OVER_TO_CHINA = Gdx.audio.newMusic(Gdx.files.internal("sounds/yougooverto.wav"));
        START_WALL = Gdx.audio.newMusic(Gdx.files.internal("sounds/startwall.wav"));
        GREAT_RELATIONSHIP = Gdx.audio.newMusic(Gdx.files.internal("sounds/greatrelationship.wav"));
        MEXICO = Gdx.audio.newMusic(Gdx.files.internal("sounds/mexico.wav"));
        MEXICO_2 = Gdx.audio.newMusic(Gdx.files.internal("sounds/mexico2.wav"));
        MEXICO_OR_JAPAN = Gdx.audio.newMusic(Gdx.files.internal("sounds/mexicoorjapan.wav"));
        SOUNDS.add(CHINA_1);
        SOUNDS.add(CHINA_2);
        SOUNDS.add(I_LOVE_EM);
        SOUNDS.add(LOAN);
        SOUNDS.add(YOU_GO_OVER_TO_CHINA);
        SOUNDS.add(START_WALL);
        SOUNDS.add(GREAT_RELATIONSHIP);
        SOUNDS.add(MEXICO);
        SOUNDS.add(MEXICO_2);
        SOUNDS.add(MEXICO_OR_JAPAN);
        MUSIC = Gdx.audio.newMusic(Gdx.files.internal("sounds/TrumpSaysChina.mp3"));
        PS_BG_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic.mp3"));
    }

    public static void playMusic() {
        MUSIC.play();
    }
}
