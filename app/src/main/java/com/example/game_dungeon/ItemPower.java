package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ItemPower {
    private int itemX, itemY;

    Bitmap item_power;
    public ItemPower(Context con, int itemX, int itemY){
        this.itemX = itemX;
        this.itemY = itemY;

        item_power = BitmapFactory.decodeResource(con.getResources(),R.drawable.item_power);
        item_power = Bitmap.createScaledBitmap(item_power, 150, 150, false);
    }
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(item_power,itemX,itemY,null);
    }
    public int getX(){return itemX;}
    public int getY(){return itemY;}
}
