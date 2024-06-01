package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

public class Map {

    int sw, sh;                         //화면 크기(가로 세로)
    Bitmap map;                         //맵 비트맵
    List<Monster1> monster1s;      //monster1 타입 몬스터 객체 배열
    List<Monster2> monster2s;      //monster2 타입 몬스터 객체 배열
    List<ItemPower> itemPower;                //파워업 아이템 객체
    List<ItemSkill> itemSkill;                //스킬 아이템 객체




    public Map(Context con, int sw, int sh,int mapResource, List<Point> monsterPosition1s,List<Point> monsterPosition2s,List<Point> powerItemPositions,List<Point> skillItemPositions ){
        this.sw=sw;
        this.sh=sh;

        //첫 맵
        map = BitmapFactory.decodeResource(con.getResources(), mapResource);

        //몬스터1 객체 배열 생성
        monster1s = new ArrayList<>();
        for (Point position : monsterPosition1s) {
            monster1s.add(new Monster1(con, position.x, position.y));
        }

        //몬스터2 객체 배열 생성
        monster2s = new ArrayList<>();
        for (Point position : monsterPosition2s) {
            monster2s.add(new Monster2(con, position.x, position.y));
        }

        //파워 아이템 객체
        itemPower = new ArrayList<>();
        for (Point position : powerItemPositions) {
            itemPower.add(new ItemPower(con, position.x, position.y));
        }
        //스킬 아이템 객체
        itemSkill = new ArrayList<>();
        for (Point position : skillItemPositions) {
            itemSkill.add(new ItemSkill(con, position.x, position.y));
        }


    }

    //캔버스 그리기
    public void draw(Canvas canvas){

        // 게임 맵 그리기
        map = Bitmap.createScaledBitmap(map, sw, sh, false);
        canvas.drawBitmap(map,0,0,null);

        //몬스터 배열
        for (Monster1 monster : monster1s){
            monster.onDraw(canvas);
        }

        //몬스터2 배열
        for (Monster2 monster : monster2s){
            monster.onDraw(canvas);
        }

        //파워 아이템 그리기
        for (ItemPower itP : itemPower){
            itP.onDraw(canvas);
        }

        //스킬 아이템 그리기
        for (ItemSkill itS : itemSkill){
            itS.onDraw(canvas);
        }



    }
}
