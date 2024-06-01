package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ItemSkill {
    private int itemX, itemY;

    Bitmap item_skill;
    public ItemSkill(Context con, int itemX, int itemY){
        this.itemX = itemX;
        this.itemY = itemY;

        item_skill = BitmapFactory.decodeResource(con.getResources(),R.drawable.item_skil);
        item_skill = Bitmap.createScaledBitmap(item_skill, 150, 150, false);
    }
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(item_skill,itemX,itemY,null);
    }
    public int getX(){return itemX;}
    public int getY(){return itemY;}
}
