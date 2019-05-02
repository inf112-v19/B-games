package inf112.skeleton.app.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.Action.Action;
import inf112.skeleton.app.Actor.Player;
import java.util.ArrayList;



public class GameUI {

    private Stage buttonStage;
    private TextureAtlas atlas;
    TextureAtlas uiMovementAtlas;
    private Action action;

    public GameUI(TextureAtlas atlas, Action action) {
        this.atlas = atlas;
        this.action = action;
        uiMovementAtlas = new TextureAtlas(Gdx.files.internal("assets/UI/Movement-Cards.atlas"));
    }


    /**
     * Loads the user interface for {@code player}.
     *
     * @param player
     */
    public void loadUI(Player player) {
        buttonStage = new Stage(new ScreenViewport());

        //Skin for different buttons
        TextureAtlas uiDefaultAtlas = new TextureAtlas(Gdx.files.internal("assets/UI/uiskin.atlas"));
        Skin defaultSkin = new Skin(uiDefaultAtlas);


        //rootTable is the table where all other tables/ui goes into. rootTable size is the same as the gamescreen.
        Table rootTable = new Table();
        Table cardsRegister = new Table();
        Table cardsOptions = new Table();

        //Putting table size to match that of the screen.
        rootTable.setFillParent(true);
        rootTable.setDebug(true);

        ArrayList<Card> givenCardsOnHand = (ArrayList<Card>) player.getCardsOnHand();

        for (int i = 0; i < givenCardsOnHand.size(); i++) {
            Card currentCard = givenCardsOnHand.get(i);
            ImageButton btn = getButton(currentCard);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    player.addCardToRegister(currentCard);
                    player.getCardsOnHand().remove(currentCard);
                    // Reload UI, should probably find some better way to do this
                    dispose(); // Not sure if GC handles this or not, so dispose to be sure.
                    loadUI(player);
                }
            });
            cardsOptions.add(btn).width(75).height(100).pad(1);
            int rowWidth = 2;
            if (i % rowWidth == rowWidth - 1) {
                cardsOptions.row();
            }
        }

        for (int i = 0; i < player.getRegister().size(); i++) {
            Card currentCard = player.getRegister().get(i);
            //if (currentCard == null) continue; // TODO Remove this when register list is fixed
            if (currentCard == null) {
                Image dummy = new Image(defaultSkin, "default-round-large");
                cardsRegister.add(dummy).width(75).height(100).pad(1);

                continue;
            }
            ImageButton btn = getButton(currentCard);
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    player.getRegister().remove(currentCard);
                    player.getCardsOnHand().add(currentCard);
                    player.getRegister().add(null); // ...
                    // Reload UI, should probably find some better way to do this
                    dispose(); // Not sure if GC handles this or not, so dispose to be sure.
                    loadUI(player);
                }
            });
            cardsRegister.add(btn).width(75).height(100).pad(1);
        }


        /*  rootTable.center() here will change the position of the objects inside of rootTable, but not rootTable itself.
            cardsTable.left() will then put the textbuttons to the left of the size of cardsTable logical table. Right now
            it does nothing since the size of cardsTable is the same as the button inside it.
        */

        //rootTable.;
        rootTable.add(cardsOptions).expandX().left();
        rootTable.row();
        rootTable.add(cardsRegister).expandY().bottom();
        rootTable.row();


        //Style for the lockRegister button and who is playing button
        TextButton.TextButtonStyle lockRegisterStyle = new TextButton.TextButtonStyle();
        lockRegisterStyle.up =  defaultSkin.getDrawable("default-round-large");
        lockRegisterStyle.font = new BitmapFont();

        //Code for Lock Register button
        TextButton lockRegister = new TextButton("Lock Register", lockRegisterStyle);
        lockRegister.setColor(Color.RED);
        lockRegister.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (player.fiveCardsInRegister()) {
                    lockRegister.setColor(Color.GREEN);
                    System.out.println(player.getHP());
                }
            }
        });
        cardsRegister.add(lockRegister).padLeft(35).height(100);

        //Creating Label and its style. For HPText and isPlaying.
        Label.LabelStyle HPTextStyle = new Label.LabelStyle();
        HPTextStyle.font = new BitmapFont();
        Label HPText = new Label(String.valueOf(player.getHP()), HPTextStyle);
        HPText.setPosition(850, 700, Align.right);

        //Creating heart image, its position is based on the position of HPText
        Texture heartImage = new Texture(Gdx.files.internal("assets/UI/hjerte.png"));
        Image heart = new Image(heartImage);
        heart.setHeight(50);
        heart.setWidth(50);
        heart.setPosition(HPText.getX() + 15, HPText.getY() - 17);

        //Creating a visual indicator of which player is playing currently
        TextButton box = new TextButton("", lockRegisterStyle);
        box.setColor(player.getColor());
        box.setHeight(25);
        box.setWidth(25);
        box.setPosition(840, 630);

        Label isPlaying = new Label("Is currently playing!" , HPTextStyle);
        isPlaying.setPosition(box.getX() + 30, box.getY() + 4);

        buttonStage.addActor(box);
        buttonStage.addActor(isPlaying);
        buttonStage.addActor(heart);
        buttonStage.addActor(HPText);

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


    private ImageButton getButton(Card card) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        SpriteDrawable sd = new SpriteDrawable(uiMovementAtlas.createSprite(whichStyle(card)));
        style.up = sd;
        ImageButton btn = new ImageButton(style);
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        btn.add(new Label("P: " + card.getPriority(), ls)).padTop(78);
        return btn;
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
