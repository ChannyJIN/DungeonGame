package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

public class User {
    Paint textP,hpP,mpP;
    int hp, power, mp, userX,userY;
    Bitmap userOrign;  //유저의 현재 비트맵 이미지
    Bitmap userBitmap;  //유저의 기본 비트맵 이미지
    Bitmap userAttack;  //유저의 공격 비트맵 이미지
    Bitmap userSkill_1;
    Bitmap userSkill_2;

    //최초 체력100, 파워10, 위치 지정
    public User(Context con, int userX, int userY){         //유저 생성자
        this.hp=100;
        this.power=10;
        this.userX=userX;
        this.userY=userY;

        textP = new Paint();
        textP.setColor(Color.GREEN);
        textP.setTextSize(30);
        hpP = new Paint();
        hpP.setColor(Color.RED);
        hpP.setTextSize(30);
        mpP = new Paint();
        mpP.setColor(Color.BLUE);
        mpP.setTextSize(30);

        //유저의 일반 이미지
        userBitmap = BitmapFactory.decodeResource(con.getResources(), R.drawable.user);
        userBitmap = Bitmap.createScaledBitmap(userBitmap, 200, 230, false);

        //유저의 공격 이미지
        userAttack = BitmapFactory.decodeResource(con.getResources(), R.drawable.user_attack);
        userAttack = Bitmap.createScaledBitmap(userAttack, 220, 250, false);

        userSkill_1 = BitmapFactory.decodeResource(con.getResources(), R.drawable.user_skill_1);
        userSkill_1 = Bitmap.createScaledBitmap(userSkill_1, 200, 230, false);
        userSkill_2 = BitmapFactory.decodeResource(con.getResources(), R.drawable.user_skill_3);
        userSkill_2 = Bitmap.createScaledBitmap(userSkill_2, 600, 250, false);
        userOrign = userBitmap;


    }
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(userOrign, userX, userY, null);
        canvas.drawText("HP : " + hp, userX + 10, userY + 250, hpP);
        canvas.drawText("MP : " + mp, userX + 10, userY + 280, mpP);
        canvas.drawText("POWER : " + power, userX + 10, userY + 310, textP);
    }

    //유저 파워 관리 함수
    public void powerUp(int power){
        this.power+=power;
    }
    public void mpUp(int mp){
        this.mp+=mp;
    }
    public void minusHp(int amount) {
        this.hp -= amount;
        if (this.hp < 0) {
            this.hp = 0; // HP가 0 이하로 내려가지 않도록
        }
    }

    //유저 이동함수
    public void moveUser(int userX,int userY){
        this.userX+=userX;
        this.userY+=userY;
    }

    //유저 공격모션 함수
    public void startAttack(){
        userOrign = userAttack;
    }

    //유저 공격 끝 함수
    public void endAttack(){
        userOrign = userBitmap;
    }

    //유저 스킬 함수
    public void startSkill_1(){
        userOrign =userSkill_1;

    }
    public void startSkill_2(){
        userOrign =userSkill_2;
    }






}
