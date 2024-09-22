package com.example.androidstudiogame2d;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Joystick(int centerPositionX, int centerPositiony, int outerCircleRadius,int innerCircleRadius){
        //outer and inner circle make up the joystick
        outerCircleCenterPositionX=centerPositionX;
        outerCircleCenterPositionY=centerPositiony;
        innerCircleCenterPositionX=centerPositionX;
        innerCircleCenterPositionY=centerPositiony;

        //radii of circles
        this.outerCircleRadius=outerCircleRadius;
        this.innerCircleRadius=innerCircleRadius;

        //paint of circles
        outerCirclePaint=new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint=new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }
    public void draw(Canvas canvas) {

        //draw outer circles
        canvas.drawCircle(
                outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                outerCircleRadius,
                outerCirclePaint
                );
        //draw inner circles
        canvas.drawCircle(
                innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint
        );

    }

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX=(int)(outerCircleCenterPositionX+actuatorX*outerCircleRadius);
        innerCircleCenterPositionY=(int)(outerCircleCenterPositionY+actuatorY*outerCircleRadius);


    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        joystickCenterToTouchDistance=Math.sqrt(
                Math.pow(outerCircleCenterPositionX-touchPositionX,2)+
                Math.pow(outerCircleCenterPositionY-touchPositionY,2)
        );
        return joystickCenterToTouchDistance<outerCircleRadius;
        
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed=isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;

    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX=touchPositionX-outerCircleCenterPositionX;
        double deltaY=touchPositionY-outerCircleCenterPositionY;
        double deltaDistance= Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));

        if(deltaDistance < outerCircleRadius){
            actuatorX= deltaX/outerCircleRadius;
            actuatorY= deltaY/outerCircleRadius;
        }else{
            actuatorX=deltaX/deltaDistance;
            actuatorY=deltaY/deltaDistance;
        }


    }

    public void resetActuator() {
        actuatorX=0.0;
        actuatorY=0.0;
    }

    public double getActuadorX() {
        return actuatorX;
    }
    public double getActuadorY() {
        return actuatorY;
    }

}
