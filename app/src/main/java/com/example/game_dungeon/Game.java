package com.example.game_dungeon;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game extends View {

    int sw, sh,esw,esh;                         //화면 크기(가로 세로)\
    Context con;
    AttributeSet at;

    private boolean start=true;     //게임 오버를 의미
    private boolean complete = false;   // 완료를 의미
    private boolean gameOver=false;     //게임 오버를 의미
    private int round;
    private User user;
    private Map map;
    private Bitmap princess;        //공주 비트맵(파이널 라운드)



    //라운드 표시
    private Paint roundPaint;
    private Handler timerHandler = new Handler();
    private int time;  // 타이머 시간 (초 단위)


    //파이어볼
    private List<Fire> fireballs = new ArrayList<>();
    private Handler handler = new Handler();    //파이어볼 생성 핸들러
    private Handler handler2 = new Handler();




    public Game(Context con, AttributeSet at){
        super(con, at);
        roundPaint = new Paint();
        this.con = con;
        this.at=at;
        user = new User(con, 180, 80);
        round=1;
        onSizeChanged(sw,sh,esw,esh);

    }

    //타이머
    private void startTimer() {
        timerHandler.removeCallbacks(timerRunnable); // 기존 타이머 콜백 제거
        timerHandler.postDelayed(timerRunnable, 1000);
    }
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (time > 0) {
                time--;
                for (Fire fireball : fireballs) {
                    fireball.moveLeft(); // 각 파이어볼의 속도 사용
                }
                invalidate();  // 화면 갱신
                timerHandler.postDelayed(this, 1000);  // 1초 후에 다시 실행
            } else {
                // 시간 초과 처리
                loadMap(round);  // 맵 다시 로드
                user.power=10;
                user.mp=0;
            }
        }
    };


    //파이어볼 이동 관련 타이머
    private void startTimer3() {            //파이어볼 이동 타이머
        handler2.removeCallbacks(moveFire); // 기존 타이머 콜백 제거
        handler2.postDelayed(moveFire, 200);
    }
    private Runnable moveFire = new Runnable() {        //파이어볼 이동타이머
        @Override
        public void run() {
            for (Fire fireball : fireballs) {
                fireball.moveLeft(); // 각 파이어볼의 속도 사용
            }
            invalidate();  // 화면 갱신
            handler2.postDelayed(this, 200);  // 1초 후에 다시 실행
        }
    };


    //파이어볼 추가 타이머 관련
    private void startTimer2() {        //파이어볼 생성 타이머
        handler.removeCallbacks(gameUpdateRunnable); // 기존 타이머 콜백 제거
        handler.postDelayed(gameUpdateRunnable, 1000);
    }
    private Runnable gameUpdateRunnable = new Runnable() {      //파이어볼 생성
        @Override
        public void run() {
            addFireball(); // 일정 시간 간격으로 파이어볼 추가
            for (Fire fireball : fireballs) {
                fireball.moveLeft(); // 각 파이어볼의 속도 사용
            }
            invalidate(); // 화면 갱신
            handler.postDelayed(this, 5000);
        }
    };
    public void addFireball() {
        int startY = new Random().nextInt(sh - 100); // 랜덤한 높이에서 시작
        int speed = new Random().nextInt(10) + 15; // 랜덤한 속도 설정 (15에서 24까지)
        fireballs.add(new Fire(con, sw - 100, startY, sw, speed)); // 화면 오른쪽 끝에 파이어볼 추가
        invalidate();
    }


    //화면 사이즈 반환 함수
    protected void onSizeChanged(int sw, int sh, int esw, int esh){
        super.onSizeChanged(sw, sh, esw, esh);
        this.sw = sw;
        this.sh = sh;
        loadMap(round);

    }

    //맵 로드 함수
    public void loadMap(int round){
        user.userX=180;
        user.userY=80;
        time=70;
        complete = false;
        startTimer();   //게임 타임의 타이머스타트
        List<Point> monsterPosition1s = new ArrayList<>();
        List<Point> monsterPosition2s = new ArrayList<>();
        List<Point> powerItemPositions = new ArrayList<>();
        List<Point> skillItemPositions = new ArrayList<>();
        int mapResource;
        if (round==1){

            mapResource = R.drawable.dunjeon;
            monsterPosition1s.add(new Point(1800, 80));
            monsterPosition1s.add(new Point(1800, 700));
            monsterPosition1s.add(new Point(1800, 700));
            monsterPosition2s.add(new Point(1700, 300));
            monsterPosition2s.add(new Point(1000, 600));
            monsterPosition2s.add(new Point(500, 400));
            powerItemPositions.add(new Point(1200,500));
            skillItemPositions.add(new Point(1050,110));

        } else if (round==2) {
            mapResource = R.drawable.dungeon2;
            monsterPosition1s.add(new Point(1800, 80));
            monsterPosition1s.add(new Point(900, 450));
            monsterPosition1s.add(new Point(1800, 700));
            monsterPosition1s.add(new Point(1050, 200));
            monsterPosition2s.add(new Point(1600, 300));
            monsterPosition2s.add(new Point(1000, 600));
            monsterPosition2s.add(new Point(500, 400));
            powerItemPositions.add(new Point(1200,500));
            skillItemPositions.add(new Point(1000,80));
            skillItemPositions.add(new Point(400,100));
        }
        else if (round==3) {
            mapResource = R.drawable.dungeon3;
            monsterPosition1s.add(new Point(1700, 80));
            monsterPosition1s.add(new Point(900, 450));
            monsterPosition1s.add(new Point(1600, 700));
            monsterPosition1s.add(new Point(1050, 200));
            monsterPosition2s.add(new Point(1500, 300));
            monsterPosition2s.add(new Point(800, 500));
            monsterPosition2s.add(new Point(500, 300));
            monsterPosition2s.add(new Point(1400, 600));
            skillItemPositions.add(new Point(1000,80));
            skillItemPositions.add(new Point(400,450));
            skillItemPositions.add(new Point(800,700));
            princess = BitmapFactory.decodeResource(con.getResources(), R.drawable.princess);
            princess = Bitmap.createScaledBitmap(princess, 200, 230, false);
            startTimer2();  //파이어볼 생성 타이머
            startTimer3();  //파이어볼 움직이는 타이머



        }
        else {

            return;
        }


        map = new Map(con, sw,sh,mapResource, monsterPosition1s, monsterPosition2s,powerItemPositions,skillItemPositions);

    }
    


    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if (map != null) {
            map.draw(canvas);
        }
        user.onDraw(canvas);

        //라운드 표시
        roundPaint.setColor(Color.WHITE);
        roundPaint.setTextSize(60);
        roundPaint.setTextAlign(Paint.Align.CENTER);
        if(round==3){
            canvas.drawText("Final Round" , sw / 2, 80, roundPaint);
            canvas.drawBitmap(princess, 2000, 500, null);

            for (Fire fireball : fireballs) {
                fireball.draw(canvas); // 파이어볼 그리기
            }
        }
        else {
            canvas.drawText("Round: " + round, sw / 2, 80, roundPaint);
        }


        //타이머 표시
        canvas.drawText("Timer: " + time, 10, 30, user.textP);  // 유저 클래스의 Paint 객체를 재사용

        //파이널 라운드에서 몬스터를 모두 없애면 성공 메세지
        if (complete) {
            princess = Bitmap.createScaledBitmap(princess, 400, 460, false);
            canvas.drawBitmap(princess, 400, 400, null);

            Paint completePaint = new Paint();
            completePaint.setColor(Color.GREEN);
            completePaint.setTextSize(100);
            completePaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Complete", sw / 2, sh / 2, completePaint);
            timerHandler.removeCallbacks(timerRunnable);
            handler.removeCallbacks(gameUpdateRunnable);
            handler2.removeCallbacks(moveFire);

            //다시하기 버튼
            Paint retryPaint = new Paint();
            retryPaint.setColor(Color.WHITE);
            retryPaint.setTextSize(60);
            retryPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawRect(sw / 2 - 200, sh / 2 + 50, sw / 2 + 200, sh / 2 + 150, retryPaint);
            retryPaint.setColor(Color.BLACK);
            canvas.drawText("Retry", sw / 2, sh / 2 + 120, retryPaint);
        }
        // 게임 오버 상태일 때 메시지 표시

        if (gameOver) {
            Paint gameOverPaint = new Paint();
            gameOverPaint.setColor(Color.RED);
            gameOverPaint.setTextSize(100);
            gameOverPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Game Over", sw / 2, sh / 2, gameOverPaint);
            //다시하기 버튼 생성
            Paint retryPaint = new Paint();
            retryPaint.setColor(Color.WHITE);
            retryPaint.setTextSize(60);
            retryPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawRect(sw / 2 - 200, sh / 2 + 50, sw / 2 + 200, sh / 2 + 150, retryPaint);
            retryPaint.setColor(Color.BLACK);
            canvas.drawText("Retry", sw / 2, sh / 2 + 120, retryPaint);
        }
        checkMonsters();    //몬스터를 모두 없앴는지 감지
        checkCollisions();  //몬스터와 충돌 감지
    }

    //게임 리셋 함수
    public void resetGame() {
        round = 1;
        user.hp = 100; // 유저 HP 초기화
        user.mp = 0;    //유저 mp초기화
        user.power=10;   //유저 power 초기화
        gameOver = false;
        loadMap(round);
        startTimer();
        invalidate();
    }
    
    //다시하기 버튼 터치이벤트
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (gameOver||complete) {
                float x = event.getX();
                float y = event.getY();
                if (x >= sw / 2 - 200 && x <= sw / 2 + 200 && y >= sh / 2 + 50 && y <= sh / 2 + 150) {
                    resetGame(); // 게임 초기화
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void checkCollisions() {
        // Monster1 클래스 몬스터와의 충돌 검사
        for (Monster1 monster : map.monster1s) {
            if (isCollision(user.userX, user.userY, monster.getX(), monster.getY())) {
                user.minusHp(10);       //hp20씩 깎임
            }
        }

        // Monster2 클래스 몬스터와의 충돌 검사
        for (Monster2 monster : map.monster2s) {
            if (isCollision(user.userX, user.userY, monster.getX(), monster.getY())) {
                user.minusHp(10);       //hp20씩 깎임
            }
        }
        if(user.hp==0){
            gameOver=true;
        }

    }

    private boolean isCollision(int x1, int y1, int x2, int y2) {
        // 충돌 감지 로직 (간단한 사각형 충돌 감지)
        int threshold = 100; // 충돌 감지 임계값 (원하는 값으로 조절 가능)
        return Math.abs(x1 - x2) < threshold && Math.abs(y1 - y2) < threshold;
    }

    public void checkMonsters() {                       //몬스터 검사
        if (map.monster1s.isEmpty() && map.monster2s.isEmpty()) {
            if (round == 3) {
                complete = true; // 라운드 3에서 모든 몬스터를 죽였을 때 complete 플래그 설정
            } else {
                nextRound(); // 다른 라운드에서는 다음 라운드로 넘어감
            }
        }
    }
    public void nextRound(){                //다음 라운드 로직 함수
        round++;
        if (round > 3) {  // maxRound는 게임의 최대 라운드를 의미
            round = 1;  // 또는 게임 종료 로직
        }
        loadMap(round);
        invalidate();
    }

    //유저 이동함수
    public void moveUser(int userX,int userY){
        user.moveUser(userX,userY);
        // 파워 아이템과의 충돌 검사
        Iterator<ItemPower> powerIter = map.itemPower.iterator();
        while (powerIter.hasNext()) {
            ItemPower powerItem = powerIter.next();
            if (user.userX -50 <= powerItem.getX() && powerItem.getX() <= user.userX + 70
                    && user.userY-100 <= powerItem.getY() && powerItem.getY()<=user.userY+150) {
                user.powerUp(20);  // 파워 업
                powerIter.remove();  // 아이템을 지도에서 제거
                invalidate();
            }
        }

        // 스킬 아이템과의 충돌 검사
        Iterator<ItemSkill> skillIter = map.itemSkill.iterator();
        while (skillIter.hasNext()) {
            ItemSkill skillItem = skillIter.next();
            if (user.userX - 100 <= skillItem.getX() && skillItem.getX() <= user.userX + 100
                    && user.userY-60 <= skillItem.getY() && skillItem.getY()<=user.userY+150) {
                user.mpUp(100);
                skillIter.remove();  // 아이템을 지도에서 제거
            }
        }

        invalidate();
    }

    //유저 기본공격
    public void attackUser(int attack){
        if (attack==1){                     //공격버튼을 눌렀을때
            user.startAttack();             //공격 이미지로 바꿔줌

            // Monster1 클래스 몬스터 처리
            Iterator<Monster1> iterator1 = map.monster1s.iterator();
            while (iterator1.hasNext()) {
                Monster1 monster = iterator1.next();
                //공격 범위 들어왔을때
                if (user.userX + 50 <= monster.getX() && monster.getX() <= user.userX + 220 && user.userY-90 <= monster.getY() && monster.getY()<=user.userY+120) {
                    monster.setHp(user.power);      //유저의 파워만큼 몬스터1 hp 줄이기
                    if(monster.getHp()<=0){
                        iterator1.remove();     // hp가 0이하면 몬스터1 삭제
                    }

                }
            }

            // Monster2 클래스 몬스터 처리
            Iterator<Monster2> iterator2 = map.monster2s.iterator();
            while (iterator2.hasNext()) {
                Monster2 monster = iterator2.next();
                //공격 범위 들어왔을때
                if (user.userX + 50 <= monster.getX() && monster.getX() <= user.userX + 220 && user.userY - 90 <= monster.getY() && monster.getY() <= user.userY + 120) {
                    monster.setHp(user.power);      //유저의 파워만큼 몬스터2 hp 줄이기
                    if(monster.getHp()<=0){
                        iterator2.remove();     // hp가 0이하면 사용해 몬스터2 삭제
                    }
                }
            }
        }
        else {
            user.endAttack();
        }

        invalidate();
    }

    //유저 스킬 모션들
    public void skillUser_1(int skill){
        if(skill == 1 && user.mp>=100){
            user.startSkill_1();       //스킬 모션으로 바꿈
        }
        else {
            user.endAttack();       //다시 기본 모션으로 바꿈
        }
    }
    public void skillUser_2(int skill){
        if(skill == 1 && user.mp>=100){
            user.startSkill_2();
            //스킬에 몬스터가 맞으면 즉사 (스킬 파워 : 100)
            //Monster1 클래스 몬스터 처리
            Iterator<Monster1> iterator1 = map.monster1s.iterator();
            while (iterator1.hasNext()) {
                Monster1 monster = iterator1.next();
                //공격 범위 들어왔을때
                if (user.userX + 50 <= monster.getX() && monster.getX() <= user.userX + 400 && user.userY-90 <= monster.getY() && monster.getY()<=user.userY+120) {
                    monster.setHp(100);      //유저의 파워만큼 몬스터1 hp 줄이기
                    if(monster.getHp()<=0){
                        iterator1.remove();     // hp가 0이하면 몬스터1 삭제
                    }

                }
            }
            // Monster2 클래스 몬스터 처리
            Iterator<Monster2> iterator2 = map.monster2s.iterator();
            while (iterator2.hasNext()) {
                Monster2 monster = iterator2.next();
                //공격 범위 들어왔을때
                if (user.userX + 50 <= monster.getX() && monster.getX() <= user.userX + 400 && user.userY - 90 <= monster.getY() && monster.getY() <= user.userY + 120) {
                    monster.setHp(100);      //유저의 파워만큼 몬스터2 hp 줄이기
                    if(monster.getHp()<=0){
                        iterator2.remove();     // hp가 0이하면 사용해 몬스터2 삭제
                    }
                }
            }
            user.mp-=100;                //mp 0으로 초기화
        }
        else {
            user.endAttack();
        }
        invalidate();
    }
}
