package inf112.skeleton.app;


import com.badlogic.gdx.graphics.Color;
import com.sun.xml.internal.bind.v2.TODO;

public class Actor implements IActor {


    private int xPos;
    private int yPos;
    private int xPosArchivePoint;
    private int yPosArchivePoint;
    private Color color;
    private int robotLives;
    private int robotHP;
    private int dockingAssignment; //* set randomly at start of game based
                                   // on number of players, determines
                                   // starting position and
                                   // priority for actions not covered by cards
    private int flagsVisited;
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

    public void setx(int x){
        this.xPos = x;
    }
    public void setY(int y){
         this.yPos = y;
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

    public void updateRestorationPoint(){
        /*
        * TODO tileCheck(); Update xPosArchivePoint and yPosArchivePoint
        *  if robot lands on a flag or wrench tile.
        */
    }

    public void restoreRobot(){
        /*
        * TODO If robot loses a life from falling down a hole
        *  or robotHP falls to 0 from receiving damage set xPos and YPos
        *  to xPosArchivePoint and yPosArchivePoint.
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

    public void dockingAssignment(Integer Dock){
        /*
         * TODO update dockingAssignment. Will have to receive
         *  a random integer from Action class based on number of
         *  players. Maybe use PlayerID from Player Class. If so
         *  have Player class set xPos and yPos?
         */
    }

    public void flagVisited(Integer FlagNumber){
        /*
         * TODO If FlagNumber is one higher
         *  than current FlagsVisited increment by 1. Wins the game
         *  when FlagsVisited reaches ??
         */
    }
}
