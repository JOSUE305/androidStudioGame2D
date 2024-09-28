package com.example.androidstudiogame2d.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiogame2d.graphics.Sprite;
import com.example.androidstudiogame2d.graphics.SpriteSheet;

public class CajonTile extends Tile {
    private final Sprite sprite;

    public CajonTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite=spriteSheet.getCajonSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas,mapLocationRect.left,mapLocationRect.top);
    }
}
