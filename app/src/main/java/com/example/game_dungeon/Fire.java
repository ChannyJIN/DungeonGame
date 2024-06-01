
package com.example.game_dungeon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Fire {
    private int x, y, sw; // 파이어볼의 위치
    private int speed; // 파이어볼의 속도
    private Bitmap image; // 파이어볼 이미지

    public Fire(Context context, int startX, int startY, int sw, int speed) {
        this.x = startX;
        this.y = startY;
        this.sw = sw;
        this.speed = speed;
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire); // 파이어볼 이미지 로드
        this.image = Bitmap.createScaledBitmap(this.image, 120, 80, false); // 이미지 크기 조정
    }

    // Move the fireball to the left
    public void moveLeft() {
        this.x -= speed;

    }

    // Draw the fireball on the canvas
    public void draw(Canvas canvas) {
        if (this.image != null) {
            canvas.drawBitmap(this.image, this.x, this.y, null);
        }
    }

    // Set the speed of the fireball
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Get the speed of the fireball
    public int getSpeed() {
        return speed;
    }
}
