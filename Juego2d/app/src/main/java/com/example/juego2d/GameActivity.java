package com.example.juego2d;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private ImageView adventurerSprite;
    private Handler handler = new Handler();

    private Bitmap spriteSheet;
    private int frameIndex = 0;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;

    private static final int FRAME_DURATION = 100;
    private static final int MOVE_DISTANCE = 10;
    private static final int JUMP_HEIGHT = 200;
    private static final int GRAVITY = 10;

    private boolean isJumping = false;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private float originalY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Referencias
        adventurerSprite = findViewById(R.id.adventurer_sprite);
        originalY = adventurerSprite.getY();

        // Botones de control
        Button btnRight = findViewById(R.id.btn_right);
        Button btnLeft = findViewById(R.id.btn_left);
        Button btnJump = findViewById(R.id.btn_jump);

        // Cargar animación inicial
        loadSpriteSheet(R.drawable.adventurer_idle);
        startAnimation();

        // Movimiento continuo derecha
        btnRight.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                moveRight();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                isMovingRight = false;
                setIdleAnimation();
            }
            return true;
        });

        // Movimiento continuo izquierda
        btnLeft.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                moveLeft();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                isMovingLeft = false;
                setIdleAnimation();
            }
            return true;
        });

        // Saltar
        btnJump.setOnClickListener(v -> jump());
    }

    // Cargar el spritesheet dinámicamente
    private void loadSpriteSheet(int spriteRes) {
        spriteSheet = BitmapFactory.decodeResource(getResources(), spriteRes);
        int sheetWidth = spriteSheet.getWidth();
        frameHeight = spriteSheet.getHeight();
        frameCount = sheetWidth / frameHeight;
        frameWidth = sheetWidth / frameCount;
    }

    // Animar el sprite
    private void startAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateFrame();
                handler.postDelayed(this, FRAME_DURATION);
            }
        }, FRAME_DURATION);
    }

    private void updateFrame() {
        Bitmap currentFrame = Bitmap.createBitmap(spriteSheet, frameIndex * frameWidth, 0, frameWidth, frameHeight);
        adventurerSprite.setImageBitmap(currentFrame);
        frameIndex = (frameIndex + 1) % frameCount;
    }

    // Movimiento continuo
    private void moveCharacter() {
        new Thread(() -> {
            while (isMovingRight || isMovingLeft) {
                runOnUiThread(() -> {
                    if (isMovingRight) {
                        adventurerSprite.setX(adventurerSprite.getX() + MOVE_DISTANCE);
                    }
                    if (isMovingLeft) {
                        adventurerSprite.setX(adventurerSprite.getX() - MOVE_DISTANCE);
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Mover a la derecha
    private void moveRight() {
        adventurerSprite.setScaleX(1f); // Mirar a la derecha
        isMovingRight = true;
        setRunningAnimation();
        moveCharacter();
    }

    // Mover a la izquierda
    private void moveLeft() {
        adventurerSprite.setScaleX(-1f); // Mirar a la izquierda
        isMovingLeft = true;
        setRunningAnimation();
        moveCharacter();
    }

    // Saltar con gravedad
    private void jump() {
        if (!isJumping) {
            isJumping = true;
            setJumpingAnimation();

            new Thread(() -> {
                for (int i = 0; i < JUMP_HEIGHT / GRAVITY; i++) {
                    runOnUiThread(() -> adventurerSprite.setY(adventurerSprite.getY() - GRAVITY));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < JUMP_HEIGHT / GRAVITY; i++) {
                    runOnUiThread(() -> adventurerSprite.setY(adventurerSprite.getY() + GRAVITY));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(() -> {
                    if(isMovingRight || isMovingLeft){
                        setRunningAnimation();
                    }else{
                        setIdleAnimation();
                    }
                    isJumping = false;
                });
            }).start();
        }
    }

    // Cambiar a animación idle
    private void setIdleAnimation() {
        loadSpriteSheet(R.drawable.adventurer_idle);
        frameIndex = 0;
    }

    // Cambiar a animación running
    private void setRunningAnimation() {
        loadSpriteSheet(R.drawable.adventurer_running);
        frameIndex = 0;
    }

    // Cambiar a animación jumping
    private void setJumpingAnimation() {
        loadSpriteSheet(R.drawable.adventurer_flying);
        frameIndex = 0;
    }
}
