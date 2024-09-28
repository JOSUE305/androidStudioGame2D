package com.example.androidstudiogame2d.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.androidstudiogame2d.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions=new BitmapFactory.Options();
        bitmapOptions.inScaled=false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_player_and_floors,bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray=new Sprite[9];
        spriteArray[0]=  new Sprite(this,new Rect(0*64,0,1*64,64));
        spriteArray[1]=  new Sprite(this,new Rect(1*64,0,2*64,64));
        spriteArray[2]=  new Sprite(this,new Rect(2*64,0,3*64,64));
        spriteArray[3]=  new Sprite(this,new Rect(3*64,0,4*64,64));
        spriteArray[4]=  new Sprite(this,new Rect(4*64,0,5*64,64));
        spriteArray[5]=  new Sprite(this,new Rect(0*64,64,1*64,128));
        spriteArray[6]=  new Sprite(this,new Rect(1*64,64,2*64,128));
        spriteArray[7]=  new Sprite(this,new Rect(2*64,64,3*64,128));
        spriteArray[8]=  new Sprite(this,new Rect(3*64,64,4*64,128));
        return spriteArray;

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getMarmolSprite() {
        return getSpriteByIndex(2,0);
    }
    public Sprite getMaderaHorSprite() {
        return getSpriteByIndex(2,1);
    }
    public Sprite getMaderaVerSprite() {
        return getSpriteByIndex(2,2);
    }
    public Sprite getCajonSprite() {
        return getSpriteByIndex(2,3);
    }
    public Sprite getPiedraSprite() {
        return getSpriteByIndex(2,4);
    }




    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this,new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol+1)*SPRITE_WIDTH_PIXELS,
                (idxRow+1)*SPRITE_HEIGHT_PIXELS
        ));

    }
}
