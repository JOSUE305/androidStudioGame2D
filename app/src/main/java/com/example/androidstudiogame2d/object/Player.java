package com.example.androidstudiogame2d.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.Joystick;
import com.example.androidstudiogame2d.R;
/*
player is the main character of the game, which the user can control with a touch
joystick.
the player class is an extension of a circle, which is an extension of a GameObject.
 */

public class Player extends Circle{
    public static final double  SPEED_PIXEL_PER_SECOND=400.0;
    public static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND/ GameLoop.MAX_UPS ;
    private final Joystick joystick;


    public Player(Context context,Joystick joystick, double positionX, double positionY, double radius){

        super(context,ContextCompat.getColor(context, R.color.player),positionX,positionY,radius);
        this.joystick=joystick;

    }



    public void update() {
        //update velocity based on actuator of joystick
        velocityX=joystick.getActuadorX()*MAX_SPEED;
        velocityY=joystick.getActuadorY()*MAX_SPEED;
        //update position
        positionX +=velocityX;
        positionY +=velocityY;

    }


}
