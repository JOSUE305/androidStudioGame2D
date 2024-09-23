package com.example.androidstudiogame2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.object.Enemy;
import com.example.androidstudiogame2d.object.Player;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private final Enemy enemy;
    private GameLoop gameLoop;


    public Game(Context context)
    {
        super(context);


        //get surface holder and add callback "obtener soporte de superficie y agregar devolución de llamada"
        SurfaceHolder surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);


        gameLoop = new GameLoop(this,surfaceHolder);
        //initialize player

        joystick=new Joystick(275,600,70,40);
        player =new Player(getContext(),joystick,500,500,30);
        enemy=new Enemy(getContext(),player,500,200,30);
        

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //handle touch event action
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();


    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawnUPS(canvas);
        drawnFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);

    }

    public void drawnUPS(Canvas canvas){
       
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(getContext(),R.color.blue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS"+averageUPS,100,100,paint);

    }
    public void drawnFPS(Canvas canvas){

        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(getContext(),R.color.blue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS"+averageFPS,100,200,paint);

    }


    public void update() {

        joystick.update();
        player.update();
        enemy.update();

    }
}
