package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.DirectionHelpers;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.CardType;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {

    private RoboRally game;

    // Rendering
    OrthographicCamera camera;
    SpriteBatch batch;
    private Sprite sprite_actor;
    private Sprite sprite_tile;
    private int tile_size = 256;
    TextureAtlas atlas;

    // Animations
    float elapsedTime = 0;
    float animSpeed = 1f;
    Animation<Sprite> conveyor;

    // Logic
    private Board board;
    private ArrayList<Actor> players;
    private Action action;

    // Test
    private float actionInterval = 1;
    private float timer = 0;
    private int player = 0;

    //UI
    private GameUI UI;

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
        board.generateRandom();
        sprite_tile = atlas.createSprite("tile");
        conveyor = new Animation<>(0.033f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);

        players = new ArrayList<>();
        sprite_actor = atlas.createSprite("robot");

        action = new Action(board);
        //X and Y here represent which tile they are on, not pixel location!
        players.add(new Actor(5, 5, Color.RED, board, 1));
        players.add(new Actor(5, 6, Color.BLUE, board, 2));
        players.add(new Actor(5, 7, Color.GREEN, board, 3));

        //Initiating new UI object(singleton) and passing in necessary objects.
        UI = new GameUI(atlas, players, action, board);
        //Loading in UI elements
        UI.loadUI2();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        elapsedTime += deltaTime * animSpeed;

        // Animation test
        Animation<Sprite> conveyor = new Animation<>(0.033f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);
        Animation<Sprite> conveyor2 = new Animation<>(0.033f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);

        // Get current frame of animation for the current stateTime
        Sprite currentFrame = conveyor.getKeyFrame(elapsedTime, true);
        currentFrame.setSize(tile_size,tile_size);
        currentFrame.setOriginCenter();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //rendering board
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                Direction facing = board.getAt(x,y).hasConveyor();
                if(facing == null){
                    batch.draw(sprite_tile, x * tile_size, y * tile_size, tile_size, tile_size);
                } else {
                    currentFrame.setPosition(x * tile_size, y * tile_size);
                    switch (facing) {
                        case NORTH:
                            currentFrame.setRotation(0);
                            break;
                        case EAST:
                            currentFrame.setRotation(270);
                            break;
                        case SOUTH:
                            currentFrame.setRotation(180);
                            break;
                        case WEST:
                            currentFrame.setRotation(90);
                            break;
                    }
                    currentFrame.draw(batch);
                }
            }
        }
        /**
        // Move actors
        timer += deltaTime;
        if(timer > actionInterval){
            timer -= actionInterval;
            player++;
            if (player >= players.size()){
                player = 0;
            }
            moveRandomly(players.get(player));
        }
        **/

        //rendering actors
        for (Actor player : players) {
            sprite_actor.setOriginCenter();
            sprite_actor.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_actor.setRotation(DirectionHelpers.rotationFromDirection(player.direction));
            sprite_actor.setSize(tile_size, tile_size);
            sprite_actor.setColor(player.getColor());
            sprite_actor.draw(batch);
            player.tileCheck();
        }
        batch.end();

        //Rendering of the user interface
        UI.renderUI2();
    }

    private void moveRandomly(Actor player) {
        Random r = new Random();
        int random = r.nextInt(CardType.values().length);
        action.playCard(player, CardType.values()[random]);
    }

    @Override
    public void resize(int width, int height) {
        //TODO: FIX width adjustments zooms
        camera.viewportWidth = 3000;
        camera.viewportHeight = 3000 * height / width;
        camera.update();

        //UI.resizeUI(width, height);
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
