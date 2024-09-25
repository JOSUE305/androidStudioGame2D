package com.example.androidstudiogame2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.androidstudiogame2d.gameObject.Circle;
import com.example.androidstudiogame2d.gameObject.Enemy;
import com.example.androidstudiogame2d.gameObject.Player;
import com.example.androidstudiogame2d.gameObject.Spell;
import com.example.androidstudiogame2d.gamePanel.GameOver;
import com.example.androidstudiogame2d.gamePanel.Joystick;
import com.example.androidstudiogame2d.gamePanel.Performance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private GameLoop gameLoop;
    private List<Enemy>  enemyList = new ArrayList<Enemy>();
    private List<Spell>  spellList = new ArrayList<Spell>();
    private int joysticPointerId=0;
    private int numberOfSpellToCast=0;
    private GameOver gameOver;
    private Performance performance;


    public Game(Context context)
    {
        super(context);

        //get surface holder and add callback "obtener soporte de superficie y agregar devolución de llamada"
        SurfaceHolder surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this,surfaceHolder);

        //iniciar panel de juego
        performance=new Performance(context,gameLoop);
        gameOver= new GameOver(context);
        joystick=new Joystick(275,600,70,40);

        //iniciar los objetos del juego

        player =new Player(context,joystick,500,500,30);

        

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //handle touch event action
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()){
                    numberOfSpellToCast++;
                }
                else if(joystick.isPressed((double)event.getX(),(double)event.getY())){

                    joysticPointerId=event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }else{
                    numberOfSpellToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joysticPointerId ==event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
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

        player.draw(canvas);

        for(Enemy enemy :enemyList){
            enemy.draw(canvas);
        }
        for(Spell spell :spellList){
            spell.draw(canvas);
        }

        joystick.draw(canvas);
        performance.draw(canvas);
        

        // dibujar el game over si el jugador muere
        if(player.getHealthPoints()<=0){
            gameOver.draw(canvas);
            
        }


    }




    public void update() {

        if(player.getHealthPoints()<=0){
            return;
        }


        joystick.update();
        player.update();

        //spawn enemy if is is time to spawn new enemys
        if(Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(),player));
        }

        while (numberOfSpellToCast >0){
            spellList.add(new Spell(getContext(),player));
            numberOfSpellToCast--;
        }
        //update state of each enemy
        for(Enemy enemy :enemyList){
            enemy.update();
        }

        //update state of each spell
        for(Spell spell :spellList){
            spell.update();
        }

        //iterate through enemylist and check for collision between each enemy and the player
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()){
            Circle enemy=iteratorEnemy.next();
            if(Circle.isColliding(enemy,player)){
                //remover el enemigo si hace colicion con el jugador
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints()-1);
                continue;
            }

            Iterator<Spell> iteratorSpell =spellList.iterator();
            while (iteratorSpell.hasNext()){
                Circle spell= iteratorSpell.next();
                if(Circle.isColliding(spell,enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }

    }
}
