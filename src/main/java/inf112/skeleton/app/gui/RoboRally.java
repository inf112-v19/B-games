package inf112.skeleton.app.gui;

import com.badlogic.gdx.Game;

public class RoboRally extends Game {
    GameScreen game_screen;
    @Override
    public void create(){

        game_screen = new GameScreen(this);

        setScreen(game_screen);
    }

}
