package com.example.androidstudiogame2d.gameObject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.R;

public class Enemy extends Circle{


    private final Player player;
    private static final double  SPEED_PIXEL_PER_SECOND=Player.SPEED_PIXEL_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND/ GameLoop.MAX_UPS ;
    private static final double SPAWNS_PER_MINUTE=20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static double UPDATES_PER_SPAWN=GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn= UPDATES_PER_SPAWN;


    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player=player;
    }

    public Enemy(Context context, Player player) {
        super(
            context,
            ContextCompat.getColor(context, R.color.enemy),
    Math.random()*1000,
    Math.random()*1000,
        30

        );
        this.player=player;


    }


    /*
    * readyToSpanw checks if a new enemy should spawn,according to the decided number to spawns per minute
    * (see SPAWNS_PER_MINUTE at top)
    * */
    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }else{
            updatesUntilNextSpawn --;
            return false;
        }

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
