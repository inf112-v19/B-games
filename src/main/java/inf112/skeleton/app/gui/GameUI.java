package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Actor.Actor;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Player;
import java.util.ArrayList;



public class GameUI {

    private Stage buttonStage;
    private TextureAtlas atlas;
    private SpriteBatch uiBatch;
    private Action action;
    private Board board;

    public GameUI(TextureAtlas atlas, Action action, Board board) {
        this.atlas = atlas;
        this.action = action;
        this.board = board;
    }

    public void loadUI(Player spiller) {
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


        //MOVE_1_FORWARD button
        Player finalSpiller = spiller;
        movementButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.MOVE_1_FORWARD);
            }
        });

        //MOVE_2_FORWARD button
        movementButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.MOVE_2_FORWARD);
            }
        });

        //MOVE_3_FORWARD button
        movementButtons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.MOVE_3_FORWARD);
            }
        });

        //MOVE_1_BACKWARD button
        movementButtons.get(3).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.MOVE_1_BACKWARD);
            }
        });

        //ROTATE_90_LEFT button
        movementButtons.get(4).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.ROTATE_90_LEFT);
            }
        });

        //ROTATE_90_RIGHT button
        movementButtons.get(5).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.ROTATE_90_RIGHT);
            }
        });

        //ROTATE_180 button
        movementButtons.get(6).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.playCard(finalSpiller, CardType.ROTATE_180);
            }
        });

        //Make input active
        Gdx.input.setInputProcessor(buttonStage);
    }

    public void loadUI2(Player spiller) {
        buttonStage = new Stage(new ScreenViewport());

        //spiller.initializeHand();
        //spiller.drawCards();
        //spiller.initializeRegister();
        ArrayList<Card> givenCardsOnHand = (ArrayList<Card>) spiller.getCardsOnHand();

        TextureAtlas uiMovementAtlas = new TextureAtlas(Gdx.files.internal("assets/UI/Movement-Cards.atlas"));
        Skin mCardsSkin = new Skin(uiMovementAtlas);
        String[] cardsName = {"m1f", "m2f", "m3f", "m1b", "r90r", "r90l", "r180"};

        TextureAtlas uiDefaultAtlas = new TextureAtlas(Gdx.files.internal("assets/UI/uiskin.atlas"));
        Skin defaultSkin = new Skin(uiDefaultAtlas);


        //rootTable is the table where all other tables/ui goes into. rootTable size is the same as the gamescreen.
        Table rootTable = new Table();
        Table cardsRegister = new Table();
        Table cardsOptions = new Table();

        //Putting table size to match that of the screen.
        rootTable.setFillParent(true);
        rootTable.setDebug(true);

        //List containing the relevant skin for each button
        ArrayList<ImageButton.ImageButtonStyle> mCardsStyle = new ArrayList<>();

        for (int i = 0; i < givenCardsOnHand.size(); i++) {
            System.out.println(givenCardsOnHand.get(i).getType());
            ImageButton.ImageButtonStyle singleCard = new ImageButton.ImageButtonStyle();
            String cardIdentifier = whichStyle(givenCardsOnHand.get(i));
            singleCard.up = mCardsSkin.getDrawable(cardIdentifier);
            mCardsStyle.add(singleCard);

            ImageButton ibCardOption = new ImageButton(mCardsStyle.get(i));
            cardsOptions.add(ibCardOption).width(50).height(75).pad(1);
            if (i >= 1 && i % 2-1 == 0) {
                cardsOptions.row();
            }
        }

        SnapshotArray<com.badlogic.gdx.scenes.scene2d.Actor> ImageButtonsFromTable = cardsOptions.getChildren();

        for (int i = 0; i < ImageButtonsFromTable.size; i++) {
            int finalI = i;
            ImageButtonsFromTable.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //Create a new Imagebutton to go to register and add it to the register table
                    ImageButton ibCardRegister = new ImageButton(mCardsStyle.get(finalI));
                    cardsRegister.add(ibCardRegister).width(75).height(100).pad(1);


                    try {
                        // TODO
                        spiller.addCardToRegister(new Card(CardType.MOVE_1_FORWARD, 200, true));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        //cardsRegister.add(m1f).height(100).width(75);

        /*  rootTable.center() here will change the position of the objects inside of rootTable, but not rootTable itself.
            cardsTable.left() will then put the textbuttons to the left of the size of cardsTable logical table. Right now
            it does nothing since the size of cardsTable is the same as the button inside it.
        */

        //rootTable.;
        rootTable.add(cardsOptions).expandX().left();
        rootTable.row();
        rootTable.add(cardsRegister).expandY().bottom();

        buttonStage.addActor(rootTable);
        Gdx.input.setInputProcessor(buttonStage);



    }

    public void loadUI3(Player spiller) {

        buttonStage = new Stage(new ScreenViewport());

        //Creating a ninepatch texture for button. The magic with ninepatch is that it will scale the button after how long the string is.
        NinePatch buttonTexture = atlas.createPatch("button_up");
        buttonTexture.setColor(Color.BLUE);
        //Each TextButton object needs as input: an string and a TextButtonStyle object(simply styles the button).
        TextButton.TextButtonStyle movementButtonStyle = new TextButton.TextButtonStyle();
        movementButtonStyle.font = new BitmapFont();
        movementButtonStyle.up = new NinePatchDrawable(buttonTexture);

        //An array of all cardtypes
        CardType[] allMovementCards = CardType.values();
        //Arraylist containing register buttons
        ArrayList<TextButton> registerButtons = new ArrayList<TextButton>();
        //Arraylist containing card buttons
        ArrayList<TextButton> cardButtons = new ArrayList<TextButton>();

        //Create graphical buttons for registers
        for (int i = 0; i < 5; i++) {
            String registerName = "Register " + (i+1) + "\n";
            TextButton register = null;
            if(spiller.getRegister().get(i) == null) {
                register = new TextButton(registerName + "Empty", movementButtonStyle);
            }
            else {
                register = new TextButton(registerName + "\n" +
                        spiller.getRegister().get(i).getType().name() + "\n" +
                        spiller.getRegister().get(i).getPriority() + "\n" +
                        spiller.getRegister().get(i).getPriority(), movementButtonStyle);
            }
            register.setPosition(0, 630 - (90*i));
            registerButtons.add(register);
            buttonStage.addActor(register);
        }
        //Create graphical buttons for cards in hand
        for (int i = 0; i < 9; i++) {
            String cardName = "Card " + (i+1);
            TextButton card = null;
            if(spiller.getCardsOnHand().get(i) == null) {
                card  = new TextButton(cardName + "Empty", movementButtonStyle);
            }
            else {
                card = new TextButton(cardName + "\nType: " +
                        spiller.getCardsOnHand().get(i).getType().name() + "\nPriority: " +
                        spiller.getCardsOnHand().get(i).getPriority(), movementButtonStyle);
            }
            card.setPosition(800, 650 - (80*i));
            cardButtons.add(card);
            buttonStage.addActor(card);
        }

        //Create graphical buttons for cards in hand
        TextButton powerDown = new TextButton("Power Down", movementButtonStyle);
        powerDown.setPosition(170, 70);
        buttonStage.addActor(powerDown);

        TextButton confirmAction = new TextButton("Confirm", movementButtonStyle);
        confirmAction.setPosition(710, 70);
        buttonStage.addActor(confirmAction);

        //Click register 1
        registerButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click register 2
        registerButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click register 3
        registerButtons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click register 4
        registerButtons.get(3).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click register 5
        registerButtons.get(4).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 1 in hand
        cardButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 2 in hand
        cardButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 3 in hand
        cardButtons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 4 in hand
        cardButtons.get(3).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 5 in hand
        cardButtons.get(4).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 6 in hand
        cardButtons.get(5).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 7 in hand
        cardButtons.get(6).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 8 in hand
        cardButtons.get(7).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click card 9 in hand
        cardButtons.get(8).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });

        //Click PowerDown
        powerDown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(spiller.getPowerdown()) {
                    spiller.setPowerDown(false);
                }
                else{
                    spiller.setPowerDown(true);
                }
            }
        });

        //Click Confirm
        confirmAction.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                spiller.confirmAction();
            }
        });

        //Make input active
        Gdx.input.setInputProcessor(buttonStage);
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
    //Converts from Card Enums to String keyword for the UI atlas
    private String whichStyle(Card card) {

        switch (card.getType()) {

            case MOVE_1_FORWARD:
                return "m1f";

            case MOVE_2_FORWARD:
                return "m2f";

            case MOVE_3_FORWARD:
                return "m3f";

            case MOVE_1_BACKWARD:
                return "m1b";

            case ROTATE_180:
                return "r180";

            case ROTATE_90_LEFT:
                return "r90l";

            case ROTATE_90_RIGHT:
                return "r90r";

            default:
                return "Invalid card";
        }

    }

    public void resizeUI(int width, int height) {
        //Resizing the button click area when resizing whole game
        buttonStage.getViewport().update(width, height, true);

    }

    public void dispose() {
        buttonStage.dispose();
    }





}
