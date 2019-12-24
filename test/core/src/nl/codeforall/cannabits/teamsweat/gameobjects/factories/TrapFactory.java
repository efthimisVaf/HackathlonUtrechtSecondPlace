package nl.codeforall.cannabits.teamsweat.gameobjects.factories;

import nl.codeforall.cannabits.teamsweat.gameobjects.traps.FireTrap;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.FreezeTrap;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.Trap;

public abstract class TrapFactory {
    public static Trap getTrap(TrapType trapType) {
        switch (trapType) {
            case FREEZE_TRAP:
                return new FreezeTrap();
            case FIRE_TRAP:
                return new FireTrap();


        }
        return new FireTrap();
    }
}
