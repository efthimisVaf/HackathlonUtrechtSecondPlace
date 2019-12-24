package nl.codeforall.cannabits.teamsweat.gameobjects.powerups;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.GameObject;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;
import nl.codeforall.cannabits.teamsweat.gameobjects.playerstates.PlayerStatus;

public abstract class PowerUp extends GameObject {

    private boolean picked;
    private Sound activationSound;

    public PowerUp(Texture image,Sound activationSound) {
        super(image);
        this.activationSound = activationSound;
        picked = false;
    }

    public void pickedUp() {
        //add to player
        picked = true;


    }

    public PlayerStatus use() {
        activationSound.play();
        //remove from inventory
        return Player.DEFAULT_PLAYER_STATUS;
    }

    public boolean isPickedUp() {
        return picked;
    }


}
