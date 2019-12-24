package nl.codeforall.cannabits.teamsweat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import nl.codeforall.cannabits.teamsweat.game.TheSWEAtProject;
import nl.codeforall.cannabits.teamsweat.gameobjects.Player;

import static nl.codeforall.cannabits.teamsweat.screens.GameScreen.X_SCREENLIMIT;
import static nl.codeforall.cannabits.teamsweat.screens.GameScreen.Y_SCREENLIMIT;

public class WinningScreen implements Screen {

    final TheSWEAtProject game;
    private Player player;
    private Music bgm;
    private TextureRegion backgroundTexture;

    private OrthographicCamera camera;

    public WinningScreen(TheSWEAtProject game, Player player) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.player = player;

        bgm = Gdx.audio.newMusic(Gdx.files.internal("endbgm.mp3"));
        bgm.setLooping(true);
        backgroundTexture = new TextureRegion(new Texture("end-title.png"), 0, 0, X_SCREENLIMIT, Y_SCREENLIMIT);

    }


    @Override
    public void show() {
        bgm.play();
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture,0,0);
        game.font.draw(game.batch, player.getName() + " Won", 237, 240);
        game.batch.end();
        
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
            }
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
