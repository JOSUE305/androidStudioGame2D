package com.example.androidstudiogame2d.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.R;

public class Enemy extends Circle{

    private final Player player;
    private static final double  SPEED_PIXEL_PER_SECOND=Player.SPEED_PIXEL_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND/ GameLoop.MAX_UPS ;


    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player=player;
    }

    @Override
    public void update() {
        //Update velocity of the enemy so that the velocity is in the direction of the player

        //calculate vector from enemy to player (in x and y)
        double distancePlayerX=player.getPositionX()-positionX;
        double distancePlayerY=player.getPositionY()-positionY;

        //calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer=getDistanceBetweenObjects(this,player);

        //calculated direction from enemy to player
        double directionX=distancePlayerX/distanceToPlayer;
        double directionY=distancePlayerY/distanceToPlayer;

        //set velocity in the direction to the player
        if(distanceToPlayer > 0){//avoid division by zero
            velocityX=directionX*MAX_SPEED;
            velocityY=directionY*MAX_SPEED;
        }else {
            velocityX=0;
            velocityY=0;
        }

        //update the position of the enemy
        positionX+=velocityX;
        positionY+=velocityY;


    }


}
