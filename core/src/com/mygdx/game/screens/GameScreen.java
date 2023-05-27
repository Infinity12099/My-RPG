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
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Assets;
import com.mygdx.game.RPGAdventure;

import java.util.Random;

public class GameScreen implements Screen {

    public static final int WINDOW_WIDTH = 800;
    RPGAdventure game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Random rand;
    Texture Player_stand_down1;
    Texture Player_stand_down2;
    Texture Player_stand_down3;
    Texture Player_stand_down4;
    Texture Player_stand_down5;

    Sprite Player_stand_down_sprite1;

    public static final int PLAYER_WIDTH = 39;
    public static final int PLAYER_HEIGHT = 63;
    public static final int PlAYER_WIDTH_PIXEL = 13;
    public static final int PlAYER_HEIGHT_PIXEL = 21;

    Animation[] stand_down_a;
    float PlayerX;
    float PlayerY;
    int stand_down;
    float stateTime;
    public static float SPEED = 500;

    public static final  float PLAYER_ANIMATION_SPEED = 0.5f;

    public GameScreen (RPGAdventure game) {
        this.game = game;
        PlayerY = 15;
        PlayerX = WINDOW_WIDTH/2-PLAYER_WIDTH/2;
        stand_down = 2;
        stand_down_a = new Animation[5];

        TextureRegion[][] player_stand_downSpriteSheet = TextureRegion.split(new Texture("PLAYER_DOWNSTADSPRITESHEET.png"), PlAYER_WIDTH_PIXEL, PlAYER_HEIGHT_PIXEL);

        stand_down_a[stand_down] = new Animation(PLAYER_ANIMATION_SPEED, player_stand_downSpriteSheet[0]);

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);

        Player_stand_down1 = new Texture("Player/Player_stand_down1.png");
        Player_stand_down_sprite1 = new Sprite(Player_stand_down1);
        Player_stand_down_sprite1.flip(false, true);
        Player_stand_down2 = new Texture("Player/Player_stand_down2.png");
        Player_stand_down3 = new Texture("Player/Player_stand_down3.png");
        Player_stand_down4 = new Texture("Player/Player_stand_down4.png");
        Player_stand_down5 = new Texture("Player/Player_stand_down5.png");



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

        batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            SPEED=600;
        }else {
            SPEED=500;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            PlayerY-=SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            PlayerY+=SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            PlayerX+=SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            PlayerX-=SPEED * Gdx.graphics.getDeltaTime();
        }
        batch.draw(stand_down_a[stand_down].getKeyFrame(stateTime,true),PlayerX,PlayerY, PLAYER_WIDTH, PLAYER_HEIGHT);
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
