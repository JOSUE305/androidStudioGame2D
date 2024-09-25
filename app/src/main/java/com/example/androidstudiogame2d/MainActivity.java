package com.example.androidstudiogame2d;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MainActivity.java","onCreate()");
        game=new Game(this);
        setContentView(game);

    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java","onStar()");
        super.onStart();
    }
    @Override
    protected void onResume () {
        Log.d("MainActivity.java","onResume()");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("MainActivity.java","onPause()");
        game.pause();
        super.onPause();

    }
    @Override
    protected void onStop() {
        Log.d("MainActivity.java","onStop()");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java","onDestroy()");
        super.onDestroy();
    }
}