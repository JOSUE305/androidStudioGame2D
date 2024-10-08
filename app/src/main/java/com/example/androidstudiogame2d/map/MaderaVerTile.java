package com.example.androidstudiogame2d.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiogame2d.graphics.Sprite;
import com.example.androidstudiogame2d.graphics.SpriteSheet;

public class MaderaVerTile extends Tile {
    private final Sprite sprite;

    public MaderaVerTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite=spriteSheet.getMaderaVerSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas,mapLocationRect.left,mapLocationRect.top);
    }
}
