package nl.codeforall.cannabits.teamsweat.gameobjects.playerstates;

import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.PowerUp;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.Trap;

public abstract class PlayerStatusImpl implements PlayerStatus{
    @Override
    public int getMovementSpeed() {
        return 1;
    }



}
