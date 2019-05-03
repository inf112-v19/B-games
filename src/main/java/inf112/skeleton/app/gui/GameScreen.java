package inf112.skeleton.app.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Actor.Direction;
import inf112.skeleton.app.Actor.DirectionHelpers;
import inf112.skeleton.app.Actor.Player;
import inf112.skeleton.app.Board.*;
import inf112.skeleton.app.Cards.CardStack;
import inf112.skeleton.app.Maps.BasicMapLoaderSaver;
import inf112.skeleton.app.Maps.IMapLoaderSaver;
import inf112.skeleton.app.Prototyping;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private RoboRally game;

    // Rendering
    OrthographicCamera camera;
    private int tile_size = 128;
    private int conveyor_padding = 8;
    TextureAtlas atlas;
    SpriteBatch batch;
    private Sprite sprite_tile;
    private Sprite sprite_laser;
    private Sprite sprite_hole;
    private Sprite sprite_hole_edge;
    private Sprite sprite_wall;
    private Sprite sprite_body;
    private Sprite sprite_wheels;
    private Sprite sprite_eye;
    private Sprite sprite_beam;
    private Sprite sprite_cog;
    private Sprite sprite_flag;
    private Sprite sprite_wrench;

    private Sprite sprite_conveyor;
    private Sprite sprite_conveyor_fast;
    private float conveyor_fastRegionY;
    private float conveyorRegionY;

    // Animations
    float elapsedTime = 0;
    float animSpeed = 100f;
    Animation<Sprite> conveyor;
    float cog_rotation = 0;

    // Logic
    private Board board;
    private ArrayList<Player> players;
    private Action action;
    private CardStack cs;
    private int playerTurn = 0;
    private String phase = "draw";
    private IMapLoaderSaver loaderSaver = new BasicMapLoaderSaver();

    // TODO Keep for eventual sprite scrolling logic
    private float actionInterval = 1;
    private float timer = 0;
    private int player = 0;

    //UI
    private GameUI UI;
    private float scrollSpeed = 0.1f;
    private float translateSpeed = 100f;

    public GameScreen(RoboRally game) {
        this.game = game;
        atlas = Assets.getTextureAtlas();

        InputProcessor ip = new InputAdapter() {
            @Override
            public boolean scrolled(int amount) {
                camera.zoom += amount * 0.1f;
                camera.update();
                return super.scrolled(amount);
            }
        };
        Gdx.input.setInputProcessor(ip);

        // Viewport settings
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 1f;
        /** yDown sets where Y-axis starts. true being bottom of screen, while false is at top of screen**/
        //camera.translate(5,5);

        // Rendering
        batch = new SpriteBatch();
        //texture_back = new Texture(Gdx.files.internal("resources/Map_overview_Risky_Exchange.png"));
        //texture_back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        //sprite_back = new Sprite(texture_back);
        // Load sprites
        sprite_tile = atlas.createSprite("tile");
        sprite_laser = atlas.createSprite("laser");
        sprite_beam = atlas.createSprite("laserbeam");
        sprite_hole = atlas.createSprite("hole");
        sprite_hole_edge = atlas.createSprite("hole_edge");
        sprite_wall = atlas.createSprite("wall");
        sprite_body = atlas.createSprite("body");
        sprite_eye = atlas.createSprite("eye");
        sprite_wheels = atlas.createSprite("wheels");
        sprite_conveyor = atlas.createSprite("conveyor_long");
        sprite_conveyor_fast = atlas.createSprite("conveyor_long");
        sprite_cog = atlas.createSprite("flag"); // TODO need a cogwheel sprite
        sprite_flag = atlas.createSprite("flag");
        sprite_wrench = atlas.createSprite("flag"); // TODO need wrench sprite
        conveyor = new Animation<>(1f, atlas.createSprites("conveyor"), Animation.PlayMode.LOOP);
        // Scale
        sprite_conveyor.setSize(tile_size - conveyor_padding * 2, tile_size - conveyor_padding * 2); // render inside tile edges
        sprite_conveyor_fast.setSize(tile_size - conveyor_padding * 2, tile_size - conveyor_padding * 2); // render inside tile edges
        sprite_tile.setSize(tile_size, tile_size);
        sprite_laser.setSize(tile_size, tile_size);
        sprite_beam.setSize(tile_size, tile_size);
        sprite_hole.setSize(tile_size, tile_size);
        sprite_wall.setSize(tile_size, tile_size);
        sprite_hole_edge.setSize(tile_size, tile_size);
        sprite_cog.setSize(tile_size, tile_size);
        sprite_flag.setSize(tile_size, tile_size);
        sprite_wrench.setSize(tile_size, tile_size);
        // Center rotation
        sprite_conveyor.setOriginCenter();
        sprite_conveyor_fast.setOriginCenter();
        sprite_wall.setOriginCenter();
        sprite_laser.setOriginCenter();
        sprite_beam.setOriginCenter();
        sprite_cog.setOriginCenter();
        // Colors
        sprite_hole_edge.setColor(Color.GOLD);
        sprite_conveyor.setColor(Color.GOLD);
        sprite_conveyor_fast.setColor(Color.RED);
        // cut conveyor texture in half
        sprite_conveyor.setRegion(sprite_conveyor.getRegionX(), sprite_conveyor.getRegionY(), sprite_conveyor.getRegionWidth(), sprite_conveyor.getRegionHeight() / 2);
        sprite_conveyor_fast.setRegion(sprite_conveyor_fast.getRegionX(), sprite_conveyor_fast.getRegionY(), sprite_conveyor_fast.getRegionWidth(), sprite_conveyor_fast.getRegionHeight() / 2);
        conveyorRegionY = sprite_conveyor.getRegionY();
        conveyor_fastRegionY = sprite_conveyor_fast.getRegionY();

        // Game initialization
        //board = Prototyping.generateRandomBoard(10, 10);
        board = loaderSaver.load("maps/Risky.txt");
        players = new ArrayList<>();
        action = new Action(board, players);

        cs = new CardStack();
        cs.initializeCardStack();
        cs.randomizeCardStack();
        // X and Y here represent which tile they are on, not pixel location!
        // TODO playtest, 3 players
        players.add(new Player(5, 5, Color.RED, board, 1, 1, cs, false));
        players.add(new Player(5, 6, Color.GREEN, board, 2, 2, cs, false));
        players.add(new Player(5, 7, Color.BLUE, board, 3, 3, cs, false));
        players.add(new Player(5, 7, Color.YELLOW, board, 3, 3, cs, false));

        // Initiating new UI object(singleton) and passing in necessary objects.
        UI = new GameUI(atlas, action, this);
        // Loading in UI elements

        // TODO remove, playtest
        players.get(1).receiveDamage();
        players.get(1).receiveDamage();
        players.get(1).receiveDamage();
        players.get(1).receiveDamage();


        UI.loadUI(players.get(0));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        elapsedTime += deltaTime * animSpeed;
        cog_rotation += deltaTime * 15;
        // move through texture region and reset on repeating pattern.
        int conveyorY = sprite_conveyor.getRegionY() + 1;//+ 0.01f;
        if (conveyorY > conveyorRegionY + 32) {
            conveyorY = (int) conveyorRegionY;
        }
        int conveyor_fast_y = sprite_conveyor_fast.getRegionY() + 2;//+ 0.01f;
        if (conveyor_fast_y > conveyor_fastRegionY + 32) {
            conveyor_fast_y = (int) conveyor_fastRegionY;
        }
        sprite_conveyor.setRegion(sprite_conveyor.getRegionX(), conveyorY, sprite_conveyor.getRegionWidth(), sprite_conveyor.getRegionHeight());
        sprite_conveyor_fast.setRegion(sprite_conveyor_fast.getRegionX(), conveyor_fast_y, sprite_conveyor_fast.getRegionWidth(), sprite_conveyor_fast.getRegionHeight());
        //sprite_conveyor.scroll(0,0.001f);

        // Wipe screen
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Rendering board
        for (int x = 0; x < board.getHeight(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                ITile tile = board.getAt(x, y);
                Conveyor conveyor = tile.hasConveyor();

                sprite_tile.setPosition(x * tile_size, y * tile_size);
                sprite_tile.draw(batch);
                // Conveyor
                if (conveyor != null) {
                    Sprite currentConveyor;
                    if (conveyor.fast) {
                        currentConveyor = sprite_conveyor_fast;
                    } else {
                        currentConveyor = sprite_conveyor;
                    }
                    currentConveyor.setRotation(DirectionHelpers.rotationFromDirection(conveyor.direction));
                    currentConveyor.setPosition(x * tile_size + conveyor_padding, y * tile_size + conveyor_padding);
                    currentConveyor.draw(batch);
                    //continue;
                }
                if (tile.isHole()) {
                    sprite_hole.setPosition(x * tile_size, y * tile_size);
                    sprite_hole.draw(batch);
                    sprite_hole_edge.setPosition(x * tile_size, y * tile_size);
                    sprite_hole_edge.draw(batch);
                }
                if (tile.hasCog() != null) {
                    RotationDirection r = tile.hasCog();
                    sprite_cog.setPosition(x * tile_size, y * tile_size);
                    if (r == RotationDirection.CLOCKWISE) {
                        sprite_cog.setRotation(-cog_rotation);
                    } else {
                        sprite_cog.setRotation(cog_rotation);
                    }
                    sprite_cog.draw(batch);
                }

                if (Laser.class.isInstance(tile)) {
                    Direction direction = ((Laser) tile).getLaser();
                    sprite_laser.setColor(Color.RED);
                    sprite_laser.setPosition(x * tile_size, y * tile_size);
                    sprite_laser.setRotation(DirectionHelpers.rotationFromDirection(direction));
                    sprite_laser.draw(batch);
                }

                // Walls
                if (tile.hasWall(Direction.NORTH)) {
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.NORTH));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if (tile.hasWall(Direction.SOUTH)) {
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.SOUTH));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if (tile.hasWall(Direction.EAST)) {
                    sprite_wall.setRotation(DirectionHelpers.rotationFromDirection(Direction.EAST));
                    sprite_wall.setPosition(x * tile_size, y * tile_size);
                    sprite_wall.draw(batch);
                }
                if (tile.hasWall(Direction.WEST)) {
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

        // Render actors
        for (Actor player : players) {
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
            //player.tileCheck(players);
        }
        batch.end();

        // TODO local multiplayer test
        if (phase.equals("draw")) {
            // add more cards, so we can play more fake rounds
            cs.initializeCardStack();
            players.get(0).drawCards();
            players.get(1).drawCards();
            players.get(2).drawCards();
            // load ui for player 1
            UI.loadUI(players.get(0));
            phase = "turns";
        } else if (phase.equals("turns")) {
            if (players.get(playerTurn % players.size()).fiveCardsInRegister()) {
                playerTurn++;
                if (playerTurn == players.size()) {
                    phase = "resolve";
                    playerTurn = 0;
                } else {
                    players.get(playerTurn).drawCards();
                    players.get(playerTurn).getRegister().clear();
                    players.get(playerTurn).initializeRegister();
                    UI.loadUI(players.get(playerTurn));
                }
            }
        } else if (phase.equals("resolve")) {
            timer += deltaTime;
            if (timer > 1) {
                if (action.waitingCards()) {
                    Player movingPlayer = action.playNextCard();
                    System.out.println();
                } else {
                    int round = action.getRound();
                    action.cardResolver(players);
                    action.updatePhase();
                    if (round != action.getRound()) {
                        phase = "draw";
                    }
                    for (Player player : players) {
                        player.tileCheck(new ArrayList<Actor>(players));
                    }
                }
                timer = 0;
            }

        }

        // Update camera
        updateCamera(deltaTime);
        //Rendering of the user interface
        UI.renderUI();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
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

    //region CAMERA MOVEMENT
    // java cant cast boolean to int, so we use int instead to simplify
    // 0 - false, 1 - true
    int wDown = 0;
    int sDown = 0;
    int aDown = 0;
    int dDown = 0;

    public void keyDown(int keyCode) {
        setKey(keyCode, 1);
    }

    public void keyUp(int keyCode) {
        setKey(keyCode, 0);
    }

    private void setKey(int keyCode, int down) {
        switch (keyCode) {
            case Input.Keys.W:
                wDown = down;
                break;
            case Input.Keys.S:
                sDown = down;
                break;
            case Input.Keys.A:
                aDown = down;
                break;
            case Input.Keys.D:
                dDown = down;
                break;
        }
    }

    private void updateCamera(float deltaTime) {
        float deltaX = (dDown - aDown) * deltaTime * translateSpeed;
        float deltaY = (wDown - sDown) * deltaTime * translateSpeed;
        camera.translate(deltaX, deltaY);
    }

    public void zoom(int scrollAmount) {
        camera.zoom += scrollAmount * scrollSpeed;
    }
    //endregion
}
