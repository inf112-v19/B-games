package inf112.skeleton.app;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Actor implements IActor {

    private int xPos;
    private int yPos;
    private Color color;

    public Color getColor() {
        return color;
    }

    private enum direction {NORTH, SOUTH, EAST, WEST};
    private int moveDistance = 50;

    public Actor(int x, int y, Color color){
        this.xPos = x;
        this.yPos = y;
        this.color = color;
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
    public void move(Enum direction) {

    }


    @Override
    public int getMoveDistance(){
        return moveDistance;
    }


    public void update() {
        badMovementTest();
    }

    //very inefficient movement test
    private void badMovementTest() {
        int randInt = new Random().nextInt(100);
        switch (randInt){
            case 0:
                this.xPos += 1;
                break;
            case 1:
                this.xPos -= 1;
                break;
            case 2:
                this.yPos += 1;
                break;
            case 3:
                this.yPos -= 1;
                break;
                default:
                    break;
        }



        /*
        if(randInt == 1) {
            int randomDirection = (int) Math.ceil(Math.random() * 4);
            if (randomDirection == 1) {
                goNorth();
            } else if (randomDirection == 2) {
                goSouth();
            } else if (randomDirection == 3) {
                goEast();
            } else {
                goWest();
            }
        }*/
    }

    @Override
    public void goNorth(){
        yPos += moveDistance;
    }

    @Override
    public void goSouth(){
        yPos -= moveDistance;
    }

    @Override
    public void goWest(){
        xPos -= moveDistance;
    }

    @Override
    public void goEast(){
        xPos += moveDistance;
    }
}
