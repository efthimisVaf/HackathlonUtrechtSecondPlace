package nl.codeforall.cannabits.teamsweat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import nl.codeforall.cannabits.teamsweat.game.TheSWEAtProject;

import static nl.codeforall.cannabits.teamsweat.screens.GameScreen.X_SCREENLIMIT;
import static nl.codeforall.cannabits.teamsweat.screens.GameScreen.Y_SCREENLIMIT;

public class MainMenuScreen implements Screen {

    final TheSWEAtProject game;
    private Music bgm;

    private OrthographicCamera camera;
    private TextureRegion backgroundTexture;

    public MainMenuScreen(final TheSWEAtProject game) {
        this.game = game;
        backgroundTexture = new TextureRegion(new Texture("title-screen.png"), 0, 0, X_SCREENLIMIT, Y_SCREENLIMIT);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bgm = Gdx.audio.newMusic(Gdx.files.internal("mainbgm.mp3"));
        bgm.setLooping(true);
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
        game.batch.draw(backgroundTexture, 0, 0);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
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
