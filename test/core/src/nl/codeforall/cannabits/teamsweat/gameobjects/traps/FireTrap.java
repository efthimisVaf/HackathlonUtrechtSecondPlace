package nl.codeforall.cannabits.teamsweat.gameobjects.traps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;
import nl.codeforall.cannabits.teamsweat.gameobjects.playerstates.PlayerStatus;

public class FireTrap extends Trap {

    private static Texture FIRE_TEXTURE = new Texture(Gdx.files.internal("images/traps/fire.png"));
    private static Sound FIRE_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/traps/fire.wav"));

    public FireTrap() {
        super(FIRE_TEXTURE, FIRE_SOUND);
    }

    @Override
    public PlayerStatus spring() {
        super.spring();
        return Player.BURNED_STATUS;
    }
}
