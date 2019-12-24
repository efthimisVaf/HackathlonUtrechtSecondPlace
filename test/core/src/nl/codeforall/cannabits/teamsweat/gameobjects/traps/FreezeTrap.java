package nl.codeforall.cannabits.teamsweat.gameobjects.traps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;
import nl.codeforall.cannabits.teamsweat.gameobjects.playerstates.PlayerStatus;

public class FreezeTrap extends Trap {
    private static Texture FREEZE_TRAP_TEXTURE = new Texture(Gdx.files.internal("images/traps/ice.png"));
    private static Sound FREEZE_SOUND = Gdx.audio.newSound(Gdx.files.internal("sounds/traps/freeze.wav"));

    public FreezeTrap() {
        super(FREEZE_TRAP_TEXTURE,FREEZE_SOUND);
    }

    @Override
    public PlayerStatus spring() {
        super.spring();
        return Player.FROZEN_STATUS;
    }
}
