package inf112.skeleton.app.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Board;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private RoboRally game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    Texture texture_actor;

    private Actor actor;
    private Board board;
    private Texture tile;

    private ArrayList<Actor> players;


    private int tile_size = 256;

    public GameScreen(RoboRally game){
        this.game = game;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 3000, 3000* (h/w));
        camera.zoom = 1.5f;
        /** yDown sets where Y-axis starts. true being bottom of screen, while false is at top of screen**/
        //camera.translate(5,5);

        batch = new SpriteBatch();
        //texture_back = new Texture(Gdx.files.internal("resources/Map_overview_Risky_Exchange.png"));
        //texture_back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //sprite_back = new Sprite(texture_back);



        board = new Board(10,10);
        tile = new Texture(Gdx.files.internal("assets/tile.png"));

        players = new ArrayList<>();
        texture_actor = new Texture(Gdx.files.internal("assets/robot.png"));

        players.add(new Actor(5, 5, texture_actor, Color.RED));
        players.add(new Actor(5, 5, texture_actor, Color.BLUE));
        players.add(new Actor(5, 5, texture_actor, Color.GREEN));
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


        //batch.draw(sprite_back, 0, 0);

        //rendering board
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                batch.draw(tile, x * tile_size, y*tile_size, tile_size, tile_size);
            }
        }

        //rendering actor
        for (Actor player : players) {
            player.update();
            player.getSprite().setPosition(player.getX() * tile_size, player.getY() * tile_size);
            player.getSprite().draw(batch);
        }
        batch.end();



    }

    @Override
    public void resize(int width, int height) {
        //TODO: FIX width adjustments zooms
        camera.viewportWidth = 3000;
        camera.viewportHeight = 3000 * height/width;
        camera.update();
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
