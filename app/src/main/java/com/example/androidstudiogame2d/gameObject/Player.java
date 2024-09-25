package com.example.androidstudiogame2d.gameObject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameDisplay;
import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.gamePanel.HealtBar;
import com.example.androidstudiogame2d.gamePanel.Joystick;
import com.example.androidstudiogame2d.R;
import com.example.androidstudiogame2d.Utils;
/*
player is the main character of the game, which the user can control with a touch
joystick.
the player class is an extension of a circle, which is an extension of a GameObject.
 */

public class Player extends Circle{
    public static final double  SPEED_PIXEL_PER_SECOND=400.0;
    public static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND/ GameLoop.MAX_UPS ;
    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private HealtBar healtBar;
    private int healthPoints;

    public Player(Context context,Joystick joystick, double positionX, double positionY, double radius){

        super(context,ContextCompat.getColor(context, R.color.player),positionX,positionY,radius);
        this.joystick=joystick;
        this.healtBar= new HealtBar(context,this);
        this.healthPoints=MAX_HEALTH_POINTS;

    }
    public void update() {
        //update velocity based on actuator of joystick
        velocityX=joystick.getActuadorX()*MAX_SPEED;
        velocityY=joystick.getActuadorY()*MAX_SPEED;

        //update position
        positionX +=velocityX;
        positionY +=velocityY;

        //actualizar direccion
        if(velocityX != 0 || velocityY!=0 ){
            double distance= Utils.getDistanceBetweenObjects(0,0,velocityX,velocityY);
            directionX=velocityX/distance;
            directionY=velocityY/distance;
        }


    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas,gameDisplay);
        healtBar.draw(canvas,gameDisplay);
        
    }


    public int getHealthPoints() {
      return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints>=0)
            this.healthPoints=healthPoints;
    }
}
