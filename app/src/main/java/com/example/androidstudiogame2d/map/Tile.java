package com.example.androidstudiogame2d.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.androidstudiogame2d.graphics.SpriteSheet;

abstract class Tile {
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect=mapLocationRect;
    }

    public enum TileType{
        MARMOL_TILE,
        MADERA_HORISONTAL_TILE,
        MADERA_VERTICAL_TILE,
        CAJON_TILE,
        PIEDRA_TILE
    }
    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]){

            case MARMOL_TILE:
                return new MarmolTile(spriteSheet,mapLocationRect);
            case MADERA_HORISONTAL_TILE:
                return new MaderaHorTile(spriteSheet,mapLocationRect);
            case MADERA_VERTICAL_TILE:
                return new MaderaVerTile(spriteSheet,mapLocationRect);
            case CAJON_TILE:
                return new CajonTile(spriteSheet,mapLocationRect);
            case PIEDRA_TILE:
                return new PiedraTile(spriteSheet,mapLocationRect);
            default:
                return null;


        }
    }


    public abstract void draw(Canvas canvas);
}
