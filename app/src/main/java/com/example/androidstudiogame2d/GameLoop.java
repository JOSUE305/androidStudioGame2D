package com.example.androidstudiogame2d;

import android.graphics.Canvas;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.util.zip.Adler32;

import kotlin.jvm.Synchronized;

public class GameLoop extends Thread{
    public static final double MAX_UPS =30.0 ;
    private static final double UPS_PERIOD =1E+3/MAX_UPS ;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game=game;
        this.surfaceHolder=surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        Log.d("GameLoop.java","startLoop()");
        isRunning=true;
        start();
    }

    @Override
    public void run() {
        Log.d("GameLoop.java","run()");
        super.run();

        int updateCount=0;
        int frameCount=0;

        long startTime;
        long elapsedTime;
        long sleepTime;
        //game loop
        Canvas canvas=null;
        startTime=System.currentTimeMillis();
        while (isRunning){

            //try to update and render game
            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;

                    game.draw(canvas);

                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }


            //pause game loop to not exceed target UPS
            elapsedTime=System.currentTimeMillis()-startTime;
            sleepTime=(long) (updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime>0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //skip frames to keep up with target UPS
            while(sleepTime<0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime=System.currentTimeMillis()-startTime;
                sleepTime=(long)(updateCount*UPS_PERIOD - elapsedTime);
            }


            //calculate average UPS and FPS
            elapsedTime=System.currentTimeMillis()-startTime;
            if(elapsedTime>=1000){
                averageUPS=updateCount/(1E-3*elapsedTime);
                averageFPS=frameCount/(1E-3*elapsedTime);
                updateCount=0;
                frameCount=0;
                startTime=System.currentTimeMillis();
            }
        }
    }

    public void stopLoop() {
        Log.d("GameLoop.java","StopLoop()");
        isRunning=false;
        try {
            join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
