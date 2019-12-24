package nl.codeforall.cannabits.teamsweat.gameobjects.musicboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.GameObject;

public class MusicBox extends GameObject {

    private static final Texture IMAGE = new Texture("musicboxes/73687619-icon-music-box-scaled.png");
    private Sound sound;
    private Boolean pickedUp;


    public MusicBox() {
        super(IMAGE);
        this.sound = Gdx.audio.newSound(Gdx.files.internal("musicboxes/Sonic.Ring.mp3"));
    }

    public Sound getSound(){
        return this.sound;
    }

    public boolean isPickedUp(){
        return this.pickedUp;
    }

    public void pickedUp() {

    }

    public void use() {
        //can't be used
    }
}
