package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.RPGAdventure;

import java.lang.management.MonitorInfo;

public class MainMenuScreen implements Screen {

    public static final int WINDOW_WITH = 800;
    public static final int WINDOW_HEIGHT = 450;
    private static final int EXIT_BUTTON_WITH = 166;
    private static final int EXIT_BUTTON_HEIGHT = 80;
    private static final int PLAY_BUTTON_WITH = 200;
    private static final int PLAY_BUTTON_HEIGHT = 80;
    private static final int EXIT_BUTTON_Y = 20;
    private static final int PlAY_BUTTON_Y = 90;

    RPGAdventure game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    Viewport viewport;

    public MainMenuScreen (RPGAdventure game) {
        this.game = game;
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        viewport.apply(true);
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
        Vector2 unprojected = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        float wX = unprojected.x;
        float wY = unprojected.y;
        if (wX > x && wX < x + PLAY_BUTTON_WITH && wY < PlAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && wY > PlAY_BUTTON_Y) {
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
        if (wX > x&& wX < x + EXIT_BUTTON_WITH && wY < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && wY > EXIT_BUTTON_Y) {
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
        viewport.update(width, height);
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
