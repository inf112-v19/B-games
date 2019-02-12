package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameScreen implements Screen {

    private RoboRally game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    Texture texture_actor;

    private Actor actor;


    public GameScreen(RoboRally game){
        this.game = game;

        camera = new OrthographicCamera();
         camera.setToOrtho(false, 1920, 1080);
         /** yDown sets where Y-axis starts. true being bottom of screen, while false is at top of screen**/

        batch = new SpriteBatch();
        texture_back = new Texture(Gdx.files.internal("assets/Map_overview_Risky_Exchange.png"));
        texture_back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite_back = new Sprite(texture_back);


        texture_actor = new Texture(Gdx.files.internal("assets/red_arrow.png"));
        actor = new Actor(200, 200, texture_actor);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(sprite_back, 0, 0);

        //rendering actor
        batch.draw(actor.getTexture(), actor.getX(), actor.getY(), actor.getTextureWidth(), actor.getTextureHeight());
        actor.update();
        batch.end();



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