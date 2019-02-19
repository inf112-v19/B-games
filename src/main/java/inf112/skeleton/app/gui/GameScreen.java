package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import inf112.skeleton.app.Actor;
import inf112.skeleton.app.Board;
import java.util.ArrayList;

public class GameScreen implements Screen {

    private RoboRally game;

    // Rendering
    OrthographicCamera camera;
    SpriteBatch batch;
    private Sprite sprite_actor;
    private Sprite sprite_tile;
    private int tile_size = 256;
    TextureAtlas atlas;

    // Logic
    private Board board;
    private ArrayList<Actor> players;

    public GameScreen(RoboRally game) {
        this.game = game;
        atlas = new TextureAtlas(Gdx.files.internal("assets/atlas/test.atlas"));

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 3000, 3000 * (h / w));
        camera.zoom = 1.5f;
        /** yDown sets where Y-axis starts. true being bottom of screen, while false is at top of screen**/
        //camera.translate(5,5);

        batch = new SpriteBatch();
        //texture_back = new Texture(Gdx.files.internal("resources/Map_overview_Risky_Exchange.png"));
        //texture_back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //sprite_back = new Sprite(texture_back);


        board = new Board(10, 10);
        sprite_tile = atlas.createSprite("tile");
        //sprite_tile = atlas.createSprite("conveyor_belt");

        players = new ArrayList<>();
        sprite_actor = atlas.createSprite("robot");

        players.add(new Actor(5, 5, Color.RED));
        players.add(new Actor(5, 5, Color.BLUE));
        players.add(new Actor(5, 5, Color.GREEN));
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

        //rendering board
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                batch.draw(sprite_tile, x * tile_size, y * tile_size, tile_size, tile_size);
            }
        }

        //rendering actors
        for (Actor player : players) {
            player.update();
            sprite_actor.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_actor.setColor(player.getColor());
            sprite_actor.draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //TODO: FIX width adjustments zooms
        camera.viewportWidth = 3000;
        camera.viewportHeight = 3000 * height / width;
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
