package com.example.androidstudiogame2d.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.androidstudiogame2d.R;

public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions=new BitmapFactory.Options();
        bitmapOptions.inScaled=false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_player_animated,bitmapOptions);
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
}
