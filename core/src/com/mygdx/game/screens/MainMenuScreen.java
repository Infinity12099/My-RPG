package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.RPGAdventure;

import java.lang.management.MonitorInfo;

public class MainMenuScreen implements Screen {

    public static final int WINDOW_WITH = 1500;
    public static final int WINDOW_HEIGHT = 1000;
    private static final int EXIT_BUTTON_WITH = 250;
    private static final int EXIT_BUTTON_HEIGHT = 120;
    private static final int PLAY_BUTTON_WITH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 120;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PlAY_BUTTON_Y = 230;

    RPGAdventure game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public MainMenuScreen (RPGAdventure game) {
        this.game = game;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
    }

    @Override
    public void show () {

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(0.7f, 0.3f, 0.2f, 1);

        game.batch.begin();

        int x = (WINDOW_WITH - PLAY_BUTTON_WITH) / 2;
        if (Gdx.input.getX() < x + PLAY_BUTTON_WITH && Gdx.input.getX() > x && WINDOW_HEIGHT - Gdx.input.getY() < PlAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && WINDOW_HEIGHT - Gdx.input.getY() > PlAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, (WINDOW_WITH - PLAY_BUTTON_WITH) / 2, PlAY_BUTTON_Y, PLAY_BUTTON_WITH, PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched()) {
                game.batch.end();
                this.dispose();
                game.setScreen(new GameScreen(game));
                return;
            }
        } else {
            game.batch.draw(playButtonInactive, (WINDOW_WITH - PLAY_BUTTON_WITH) / 2, PlAY_BUTTON_Y, PLAY_BUTTON_WITH, PLAY_BUTTON_HEIGHT);
        }
        x = (WINDOW_WITH - EXIT_BUTTON_WITH) / 2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WITH && Gdx.input.getX() > x && WINDOW_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && WINDOW_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WITH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WITH, EXIT_BUTTON_HEIGHT);
        }
        game.batch.end();
    }

    @Override
    public void resize (int width, int height) {

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {

    }
}
