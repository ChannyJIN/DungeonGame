package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Monster2 {
    private Bitmap monster1;
    private int monX, monY; // 몬스터의 위치
    private int hp;   // 몬스터의 체력
    private Paint textP;

    public Monster2(Context con, int monX, int monY) {

        this.monX = monX;
        this.monY = monY;
        this.hp = 100; // 예를 들어, 몬스터의 초기 체력을 100으로 설정

        this.monster1 = BitmapFactory.decodeResource(con.getResources(), R.drawable.monster2);
        monster1 = Bitmap.createScaledBitmap(monster1, 200, 200, true);

        textP = new Paint();
        textP.setColor(Color.RED);
        textP.setTextSize(40);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(monster1, monX, monY, null);
        canvas.drawText("HP: " + hp, monX + 40, monY + 220, textP);
    }

    // 몬스터의 위치 설정 메소드
    public void setPosition(int monX, int monY) {
        this.monX = monX;
        this.monY = monY;
    }

    // 몬스터의 체력 관리 메소드
    public void setHp(int hp) {
        this.hp -= hp;
    }
    public int getHp() {
        return hp;
    }

    public int getX() {
        return monX;
    }

    public int getY() {
        return monY;
    }
}

