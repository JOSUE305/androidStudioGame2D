package com.example.androidstudiogame2d.gamePanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.GameLoop;
import com.example.androidstudiogame2d.R;

public class Performance {
    private GameLoop gameLoop;
    private Context context;


    public Performance(Context context, GameLoop gameLoop){
        this.gameLoop=gameLoop;
        this.context=context;
    }

    public void draw(Canvas canvas){
        drawnUPS(canvas);
        drawnFPS(canvas);

    }
    public void drawnUPS(Canvas canvas){

        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context, R.color.blue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS"+averageUPS,100,100,paint);

    }
    public void drawnFPS(Canvas canvas){

        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color= ContextCompat.getColor(context,R.color.blue);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS"+averageFPS,100,200,paint);

    }
}
