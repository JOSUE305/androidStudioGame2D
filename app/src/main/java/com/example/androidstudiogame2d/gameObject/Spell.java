package com.example.androidstudiogame2d.gameObject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.R;

public class Spell extends Circle {

    public static final double  SPEED_PIXEL_PER_SECOND=800.0;
    public static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND/ GameLoop.MAX_UPS ;

    public Spell(Context context, Player spellcaster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                25

        );
        velocityX=spellcaster.getDirectionX()*MAX_SPEED;
        velocityY=spellcaster.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {

        positionX+=velocityX;
        positionY+=velocityY;
    }
}
