package com.example.androidstudiogame2d.gamePanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameDisplay;
import com.example.androidstudiogame2d.R;
import com.example.androidstudiogame2d.gameObject.Player;

// barrra de vida del jugador
public class HealtBar {
    private Player player;
    private int width,height,margin;
    private Paint borderPaint,healthPaint;

    public HealtBar(Context context, Player player){
        this.player=player;
        this.width=100;
        this.height=20;
        this.margin=2;

        this.borderPaint=new Paint();
        int borderColor= ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint=new Paint();
        int healthColor= ContextCompat.getColor(context, R.color.healthBarHeath);
        healthPaint.setColor(healthColor);

    }
    public void draw(Canvas canvas, GameDisplay gameDisplay){
        float x= (float) player.getPositionX();
        float y= (float) player.getPositionY();
        float distanceToPlayer=30;
        float healthPointPercentage=(float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;



        //dibuja los bordes
        float borderLeft,borderTop,borderRight,borderBottom;
        borderLeft=x-width/2;
        borderRight=x+width/2;
        borderBottom=y-distanceToPlayer;
        borderTop=borderBottom-height;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(borderRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderBottom),
                borderPaint);

        //dibujar la vida
        float healthLfet,healthTop,healthRight,healthBottom,healthWidth,healthHeight;
        healthWidth=width-2*margin;
        healthHeight=height-2*margin;
        healthLfet=borderLeft+margin;
        healthRight=healthLfet+healthWidth*healthPointPercentage;
        healthBottom=borderBottom-margin;
        healthTop=healthBottom-healthHeight;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(healthLfet) ,
                (float) gameDisplay.gameToDisplayCoordinatesY(healthTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(healthRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthBottom) ,
                healthPaint);

    }

}
