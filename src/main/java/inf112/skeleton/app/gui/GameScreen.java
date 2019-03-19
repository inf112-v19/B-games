package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.*;

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

    //Stages
    private Stage buttonStage;

    public GameScreen(RoboRally game) {
        this.game = game;
        atlas = new TextureAtlas(Gdx.files.internal("assets/atlas/test.atlas"));

        buttonStage = new Stage(new ScreenViewport());

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
        players.add(new Actor(5, 5, Color.RED, board));
        players.add(new Actor(5, 6, Color.BLUE, board));
        players.add(new Actor(5, 7, Color.GREEN, board));

        //Creating a ninepatch texture for button. The magic with ninepatch is that it will scale the button after how long the string is.
        NinePatch buttonTexture = atlas.createPatch("button_up");
        buttonTexture.setColor(Color.BLUE);
        //Each TextButton object needs as input: an string and a TextButtonStyle object(simply styles the button).
        TextButton.TextButtonStyle movementButtonStyle = new TextButton.TextButtonStyle();
        movementButtonStyle.font = new BitmapFont();
        movementButtonStyle.up = new NinePatchDrawable(buttonTexture);
        //The ArrayList containing our buttons
        ArrayList<TextButton> movementButtons = new ArrayList<TextButton>();
        //An array of all cardtypes
        CardType[] allMovementCards = CardType.values();
        //Create as many buttons as there are cards, set their position on screen, add them to movementButton array and to stage
        for (int i = 0; i < allMovementCards.length; i++) {
            TextButton tb = new TextButton(allMovementCards[i].toString(), movementButtonStyle);
            tb.setPosition(0, tb.getHeight() * i);
            movementButtons.add(tb);
            buttonStage.addActor(tb);
        }

        //Movement will be applied to robot at index 0(red robot)
        Actor selectedPlayer = players.get(0);
        //MOVE_1_FORWARD button
        movementButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.MOVE_1_FORWARD);
            }
        });

        //MOVE_2_FORWARD button
        movementButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.MOVE_2_FORWARD);
            }
        });

        //MOVE_3_FORWARD button
        movementButtons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.MOVE_3_FORWARD);
            }
        });

        //MOVE_1_BACKWARD button
        movementButtons.get(3).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.MOVE_1_BACKWARD);
            }
        });

        //ROTATE_90_LEFT button
        movementButtons.get(4).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.ROTATE_90_LEFT);
            }
        });

        //ROTATE_90_RIGHT button
        movementButtons.get(5).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.ROTATE_90_RIGHT);
            }
        });

        //ROTATE_180 button
        movementButtons.get(6).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(selectedPlayer, CardType.ROTATE_180);
            }
        });

        //Make input active
        Gdx.input.setInputProcessor(buttonStage);

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
                            currentFrame.setRotation(90);
                            break;
                        case SOUTH:
                            currentFrame.setRotation(180);
                            break;
                        case WEST:
                            currentFrame.setRotation(270);
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
        }
        batch.end();

        //Updates and draws every actor in the stage. Actors here are the buttons.
        buttonStage.act(Gdx.graphics.getDeltaTime());
        buttonStage.draw();
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
