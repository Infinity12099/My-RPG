package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.RPGAdventure;

import java.util.Random;

public class GameScreen implements Screen {

    public static final int WINDOW_WIDTH = 800;
    RPGAdventure game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Random rand;

    Viewport viewport;


    public static final int PLAYER_WIDTH = 13*5;
    public static final int PLAYER_HEIGHT = 21*5;
    public static final int PlAYER_WIDTH_PIXEL = 13;
    public static final int PlAYER_HEIGHT_PIXEL = 21;

    Animation[] stand_down_a;
    Animation[] stand_right_a;
    Animation[] stand_left_a;
    Animation[] stand_up_a;
    Animation[] walk_down_a;
    Animation[] walk_right_a;
    Animation[] walk_left_a;
    Animation[] walk_up_a;
    float PlayerX;
    float PlayerY;
    int stand_down;
    int stand_right;
    int stand_left;
    int stand_up;
    int walk_down;
    int walk_right;
    int walk_left;
    int walk_up;
    float stateTime;

    String was_keypressed;
    public static float SPEED = 500;

    public static final  float PLAYER_ANIMATION_SPEED = 0.2f;

    public GameScreen (RPGAdventure game) {
        this.game = game;
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        viewport.apply(true);
        PlayerY = 15;
        PlayerX = WINDOW_WIDTH/2-PLAYER_WIDTH/2;
        stand_down = 2;
        stand_right = 2;
        stand_left = 2;
        stand_up = 2;
        walk_down = 0;
        walk_right = 0;
        walk_left = 0;
        walk_up = 0;
        stand_down_a = new Animation[5];
        stand_right_a = new Animation[5];
        stand_left_a = new Animation[5];
        stand_up_a = new Animation[5];
        walk_down_a = new Animation[6];
        walk_right_a = new Animation[6];
        walk_left_a = new Animation[6];
        walk_up_a = new Animation[6];

        TextureRegion[][] player_stand_downSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_DOWN_STANDSPRITESHEET.png"), PlAYER_WIDTH_PIXEL, PlAYER_HEIGHT_PIXEL);
        TextureRegion[][] player_stand_rightSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_RIGHT_STANDSPRITESHEET.png"), 15, PlAYER_HEIGHT_PIXEL);
        TextureRegion[][] player_stand_leftSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_LEFT_STANDSPRITESHEET.png"), 15, PlAYER_HEIGHT_PIXEL);
        TextureRegion[][] player_stand_upSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_UP_STANDSPRITESHEET.png"), PlAYER_WIDTH_PIXEL, PlAYER_HEIGHT_PIXEL);
        TextureRegion[][] player_walk_downSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_DOWN_WALKSPRITESHEET.png"), PlAYER_WIDTH_PIXEL, 23);
        TextureRegion[][] player_walk_rightSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_RIGHT_WALKSPRITESHEET.png"), 15, 23);
        TextureRegion[][] player_walk_leftSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_LEFT_WALKSPRITESHEET.png"), 15, 23);
        TextureRegion[][] player_walk_upSpriteSheet = TextureRegion.split(new Texture("Player/PLAYER_UP_WALKSPRITESHEET.png"), 13, 22);
        stand_down_a[stand_down] = new Animation(PLAYER_ANIMATION_SPEED, player_stand_downSpriteSheet[0]);
        stand_right_a[stand_right] = new Animation(PLAYER_ANIMATION_SPEED, player_stand_rightSpriteSheet[0]);
        stand_left_a[stand_left] = new Animation(PLAYER_ANIMATION_SPEED, player_stand_leftSpriteSheet[0]);
        stand_up_a[stand_up] = new Animation(PLAYER_ANIMATION_SPEED, player_stand_upSpriteSheet[0]);
        walk_down_a[walk_down] = new Animation(PLAYER_ANIMATION_SPEED, player_walk_downSpriteSheet[0]);
        walk_right_a[walk_right] = new Animation(PLAYER_ANIMATION_SPEED, player_walk_rightSpriteSheet[0]);
        walk_left_a[walk_left] = new Animation(PLAYER_ANIMATION_SPEED, player_walk_leftSpriteSheet[0]);
        walk_up_a[walk_up] = new Animation(PLAYER_ANIMATION_SPEED, player_walk_upSpriteSheet[0]);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);


        batch = new SpriteBatch();

        rand = new Random();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        stateTime += delta;

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

        }

        batch.setProjectionMatrix(camera.combined);

        Vector2 unprojected = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

        batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            SPEED=600;
        }else {
            SPEED=500;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            PlayerY-=SPEED * Gdx.graphics.getDeltaTime();
            was_keypressed = "W";
            if (PlayerY + PLAYER_HEIGHT > Gdx.graphics.getHeight()) {
                PlayerY = Gdx.graphics.getHeight() - PLAYER_HEIGHT;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            PlayerY+=SPEED * Gdx.graphics.getDeltaTime();
            was_keypressed = "S";
            if (PlayerY < 0) {
                PlayerY = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            PlayerX+=SPEED * Gdx.graphics.getDeltaTime();
            was_keypressed = "D";
            if (PlayerX + 15*5 > Gdx.graphics.getWidth()){
                PlayerX = Gdx.graphics.getWidth() - 15*5;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            PlayerX-=SPEED * Gdx.graphics.getDeltaTime();
            was_keypressed = "A";
            if (PlayerX < 0){
                PlayerX = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            batch.draw((TextureRegion) walk_down_a[walk_down].getKeyFrame(stateTime, true), PlayerX, PlayerY, PLAYER_WIDTH, 23*5);
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            batch.draw((TextureRegion) walk_up_a[walk_up].getKeyFrame(stateTime, true), PlayerX, PlayerY, PLAYER_WIDTH, 22*5);
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)){
            batch.draw((TextureRegion) walk_right_a[walk_right].getKeyFrame(stateTime, true), PlayerX, PlayerY, 15*5, 23*5);
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            batch.draw((TextureRegion) walk_left_a[walk_left].getKeyFrame(stateTime, true), PlayerX, PlayerY, 15*5, 23*5);
        }else if (was_keypressed == "S") {
                batch.draw((TextureRegion) stand_down_a[stand_down].getKeyFrame(stateTime, true), PlayerX, PlayerY, PLAYER_WIDTH, PLAYER_HEIGHT);
                was_keypressed = "S";
            } else if (was_keypressed == "D") {
                batch.draw((TextureRegion) stand_right_a[stand_right].getKeyFrame(stateTime, true), PlayerX, PlayerY, 15 * 5, PLAYER_HEIGHT);
                was_keypressed = "D";
            } else if (was_keypressed == "A") {
                batch.draw((TextureRegion) stand_left_a[stand_left].getKeyFrame(stateTime, true), PlayerX, PlayerY, 15 * 5, PLAYER_HEIGHT);
                was_keypressed = "A";
            } else if (was_keypressed == "W") {
                batch.draw((TextureRegion) stand_up_a[stand_up].getKeyFrame(stateTime, true), PlayerX, PlayerY, PLAYER_WIDTH, PLAYER_HEIGHT);
                was_keypressed = "W";
            } else {
                batch.draw((TextureRegion) stand_down_a[stand_down].getKeyFrame(stateTime, true), PlayerX, PlayerY, PLAYER_WIDTH, PLAYER_HEIGHT);
            }

        //for (int i = 0; i<1920; i+=16) {
            //for (int j = 0; j<1080; j+=16) {
                //switch (rand.nextInt(2)) {
                   // case 0:
                        //batch.draw(Assets.sprite_grass, i, j);
                        //break;
                    //case 1:
                        //batch.draw(Assets.sprite_grass2, i, j);
                        //break;
              //  }
           // }
        //}
        batch.end();
    }
    @Override
    public void show () {

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
