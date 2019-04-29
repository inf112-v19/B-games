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
import inf112.skeleton.app.Board.ITile;
import inf112.skeleton.app.Board.Laser;
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
    private Sprite sprite_laser;
    private Sprite sprite_hole;
    private Sprite sprite_hole_edge;
    private Sprite sprite_wall;

    private Sprite sprite_body;
    private Sprite sprite_wheels;
    private Sprite sprite_eye;


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
    Sprite sprite_beam;

    //UI
    private GameUI UI;

    public GameScreen(RoboRally game) {
        this.game = game;
        atlas = Assets.getTextureAtlas();

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
        sprite_laser = atlas.createSprite("laser");
        sprite_beam = atlas.createSprite("laserbeam");
        sprite_hole = atlas.createSprite("hole");
        sprite_hole_edge = atlas.createSprite("hole_edge");
        sprite_wall = atlas.createSprite("wall");

        sprite_body = atlas.createSprite("body");
        sprite_eye = atlas.createSprite("eye");
        sprite_wheels = atlas.createSprite("wheels");


        conveyor = new Animation<>(1f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);



        players = new ArrayList<>();
        sprite_actor = atlas.createSprite("robot");

        action = new Action(board);
        //X and Y here represent which tile they are on, not pixel location!
        players.add(new Actor(5, 5, Color.RED, board, 1));
        players.add(new Actor(5, 6, Color.BLUE, board, 2));
        players.add(new Actor(5, 7, Color.GREEN, board, 3));

        //Initiating new UI object(singleton) and passing in necessary objects.
        UI = new GameUI(atlas, players, action);
        //Loading in UI elements
        UI.loadUI();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        elapsedTime += deltaTime * animSpeed;

        // Animation test
        Animation<Sprite> conveyor = new Animation<>(0.1f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);
        Animation<Sprite> conveyor2 = new Animation<>(0.1f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);

        // Get current frame of animation for the current stateTime
        Sprite currentFrame = conveyor.getKeyFrame(elapsedTime, true);
        currentFrame.setSize(tile_size, tile_size);
        currentFrame.setOriginCenter();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        sprite_tile.setSize(tile_size, tile_size);
        sprite_laser.setSize(tile_size, tile_size);
        sprite_beam.setSize(tile_size,tile_size);
        sprite_hole.setSize(tile_size,tile_size);
        sprite_wall.setSize(tile_size,tile_size);
        sprite_wall.setOriginCenter();
        sprite_hole_edge.setSize(tile_size,tile_size);
        sprite_hole_edge.setColor(Color.GOLD);
        sprite_laser.setOriginCenter();
        sprite_beam.setOriginCenter();


        //rendering board
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                ITile tile = board.getAt(x, y);
                Direction conveyorFacing = tile.hasConveyor();


                // Conveyor
                if (conveyorFacing != null) {
                    currentFrame.setRotation(DirectionHelpers.rotationFromDirection(conveyorFacing));
                    currentFrame.setPosition(x * tile_size, y * tile_size);
                    currentFrame.draw(batch);
                    continue;
                }



                sprite_tile.setPosition(x * tile_size, y * tile_size);
                sprite_tile.draw(batch);
                if(tile.isHole()){
                    sprite_hole.setPosition(x * tile_size, y * tile_size);
                    sprite_hole.draw(batch);
                    sprite_hole_edge.setPosition(x * tile_size, y * tile_size);
                    sprite_hole_edge.draw(batch);
                }

                if (Laser.class.isInstance(tile)) {
                    Direction direction = ((Laser)tile).getLaser();
                    sprite_laser.setColor(Color.RED);
                    sprite_laser.setPosition(x * tile_size, y * tile_size);
                    sprite_laser.setRotation(DirectionHelpers.rotationFromDirection(direction));
                    sprite_laser.draw(batch);
                }

                // Walls
                if(tile.hasWall(Direction.NORTH)){
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.NORTH));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if(tile.hasWall(Direction.SOUTH)){
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.SOUTH));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if(tile.hasWall(Direction.EAST)){
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.EAST));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if(tile.hasWall(Direction.WEST)){
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.WEST));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }

            }
        }

/*
        // TODO Laser fire test, move this to event
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                ITile tile = board.getAt(x, y);
                if (Laser.class.isInstance(tile)) {
                    Direction direction = ((Laser)tile).getLaser();

                    sprite_beam.setPosition(x * tile_size, y * tile_size);
                    sprite_beam.setRotation(DirectionHelpers.rotationFromDirection(direction));
                    sprite_beam.draw(batch);
                }
            }
        }
*/




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
            /*
            sprite_actor.setOriginCenter();
            sprite_actor.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_actor.setRotation(DirectionHelpers.rotationFromDirection(player.direction));
            sprite_actor.setSize(tile_size, tile_size);
            sprite_actor.setColor(player.getColor());
            sprite_actor.draw(batch);*/
            sprite_wheels.setOriginCenter();
            sprite_wheels.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_wheels.setRotation(DirectionHelpers.rotationFromDirection(player.direction));
            sprite_wheels.setColor(Color.GRAY);
            sprite_wheels.setSize(tile_size, tile_size);
            sprite_wheels.draw(batch);
            sprite_body.setOriginCenter();
            sprite_body.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_body.setRotation(DirectionHelpers.rotationFromDirection(player.direction));
            sprite_body.setSize(tile_size, tile_size);
            sprite_body.setColor(player.getColor());
            sprite_body.draw(batch);
            sprite_eye.setOriginCenter();
            sprite_eye.setPosition(player.getX() * tile_size, player.getY() * tile_size);
            sprite_eye.setRotation(DirectionHelpers.rotationFromDirection(player.direction));
            sprite_eye.setSize(tile_size, tile_size);
            sprite_eye.draw(batch);
            player.tileCheck();
        }
        batch.end();

        //Rendering of the user interface
        UI.renderUI();
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
