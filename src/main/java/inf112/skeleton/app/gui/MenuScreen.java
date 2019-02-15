package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {


    SpriteBatch batch;
    Stage stage;

    TextButton btn_play;
    private RoboRally game;

    public MenuScreen(RoboRally game) {
        this.game = game;

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        btn_play = new TextButton("Play", style);
        btn_play.getLabel().setFontScale(5);

        btn_play.addListener(event -> {
                    if (event instanceof ChangeListener.ChangeEvent) {
                        game.startGame();
                        event.handle();
                    }
                    return true;
                }
        );

        Table t = new Table();
        t.add(btn_play);
        t.setFillParent(true);
        stage.addActor(t);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    }
}
