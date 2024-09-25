package com.example.androidstudiogame2d;

import com.example.androidstudiogame2d.gameObject.GameObject;

public class GameDisplay {

    private double gameToDisplayCoordinateOffSetX;
    private double gameToDisplayCoordinateOffSetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels,int heightPixels,GameObject centerObject){
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
}
