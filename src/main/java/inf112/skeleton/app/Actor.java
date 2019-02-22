package inf112.skeleton.app;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Actor implements IActor {

    private int xPos;
    private int yPos;
    private Color color;
    public Direction direction = Direction.NORTH;

    public Color getColor() {
        return color;
    }

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

}
