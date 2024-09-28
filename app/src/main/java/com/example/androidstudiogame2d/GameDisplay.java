package com.example.androidstudiogame2d;

import android.graphics.Rect;

import com.example.androidstudiogame2d.gameObject.GameObject;

public class GameDisplay {

    public  final Rect DISPLAY_RECT ;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinateOffSetX;
    private double gameToDisplayCoordinateOffSetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels,int heightPixels,GameObject centerObject){

        this.widthPixels=widthPixels;
        this.heightPixels=heightPixels;
        DISPLAY_RECT=new Rect(0,0,widthPixels,heightPixels);
        this.centerObject=centerObject;

        displayCenterX=widthPixels/2.0;
        displayCenterY=heightPixels/2.0;


    }


    public void update(){
        gameCenterX=centerObject.getPositionX();
        gameCenterY=centerObject.getPositionY();

        gameToDisplayCoordinateOffSetX=displayCenterX-gameCenterX;
        gameToDisplayCoordinateOffSetY=displayCenterY-gameCenterY;
    }
    public double gameToDisplayCoordinatesX(double x) {

        return x+gameToDisplayCoordinateOffSetX;
        
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y+gameToDisplayCoordinateOffSetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int)gameCenterX-widthPixels/2,
                (int)gameCenterY-heightPixels/2,
                (int)gameCenterX+widthPixels/2,
                (int)gameCenterY+heightPixels/2
        );
    }
}
