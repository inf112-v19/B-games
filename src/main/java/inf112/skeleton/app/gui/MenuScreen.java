package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MenuScreen implements Screen {

    Stage stage;
    Stage menu;
    Stage playMenu;
    TextureAtlas atlas;

    public MenuScreen(RoboRally game) {
        // Load atlas
        atlas = new TextureAtlas(Gdx.files.internal("assets/atlas/test.atlas"));

        // UI stage
        menu = new Stage(new ScreenViewport());
        playMenu = new Stage(new ScreenViewport());

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
                        //menu.dispose();
                        //game.startGame();
                        stage = playMenu;
                        Gdx.input.setInputProcessor(stage);
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

        // region MAINMENU
        {
            // Root table
            Table t = new Table();
            //t.setDebug(true);
            t.add(btn_play).width(150);
            t.center();
            t.row();
            t.add(btn_options).width(150).padTop(20);
            t.row();
            t.add(btn_exit).width(150).padTop(200);
            t.setFillParent(true);
            menu.addActor(t);
        }
        // endregion

        // region PLAYMENU
        {
            Table t = new Table();
            TextButton btn_start = new TextButton("START", style);
            btn_start.getLabel().setFontScale(2);
            btn_start.addListener(event -> {
                        if (event instanceof ChangeListener.ChangeEvent) {
                            stage.dispose();
                            game.startGame();
                            event.handle();
                        }
                        return true;
                    }
            );
            //t.setDebug(true);
            t.add(btn_start).width(150);
            t.center();
            t.row();
            t.add().width(150).padTop(20);
            t.row();
            t.add().width(150).padTop(200);
            t.setFillParent(true);
            playMenu.addActor(t);
        }
        // endregion
        stage = menu;
        // Activate inputs
        Gdx.input.setInputProcessor(stage);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("assets/cursor.png")), 0,0));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //Resizing so that the buttons "hitbox" is correct when window is resized
        stage.getViewport().setScreenSize(width, height);
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
