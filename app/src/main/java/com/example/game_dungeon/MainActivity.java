package com.example.game_dungeon;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.os.Handler;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Game gameView;
    private ImageButton upButton;
    private ImageButton downButton;
    private ImageButton rightButton;
    private ImageButton leftButton;
    private Button attackButton;
    private Button skillButton;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView); // Game 뷰 ID
        upButton = findViewById(R.id.upButton);
        downButton = findViewById(R.id.downButton);
        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);
        attackButton = findViewById(R.id.attackButton);
        skillButton = findViewById(R.id.skillButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveUser(0, -30); // User를 아래로 10픽셀 이동
                gameView.invalidate(); // Game 뷰 갱신 요청
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveUser(0, 30); // User를 아래로 10픽셀 이동
                gameView.invalidate(); // Game 뷰 갱신 요청
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveUser(30, 0); // User를 아래로 10픽셀 이동
                gameView.invalidate(); // Game 뷰 갱신 요청
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.moveUser(-30, 0); // User를 아래로 10픽셀 이동
                gameView.invalidate(); // Game 뷰 갱신 요청
            }
        });
        //공격버튼 누를때 A
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.attackUser(1); // 공격 이미지로 변경
                gameView.invalidate(); // 화면 갱신

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameView.attackUser(0); // 원래 이미지로 복원
                        gameView.invalidate(); // 화면 갱신
                    }
                }, 500); // 0.5초 후에 실행

            }
        });
        //스킬 버튼 누를때 B
        skillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.skillUser_1(1); // 공격 이미지로 변경
                gameView.invalidate(); // 화면 갱신

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameView.skillUser_2(1); // 원래 이미지로 복원
                        gameView.invalidate(); // 화면 갱신
                    }
                }, 400); // 0.5초 후에 실행
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameView.skillUser_2(0); // 원래 이미지로 복원
                        gameView.invalidate(); // 화면 갱신
                    }
                }, 1500); // 0.5초 후에 실행

            }
        });

    }

}