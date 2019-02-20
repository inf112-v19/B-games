package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MenuScreen implements Screen {

    Stage stage;
    TextureAtlas atlas;

    public MenuScreen(RoboRally game) {
        // Load atlas
        atlas = new TextureAtlas(Gdx.files.internal("assets/atlas/test.atlas"));

        // UI stage
        stage = new Stage(new ScreenViewport());

        // Set up button style
        NinePatch patch_up = atlas.createPatch("button_up");
        NinePatch patch_down = atlas.createPatch("button_down");
        patch_up.setColor(Color.RED);
        patch_down.setColor(Color.RED);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.up = new NinePatchDrawable(patch_up);
        style.down = new NinePatchDrawable(patch_down);
        style.overFontColor = Color.BLACK;
        style.fontColor = Color.WHITE;


        // BUTTONS
        TextButton btn_play = new TextButton("PLAY", style);
        btn_play.getLabel().setFontScale(2);

        TextButton btn_options = new TextButton("OPTIONS", style);
        btn_options.getLabel().setFontScale(2);

        TextButton btn_exit = new TextButton("EXIT", style);
        btn_exit.getLabel().setFontScale(2);

        btn_play.addListener(event -> {
                    if (event instanceof ChangeListener.ChangeEvent) {
                        game.startGame();
                        event.handle();
                    }
                    return true;
                }
        );
        btn_options.addListener(event -> {
                    if (event instanceof ChangeListener.ChangeEvent) {
                        System.out.println("Option screen not implemented");
                        event.handle();
                    }
                    return true;
                }
        );
        btn_exit.addListener(event -> {
                    if (event instanceof ChangeListener.ChangeEvent) {
                        event.handle();
                        Gdx.app.exit();
                    }
                    return true;
                }
        );

        // Root table
        Table t = new Table();
        t.background(new TextureRegionDrawable(new Texture(Gdx.files.internal("assets/background.png"))));

        //t.setDebug(true);
        t.add(btn_play).width(150);
        t.center();
        t.row();
        t.add(btn_options).width(150).padTop(20);
        t.row();
        t.add(btn_exit).width(150).padTop(200);
        t.setFillParent(true);

        stage.addActor(t);

        // Activate inputs
        Gdx.input.setInputProcessor(stage);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("assets/cursor.png")), 0,0));
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
