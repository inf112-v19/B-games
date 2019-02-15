package inf112.skeleton.app.gui;

import com.badlogic.gdx.Game;

public class RoboRally extends Game {
    static GameScreen game_screen;

    enum GameState { MENU, GAME}
    GameState state = GameState.MENU;

    @Override
    public void create(){
        setScreen(new MenuScreen(this));
    }

    public void startGame(){
        state = GameState.GAME;
        game_screen = new GameScreen(this);
        setScreen(game_screen);
    }
}
