package nl.codeforall.cannabits.teamsweat.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import nl.codeforall.cannabits.teamsweat.gameobjects.playerstates.*;
import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.PowerUp;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.Trap;
import nl.codeforall.cannabits.teamsweat.screens.GameScreen;

import java.util.ArrayList;

public class Player extends GameObject{

    private String name;
    public static PlayerStatus DEFAULT_PLAYER_STATUS = new Default();
    public static PlayerStatus DOUBLE_SPEED_STATUS = new DoubleSpeed();
    public static PlayerStatus FROZEN_STATUS = new Frozen();
    public static PlayerStatus BURNED_STATUS = new Burning();

    private final int POWERUP_DURATION = 5;


    private float poweredUptime = 0f;
    private PowerUp powerUp;
    private PlayerStatus status;
    private Trap trap;
    private int movementSpeed;
    private ArrayList words;
    private int musicBoxes;

    private Texture textureDown;
    private Texture textureUp;
    private Texture textureLeft;
    private Texture textureRight;

    public Player(String name, Texture playerTexture){
        super(playerTexture);
        movementSpeed = 1;
        musicBoxes = 0;
        words = new ArrayList();
        this.name = name;
        status = DEFAULT_PLAYER_STATUS;
    }

    public void moveLeft(){
        super.setImage(textureLeft);
        x -= GameScreen.TRAVEL_DISTANCE * getMovementSpeed() * Gdx.graphics.getDeltaTime();
    }

    public void moveRight(){
        super.setImage(textureRight);
        x += GameScreen.TRAVEL_DISTANCE * getMovementSpeed() * Gdx.graphics.getDeltaTime();
    }

    public void moveUp(){
        super.setImage(textureUp);
        y += GameScreen.TRAVEL_DISTANCE * getMovementSpeed() * Gdx.graphics.getDeltaTime();
    }

    public void moveDown(){
        super.setImage(textureDown);
        y -= GameScreen.TRAVEL_DISTANCE * getMovementSpeed() * Gdx.graphics.getDeltaTime();
    }

    public int getMovementSpeed() {
        return status.getMovementSpeed();
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }


    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
        powerUp.pickedUp();
    }

    public void setTrap(Trap trap) {
        //sets the inventory trap
        this.trap = trap;
        trap.pickedUp();
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void usePowerUp(){
        if (powerUp != null) {
            status = powerUp.use();
            powerUp = null;
        }

    }

    public Trap placeTrap(){
        Trap toReturn = null;
        if (trap != null){
            trap.setX(this.x + 90);
            trap.setY(this.y);
            trap.use();
            toReturn = trap;
            trap = null;
        }
        return toReturn;
    }

    public void setMusicBoxes(){
        this.musicBoxes++;
    }

    public int getMusicBoxes(){
        return this.musicBoxes;
    }

    public String getName(){
        return this.name;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public void setDefault(){
        status = DEFAULT_PLAYER_STATUS;
    }

    public void setTextureDown(Texture textureDown) {
        this.textureDown = textureDown;
    }

    public void setTextureUp(Texture textureUp) {
        this.textureUp = textureUp;
    }

    public void setTextureLeft(Texture textureLeft) {
        this.textureLeft = textureLeft;
    }

    public void setTextureRight(Texture textureRight) {
        this.textureRight = textureRight;
    }
}
