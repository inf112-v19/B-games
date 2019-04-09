package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.Action.Action;
import java.util.ArrayList;


public class GameUI {

    private Stage buttonStage;
    private TextureAtlas atlas;
    private ArrayList<Actor> players;
    private Action action;

    public GameUI(TextureAtlas atlas, ArrayList<Actor> players, Action action) {
        this.atlas = atlas;
        this.players = players;
        this.action = action;
    }

    public void loadUI() {
        buttonStage = new Stage(new ScreenViewport());

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

    public void loadUI2() {
        buttonStage = new Stage(new ScreenViewport());
        Table mCards = new Table();
        //Putting table size to match that of the screen.
        mCards.setFillParent(true);
        mCards.setDebug(true);
        Skin skin = new Skin(Gdx.files.internal("assets/atlas/uiskin.json"));
        //mCards.setWidth(buttonStage.getWidth());
        mCards.align(Align.bottom + 20);

        //mCards.setPosition(500, 500);
        TextButton b = new TextButton("Hallo", skin);
        TextButton c = new TextButton("Boooooooooo", skin);
        mCards.add(b);
        mCards.row();
        mCards.add(c);


        buttonStage.addActor(mCards);

    }

    public void renderUI() {
        //Updates and draws every actor in the stage. Actors here are the buttons.
        buttonStage.act(Gdx.graphics.getDeltaTime());
        buttonStage.draw();

    }

    public void renderUI2() {
        //Updates and draws every actor in the stage. Actors here are the buttons.
        buttonStage.act(Gdx.graphics.getDeltaTime());
        buttonStage.draw();

    }

    public void resizeUI(int width, int height) {
        //Resizing the button click area when resizing whole game
        buttonStage.getViewport().update(width, height, true);

    }





}
