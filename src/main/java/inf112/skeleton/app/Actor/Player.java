package inf112.skeleton.app.Actor;

import com.badlogic.gdx.graphics.Color;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Actor implements IPlayer {
    private Board board;
    private int playerId;
    private CardStack cardStack;
    private List<Card> cardsOnHand;
    private List<Card> cardsInRegister;
    private boolean powerDownRobot;
    private boolean confirmAction;

    public Player(int x, int y, Color color, Board board, int playerId, CardStack cardStack, boolean confirmAction){
        super(x, y, color, board, 0); // TODO: docking 0
        this.playerId = playerId;
        this.cardStack = cardStack;
        this.cardsOnHand = new ArrayList<>();
        initializeHand();
        this.cardsInRegister = new ArrayList<>();
        initializeRegister();
        this.confirmAction = confirmAction;

    }

    public void initializeHand(){
        for (int i=0; i<9; i++ ){
            cardsOnHand.add(null);
        }
    }

    public void initializeRegister(){
        for (int i=0; i<5; i++ ){
            cardsInRegister.add(null);
        }
    }

    // Draw cards from CardStack based on RobotHP and add them to CardsOnHand
    public void drawCards(){
        for (int i = 0; i< (getHP()-1); i++){ // replace robotHP with getRobotHP() once Actor is complete

            cardsOnHand.add(i, cardStack.getCardFromStack());
            cardsOnHand.remove((getHP()-1));
        }
    }

    // Move card from CardsOnHand and put it in cardsInRegister
    public void addCardToRegister(int from, int to) throws Exception {
        if (to > 0 && to <= 5) {
            if (cardsInRegister.get(to) == null) {
                if (from >= 1 && from <= 9) {
                    if (cardsOnHand.get(from) instanceof Card) {
                        Card card = cardsOnHand.remove(from-1);
                        cardsOnHand.add(null);
                        cardsInRegister.remove(to-1);
                        cardsInRegister.add(to-1, card);
                        return;
                    } else {
                        throw new Exception("No card in your hand at that number");
                    }
                } else {
                    throw new Exception("Number for hand needs to be between 1 and 9.");
                }
            } else{
                throw new Exception("That register number is not empty");
            }
        }
        else {
            throw new Exception("Number for register needs to be between 1 and 5.");
        }
    }

    public void addCardToHand(int indexFrom){
        // TODO Move card from CardsInRegister and back into CardsOnHand
        Card card = cardsInRegister.remove(indexFrom);
        for (int i = 1; i< cardsOnHand.size(); i++){
            if (cardsOnHand.get(i) == null) {
                cardsOnHand.add(i, card);
                cardsOnHand.remove(i+1);
                break;
            }
        }

    }

    public void lockCardsInRegisters(int robotHP){
        // TODO Locks cards in register. If robotHP at:
        //  - 5 lock register 5
        //  - 4 lock register 5 & 4
        //  - 3 lock register 5, 4 & 3
        //  - 2 lock register 5, 4, 3 & 2
        //  - 1 lock all registers
        // if no cards in register first card gets put into locked register

        if (robotHP <6){
            cardsInRegister.get(4).setLocked();
        }
        if (robotHP <5){
            cardsInRegister.get(3).setLocked();
        }
        if (robotHP <4){
            cardsInRegister.get(2).setLocked();
        }
        if (robotHP < 3){
            cardsInRegister.get(1).setLocked();
        }
        if (robotHP < 2){
            cardsInRegister.get(0).setLocked();
        }

    }

    public List<Card> getRegister (){
        return cardsInRegister;
    }

    public List<Card> getCardsOnHand (){
        return cardsOnHand;
    }

    public void putCardsBackIntoCardStack(List<Card> cardsOnHand, List<Card> cardsInRegister){
        // TODO Collect all cards that aren't locked and
        //  put them back into card stack. Maybe be best to
        //  put this in Action class as it will
        //  be done by all players at end of turn

        for (int i = 0; i < cardsOnHand.size(); i++) {
            cardStack.addCardToStack(cardsOnHand.remove(i));
        }
        for (int i = 0; i < cardsInRegister.size(); i++){
            cardStack.addCardToStack(cardsInRegister.remove(i));
        }
    }

    public void powerDown(){
        /*
         * TODO Robot remain inactive for 1 turn, restores all HP
         *  at end of turn (after repair but before cards are collected)
         */
        powerDownRobot = true;
    }

    public void ConfirmAction(){
        /*
         * TODO check if a valid action has been performed and set
         *  confirmAction to true
         */
        if (powerDownRobot){
            /*
             * TODO tell robot to powerdown
             */
            confirmAction = true;
        }
        else if(FiveCardsInRegister()){
            confirmAction = true;
        }
        else {
            System.out.println("You need to either powerdown or place 5 cards in register!");
        }


    }

    public boolean FiveCardsInRegister() {

        int counter = 0;
        for (int i = 0; i < 5; i++){
            if (!(cardsInRegister.get(i) == null)){
                counter++;
            }
        }

        return counter == 5;
    }

    public void playerTurn() throws Exception {
        /*
         * TODO Main method that runs between every turn.
         *  Reads input from players and waits for
         *  confirmAction to be equal to true to execute actions
         */

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

                addCardToRegister(from, to);
            }
            else if(choice == 2){
                System.out.println("Move which card from register # to hand?");
                int index = reader.nextInt();
                addCardToHand(index);
            }
            else if(choice == 3){
                powerDown();
            }
            else if(choice == 4){
                ConfirmAction();
            }
        }
    }
}
