package nl.codeforall.cannabits.teamsweat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import nl.codeforall.cannabits.teamsweat.game.TheSWEAtProject;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;
import nl.codeforall.cannabits.teamsweat.gameobjects.factories.PowerUpFactory;
import nl.codeforall.cannabits.teamsweat.gameobjects.factories.PowerUpType;
import nl.codeforall.cannabits.teamsweat.gameobjects.factories.TrapFactory;
import nl.codeforall.cannabits.teamsweat.gameobjects.factories.TrapType;
import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.DoubleSpeed;
import nl.codeforall.cannabits.teamsweat.gameobjects.powerups.PowerUp;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.FreezeTrap;
import nl.codeforall.cannabits.teamsweat.gameobjects.traps.Trap;
import java.util.Iterator;

import nl.codeforall.cannabits.teamsweat.gameobjects.musicboxes.MusicBox;

public class GameScreen implements Screen {

    public static final int TRAVEL_DISTANCE = 200;
    private final int MAX_BOXES = 1;
    public static final int X_SCREENLIMIT = 800;
    public static final int Y_SCREENLIMIT = 480;
    public static final int SPRITESIZE = 32;
    private float timeSeconds = 0f;
    private float period = 6f;

    private Player player1;
    private Player player2;

    private Array<MusicBox> musicBoxes;

    private TheSWEAtProject game;
    private OrthographicCamera camera;
    private Array<Trap> traps;
    private Music bgm;
    private TextureRegion backgroundTexture;
    private Array<PowerUp> powerUps;
    private long lastTrapDropTime;
    private long lastPowerUpDropTime;
    private long lastMusicBoxDropTime;

    public GameScreen(final TheSWEAtProject game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, X_SCREENLIMIT, Y_SCREENLIMIT);

        player1 = new Player("Player 1", new Texture(Gdx.files.internal("player1/1_single_down.png")));
        player2 = new Player("Player 2", new Texture(Gdx.files.internal("player2/2_single_down.png")));


        player1.setTextureDown(new Texture(Gdx.files.internal("player1/1_single_down.png")));
        player1.setTextureUp(new Texture(Gdx.files.internal("player1/1_single_up.png")));
        player1.setTextureLeft(new Texture(Gdx.files.internal("player1/1_single_left.png")));
        player1.setTextureRight(new Texture(Gdx.files.internal("player1/1_single_right.png")));

        player2.setTextureDown(new Texture(Gdx.files.internal("player2/2_single_down.png")));
        player2.setTextureUp(new Texture(Gdx.files.internal("player2/2_single_up.png")));
        player2.setTextureLeft(new Texture(Gdx.files.internal("player2/2_single_left.png")));
        player2.setTextureRight(new Texture(Gdx.files.internal("player2/2_single_right.png")));

        traps = new Array<>();
        traps.add(new FreezeTrap());
        backgroundTexture = new TextureRegion(new Texture("gamemap.png"), 0, 0, X_SCREENLIMIT, Y_SCREENLIMIT);

        powerUps = new Array<>();
        powerUps.add(new DoubleSpeed());

        musicBoxes = new Array<>();


        bgm = Gdx.audio.newMusic(Gdx.files.internal("gamebgm.mp3"));
        bgm.setLooping(true);
        spawnPowerUp();
        spawnTrap();
        spawnMusicBox();

    }
    private void spawnPowerUp(){
        int random = (int) (Math.random() * PowerUpType.values().length);
        powerUps.add(PowerUpFactory.getPowerUp(PowerUpType.values()[random]));
        lastPowerUpDropTime = TimeUtils.nanoTime();
    }

    private void spawnTrap(){
        int random = (int) (Math.random() * TrapType.values().length);
        traps.add(TrapFactory.getTrap(TrapType.values()[random]));
        lastTrapDropTime = TimeUtils.nanoTime();
    }

    private void spawnMusicBox(){
        if(musicBoxes.size < MAX_BOXES){
            musicBoxes.add(new MusicBox());
        }
        lastMusicBoxDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        bgm.play();
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();// draw in here
        game.batch.draw(backgroundTexture, 0, 0);
        game.batch.draw(player1.getImage(), player1.getX(), player1.getY());
        game.batch.draw(player2.getImage(), player2.getX(), player2.getY());
        game.font.draw(game.batch, "Player 1: " + player1.getMusicBoxes(), 0, 470);
        game.font.draw(game.batch, "Player 2: " + player2.getMusicBoxes(), 0, 440);

        for (Trap trap : traps) {
            game.batch.draw(trap.getImage(), trap.getX(), trap.getY());
        }

        for (PowerUp powerUp : powerUps) {
            game.batch.draw(powerUp.getImage(), powerUp.getX(), powerUp.getY());
        }

        for (MusicBox musicBox : musicBoxes) {
            game.batch.draw(musicBox.getImage(), musicBox.x, musicBox.y);}

        Iterator<MusicBox> musicBoxIterator = musicBoxes.iterator();
        while (musicBoxIterator.hasNext()) {
            MusicBox musicBox = musicBoxIterator.next();
            if (musicBox.overlaps(player1)) {
                player1.setMusicBoxes();
                musicBox.getSound().play();
                musicBoxes.add(new MusicBox());
                musicBoxIterator.remove();
            }

            if (musicBox.overlaps(player2)) {
                player2.setMusicBoxes();
                musicBox.getSound().play();
                musicBoxes.add(new MusicBox());
                musicBoxIterator.remove();
            }
        }

        timeSeconds +=Gdx.graphics.getRawDeltaTime();
        if(timeSeconds > period){
            timeSeconds-=period;
            player1.setDefault();
            player2.setDefault();
        }

        Iterator<PowerUp> powerUpIterator = powerUps.iterator();
        while (powerUpIterator.hasNext()) {
            PowerUp powerUp = powerUpIterator.next();

            if (player1.overlaps(powerUp)) {
                player1.setPowerUp(powerUp);
            }
            if (player2.overlaps(powerUp)) {
                player2.setPowerUp(powerUp);
            }

            if (powerUp.isPickedUp()) {
                powerUpIterator.remove();
            }

        }
        game.batch.end();

        Iterator<Trap> trapIterator = traps.iterator();
        while (trapIterator.hasNext()) {
            Trap trap = trapIterator.next();
            if (trap.isPickedUp() && !trap.isArmed()) {
                trapIterator.remove();
            }
            if (trap.isArmed()) {
                if (player1.overlaps(trap)) {
                    player1.setStatus( trap.spring());
                    trapIterator.remove();
                }
                if (player2.overlaps(trap)) {
                    player2.setStatus(trap.spring());
                    trapIterator.remove();
                }
            } else {
                if (player1.overlaps(trap)) {
                    player1.setTrap(trap);
                }
                if (player2.overlaps(trap)) {
                    player2.setTrap(trap);
                }
            }
        }
        if (player1.getMusicBoxes() > 20 || player2.getMusicBoxes() >20) {
            game.setScreen(new WinningScreen(game,getWinningPlayer()));
            dispose();
        }

        setPlayerControls(player1, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.O,Input.Keys.P);
        setPlayerControls(player2, Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D,Input.Keys.R,Input.Keys.T);

        if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
            Boolean fullScreen = Gdx.graphics.isFullscreen();
            Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
            if (fullScreen) {
                Gdx.graphics.setWindowedMode(currentMode.width, currentMode.height);
            } else {
                Gdx.graphics.setFullscreenMode(currentMode);
            }
        }

        if(TimeUtils.nanoTime() - lastPowerUpDropTime > 1900000000){
            spawnPowerUp();
        }

        if(TimeUtils.nanoTime() - lastTrapDropTime > 2000000000){
            spawnTrap();
        }
        if(TimeUtils.nanoTime() - lastMusicBoxDropTime > 2000000000){
            spawnMusicBox();
        }
    }

    private void setPlayerControls(Player player, int up, int down, int left, int right, int placeTrap,int usePowerUp){


        if(Gdx.input.isKeyPressed(left)) {
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(right)) {
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(down)) {
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(up)) {
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(placeTrap)) {
            Trap trap = player.placeTrap();
            if (trap != null){
                traps.add(trap);
            }
        }
        if(Gdx.input.isKeyPressed(usePowerUp)) {
            PowerUp powerUp;
            if ((powerUp = player.getPowerUp()) != null){
                player.usePowerUp();
            }
        }


        //Boundaries
        if (player.x < 0) {
            player.x = 0;
        }
        if (player.y < 0) {
            player.y = 0;
        }
        if (player.x > X_SCREENLIMIT - SPRITESIZE) {
            player.x = X_SCREENLIMIT - SPRITESIZE;
        }
        if (player.y > Y_SCREENLIMIT - SPRITESIZE) {
            player.y = Y_SCREENLIMIT - SPRITESIZE;
        }
    }

    public Player getWinningPlayer(){
        if (player1.getMusicBoxes() > player2.getMusicBoxes())
        {
            return player1;
        }

        return player2;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bgm.dispose();
    }
}
