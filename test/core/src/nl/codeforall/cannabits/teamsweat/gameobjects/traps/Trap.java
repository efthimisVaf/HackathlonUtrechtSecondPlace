package nl.codeforall.cannabits.teamsweat.gameobjects.traps;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.GameObject;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;
import nl.codeforall.cannabits.teamsweat.gameobjects.playerstates.PlayerStatus;

public abstract class Trap extends GameObject {
    private boolean armed;
    private boolean pickedUp;
    private Sound springSound;

    public Trap(Texture image,Sound springSound) {

        super(image);
        this.springSound = springSound;
        armed = false;
    }


    public void pickedUp() {
        //gets picked up by player
        pickedUp = true;
        //removed from the grid
    }

    public void use() {
        //place trap
        armed = true;
        //removed from player inventory

    }


    public PlayerStatus spring(){
        //spring trap
        armed = false;
        springSound.play();
        //removed from the grid
        return Player.DEFAULT_PLAYER_STATUS;

    }

    public boolean isArmed() {
        return armed;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
}
