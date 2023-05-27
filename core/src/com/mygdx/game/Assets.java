package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
    public static Texture texture_back;
    public static Sprite sprite_back;
    public static Texture texture_sheet;
    public static Sprite sprite_grass;
    public static Sprite sprite_grass2;
    public static void load(){
        texture_back = new Texture(Gdx.files.internal("back.png"));
        texture_back.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.flip(false, true);

        texture_sheet = new Texture(Gdx.files.internal("sprite_sheet.jpg"));
        sprite_grass = new Sprite(texture_sheet, 0, 0, 16, 16);
        sprite_grass2 = new Sprite(texture_sheet, 16, 0, 16, 16);
    }
}
