package nl.codeforall.cannabits.teamsweat.gameobjects.factories;

import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.DoubleSpeed;
import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.PowerUp;

public abstract class PowerUpFactory {
    public static PowerUp getPowerUp(PowerUpType powerUpType){
        switch (powerUpType){
            case DoubleSpeed:
                return new DoubleSpeed();
        }
        return new DoubleSpeed();
    }
}
