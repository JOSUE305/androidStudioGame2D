package com.example.androidstudiogame2d.graphics;

import android.graphics.Canvas;

import com.example.androidstudiogame2d.GameDisplay;
import com.example.androidstudiogame2d.gameObject.Player;
import com.example.androidstudiogame2d.gameObject.PlayerState;

public class Animator {

    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;
    private Sprite[] PlayerSpriteArray;
    private int idxNotMovingFrame=0;
    private int idxMovingFrame=1;
    private int updateBeforeNextMoveFrame;
    private final int NUM_MOVING_FRAMES = 9;


    public Animator(Sprite[] playerSpriteArray) {
        this.PlayerSpriteArray=playerSpriteArray;
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayeState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, PlayerSpriteArray[idxNotMovingFrame]);
                idxMovingFrame = 0; // Resetear la animación
                break;
            case STARTED_MOVING:
                updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                drawFrame(canvas, gameDisplay, player, PlayerSpriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                updateBeforeNextMoveFrame--;
                if (updateBeforeNextMoveFrame == 0) {
                    updateBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    idxMovingFrame = (idxMovingFrame + 1) % NUM_MOVING_FRAMES;
                }
                drawFrame(canvas, gameDisplay, player, PlayerSpriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }


    }
/*
    private void toggleIdxMovingFrame() {
        if(idxMovingFrame==1)
            idxMovingFrame=2;
        else
            idxMovingFrame=1;


    }
    */


    public void drawFrame(Canvas canvas,GameDisplay gameDisplay,Player player,Sprite sprite){

        sprite.draw(
                canvas,
                (int)  gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()-sprite.getWidth()/2),
                (int)  gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()-sprite.getHeight()/2)
        );
    }
}
