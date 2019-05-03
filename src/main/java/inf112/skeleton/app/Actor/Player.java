package inf112.skeleton.app.Actor;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor implements IPlayer {
    private int playerId;
    private CardStack cardStack;
    private List<Card> cardsOnHand;
    private List<Card> cardsInRegister;
    private boolean powerDownRobot;
    private boolean confirmAction;

    private Color color;

    public Color getColor() {
        return color;
    }
    public Player(int x, int y, Color color, Board board,
                  int playerId, int dockingAssignment, CardStack cardStack, boolean confirmAction) {
        super(x, y, color, board, dockingAssignment);
        this.playerId = playerId;
        this.cardStack = cardStack;
        this.cardsOnHand = new ArrayList<>();
        this.color = color;
        //initializeHand();
        this.cardsInRegister = new ArrayList<>();
        initializeRegister();
        this.confirmAction = confirmAction;

    }

    public void initializeHand() {
        for (int i = 0; i < 9; i++) {
            cardsOnHand.add(null);
        }
    }

    public void initializeRegister() {
        for (int i = 0; i < 5; i++) {
            cardsInRegister.add(null);
        }
    }

    // Draw cards from CardStack based on RobotHP and add them to CardsOnHand
    public void drawCards() {
        cardsOnHand.clear();
        for (int i = 0; i < (getHP() - 1); i++) {
            cardsOnHand.add(i, cardStack.getCardFromStack());
        }
        /*
        ArrayList<Integer> registersToBeLocked = lockedRegistersNumbers();

        for (int i = 0; i< (getHP()-1); i++) {
            cardsOnHand.add(i, cardStack.getCardFromStack());
            cardsOnHand.remove((getHP() - 1));
            if (!registersToBeLocked.isEmpty()) {
                int number = registersToBeLocked.get(0);
                try {

                    addCardToRegister(0, number, false);
                }
                catch (Exception e){
                    System.out.print(e);
                }
                registersToBeLocked.remove(0);
            }
            lockCardsInRegisters();
        }
        */

    }

    public void addCardToRegister(Card card) {
        // TODO R: hacky way to add cards
        //cardsOnHand.remove(card);
        System.out.println(card.getType());
        cardsInRegister.remove(0);
        cardsInRegister.add(card);
    }


    // Move card from CardsOnHand and put it in cardsInRegister
    public void addCardToRegister(int from, int to, boolean player) throws Exception {
        if (to >= 0 && to <= 5) {
            if (cardsInRegister.get(to) == null) {
                if (from >= 0 && from <= 9) {
                    if (cardsOnHand.get(from) instanceof Card) {
                        Card card = cardsOnHand.remove(from);
                        cardsOnHand.add(null);
                        cardsInRegister.remove(to);
                        cardsInRegister.add(to, card);
                        return;
                    } else {
                        throw new Exception("No card in your hand at that number");
                    }
                } else {
                    throw new Exception("Number for hand needs to be between 1 and 9.");
                }
            } else {
                if (player) {
                    throw new Exception("That register number is not empty");
                } else {
                }
            }
        } else {
            throw new IllegalArgumentException("Number for register needs to be between 1 and 5. Was: " + to);
        }
    }

    public void addCardToHand(int indexFrom) throws Exception {
        if (indexFrom >= 0 && indexFrom <= 5) {
            if (cardsInRegister.get(indexFrom) instanceof Card) {
                if (cardsInRegister.get(indexFrom).getUnlockedStatus() == true) {
                    Card card = cardsInRegister.remove(indexFrom);
                    for (int i = 0; i < cardsOnHand.size(); i++) {
                        if (cardsOnHand.get(i) == null) {
                            cardsOnHand.add(i, card);
                            cardsOnHand.remove(i + 1);
                            break;
                        }
                    }
                } else {
                    throw new Exception("That register number is locked.");
                }
            } else {
                throw new Exception("That register number is empty.");
            }
        } else {
            throw new IllegalArgumentException("Number for register needs to be between 1 and 5. Was: " + indexFrom);
        }
    }

    public void lockCardsInRegisters() {

        ArrayList<Integer> registersToBeLocked = lockedRegistersNumbers();

        if (registersToBeLocked.isEmpty()) {
            for (int i = 0; i < cardsInRegister.size(); i++) {
                if (cardsInRegister.get(i) instanceof Card) {
                    cardsInRegister.get(i).setUnlocked();
                }
            }
        } else {
            for (int i = 0; i < registersToBeLocked.size(); i++) {
                int number = registersToBeLocked.get(i);
                if (cardsInRegister.get(number) instanceof Card) {
                    cardsInRegister.get(number).setLocked();
                } else {
                    try {
                        addCardToRegister(0, number, false);
                        cardsInRegister.get(number).setLocked();
                    } catch (Exception e) {
                        System.out.println("No card in hand.");
                    }
                }
            }
        }
    }

    public boolean getPowerdown(){
        return powerDownRobot;
    }

    public List<Card> getRegister() {
        return cardsInRegister;
    }

    public List<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public void putCardsBackIntoCardStack() {

        for (int i = 0; i < cardsOnHand.size(); i++) {
            if (cardsOnHand.get(i) instanceof Card) {
                cardStack.addCardToStack(cardsOnHand.remove(i));
                cardsOnHand.add(i, null);
            }
        }
        for (int i = 0; i < cardsInRegister.size(); i++) {
            if (cardsInRegister.get(i) instanceof Card) {
                if (cardsInRegister.get(i).getUnlockedStatus() == true) {
                    cardStack.addCardToStack(cardsInRegister.remove(i));
                    cardsInRegister.add(i, null);
                }
            }
        }
    }

    public void setPowerDown(boolean bool) {
        powerDownRobot = bool;
    }

    public void setConfirmAction(boolean bool) {
        confirmAction = bool;
    }

    public boolean getConfirmAction() {
        return confirmAction;
    }

    public void confirmAction() {

        if (powerDownRobot) {
            robotPowerDown();
            confirmAction = true;
        } else if (fiveCardsInRegister()) {
            confirmAction = true;
        } else {
            confirmAction = false;
            System.out.println("You need to either powerdown or place 5 cards in register!");
        }
    }

    public boolean fiveCardsInRegister() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            if (!(cardsInRegister.get(i) == null)) {
                counter++;
            }
        }
        return counter == 5;
    }

    public ArrayList<Integer> lockedRegistersNumbers() {
        ArrayList<Integer> lockedRegisterNumbers = new ArrayList<>();
        if (getHP() == 5) {
            lockedRegisterNumbers.add(4);
        } else if (getHP() == 4) {
            lockedRegisterNumbers.add(4);
            lockedRegisterNumbers.add(3);
        } else if (getHP() == 3) {
            lockedRegisterNumbers.add(4);
            lockedRegisterNumbers.add(3);
            lockedRegisterNumbers.add(2);
        }
        if (getHP() == 2) {
            lockedRegisterNumbers.add(4);
            lockedRegisterNumbers.add(3);
            lockedRegisterNumbers.add(2);
            lockedRegisterNumbers.add(1);
        } else if (getHP() == 1) {
            lockedRegisterNumbers.add(4);
            lockedRegisterNumbers.add(3);
            lockedRegisterNumbers.add(2);
            lockedRegisterNumbers.add(1);
            lockedRegisterNumbers.add(0);
        }
        return lockedRegisterNumbers;
    }
    /*
    public void playerTurn() throws Exception {
        drawCards();

        // will have to be based on text input till GUI for player interface is implemented
        while (!confirmAction) {

            System.out.println("Cards in register:");
            for (int i = 0; i < cardsInRegister.size(); i++){
                System.out.println(cardsInRegister.get(i));
            }

            System.out.println("Cards on hand:");
            for (int i = 0; i < cardsOnHand.size(); i++){
                System.out.println(cardsOnHand.get(i));
            }

            Scanner reader = new Scanner(System.in);

            System.out.println("Please specify action:");
            System.out.println("1: Add a card from hand to register");
            System.out.println("2: Add a card from register to hand");
            System.out.println("3: Powerdown");
            System.out.println("4: Confirm action");

            int choice = reader.nextInt();

            if(choice == 1){
                System.out.println("Move which card # to which register #?");
                int from = reader.nextInt();
                int to = reader.nextInt();

                addCardToRegister(from, to, true);
            }
            else if(choice == 2){
                System.out.println("Move which card from register # to hand?");
                int index = reader.nextInt();
                addCardToHand(index);
            }
            else if(choice == 3){
                setPowerDown(true);
            }
            else if(choice == 4){
                confirmAction();
            }
        }
    }
     */
}
