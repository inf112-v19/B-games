package inf112.skeleton.app;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.sun.xml.internal.bind.v2.TODO;

public class Actor implements IActor {

    private int PlayerId;
    private int xPos;
    private int yPos;
    private int xPosRestorationPoint;
    private int yPosRestorationPoint;
    private Color color;
    private int robotLives;
    private int robotHP;
    private int dockingAssignment; //* set randomly at start of game based
                                   // on number of players, determines
                                   // starting position and
                                   // priority for actions not covered by cards
    private List<Card> cardsOnHand;
    private List<Card> cardsInRegister;
    private int flagsVisited;
    private boolean powerDownRobot;
    private boolean confirmAction;
    public Direction direction = Direction.NORTH;
    private int moveDistance = 50;

    public Actor(int x, int y, Color color){
        this.xPos = x;
        this.yPos = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int getX(){
        return this.xPos;
    }

    @Override
    public int getY(){
        return this.yPos;
    }

    //width of actor image
    public int getTextureWidth(){
        return 50;
    }

    //height of actor image
    public int getTextureHeight(){
        return 50;
    }

    @Override
    public void move(Direction direction) {
        switch (direction){
            case NORTH:
                this.yPos += 1;
                break;
            case EAST:
                this.xPos += 1;
                break;
            case SOUTH:
                this.yPos -= 1;
                break;
            case WEST:
                this.xPos -= 1;
                break;
        }
    }

    @Override
    public int getMoveDistance(){
        return moveDistance;
    }

    public void tileCheck(Tile tile){
        // TODO Checks tile at xPos, yPos and calls appropriate
        //  method
    }

    public void drawCards(){
        // TODO Draw cards from CardStack based on RobotHP
        //  and add them to CardsOnHand
    }

    public void addCardToRegister(Card card){
        // TODO Move card from CardsOnHand and put it in cardsInRegister
    }

    public void addCardToHand(Card card){
        // TODO Move card from CardsInRegister and back into CardsOnHand
    }

    public void putCardsBackIntoCardStack(List<Card> cardsOnHand, List<Card> cardsInRegister){
        // TODO Collect all cards that aren't locked and
        //  put them back into card stack. Maybe be best to
        //  put this in Action class as it will
        //  be done by all players at end of turn
    }

    public void lockCardsInRegisters(){
        // TODO Locks cards in register. If robotHP at:
        //  - 5 lock register 5
        //  - 4 lock register 5 & 4
        //  - 3 lock register 5, 4 & 3
        //  - 2 lock register 5, 4, 3 & 2
        //  - 1 lock all registers
    }

    public void updateRestorationPoint(){
        /*
        * TODO tileCheck(); Update xPosRestorationPoint and yPosRestorationPoint
        *  if robot lands on a flag or wrench tile.
        */
    }

    public void restoreRobot(){
        /*
        * TODO If robot loses a life from falling down a hole
        *  or robotHP falls to 0 from receiving damage set xPos and YPos
        *  to xPosRestorationPoint and yPosRestorationPoint.
        *  robotHP starts at 8 player chooses which direction the robot will face
        */
    }

    public void receiveDamage(){
        /*
         * TODO Decrements robotHP by 1
         */
    }

    public void repairDamage(){
        /*
         * TODO Increments robotHP by 1
         */
    }

    public void robotDestroyed(){
        /*
         * TODO Decrements robotLives by 1. Calls restoreRobot()
         */
    }

    public void powerDown(){
        /*
         * TODO Robot remain inactive for 1 turn, restores all HP
         *  at end of turn (after repair but before cards are collected)
         */
    }


    public void dockingAssignment(Integer Dock){
        /*
         * TODO update dockingAssignment. Will have to receive
         *  a random integer from Action class based on number of
         *  players
         */
    }

    public void flagVisited(Integer FlagNumber){
        /*
         * TODO If FlagNumber is one higher
         *  than current FlagsVisited increment by 1. Wins the game
         *  when FlagsVisited reaches ??
         */
    }

    public static void main(String[] args) {
        /*
         * TODO Main method that runs between every turn.
         *  Reads input from players and waits for
         *  confirmAction to be equal to true to execute actions
         */

    }
}
