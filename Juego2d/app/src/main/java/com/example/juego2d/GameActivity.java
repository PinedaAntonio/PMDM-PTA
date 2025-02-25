package com.example.juego2d;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private ImageView adventurerSprite;
    private Handler handler = new Handler();

    private Bitmap spriteSheet;
    private ImageView groundImage;
    private int frameIndex = 0;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;

    private static final int FRAME_DURATION = 100; // Tiempo entre frames en ms
    private static final int JUMP_HEIGHT = 200; // Altura del salto
    private static final int MOVE_DISTANCE = 50; // Distancia por pulsación

    private boolean isJumping = false;
    private float originalY; // Para controlar el salto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Referencias
        adventurerSprite = findViewById(R.id.adventurer_sprite);
        originalY = adventurerSprite.getY(); // Guardar posición inicial

        // Botones de control
        Button btnRight = findViewById(R.id.btn_right);
        Button btnLeft = findViewById(R.id.btn_left);
        Button btnJump = findViewById(R.id.btn_jump);

        // Cargar animación inicial
        loadSpriteSheet(R.drawable.adventurer_idle);
        startAnimation();

        // Referencia al suelo
        groundImage = findViewById(R.id.ground);

        // Aplicar textura repetitiva
        applyRepeatingTexture();

        // Movimiento derecha
        btnRight.setOnClickListener(v -> moveRight());

        // Movimiento izquierda
        btnLeft.setOnClickListener(v -> moveLeft());

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

    // Animar el sprite cambiando los frames
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

    // Mover a la derecha
    private void moveRight() {
        adventurerSprite.setX(adventurerSprite.getX() + MOVE_DISTANCE);
        setRunningAnimation();
    }

    // Mover a la izquierda
    private void moveLeft() {
        adventurerSprite.setX(adventurerSprite.getX() - MOVE_DISTANCE);
        setRunningAnimation();
    }

    // Saltar
    private void jump() {
        if (!isJumping) {
            isJumping = true;
            setJumpingAnimation();

            // Subir
            adventurerSprite.animate().y(originalY - JUMP_HEIGHT).setDuration(300).withEndAction(() -> {
                // Bajar
                adventurerSprite.animate().y(originalY).setDuration(300).withEndAction(() -> {
                    setIdleAnimation();
                    isJumping = false;
                });
            });
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

    private void applyRepeatingTexture() {
        // Cargar el bitmap del suelo
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.temple_ground);

        // Crear un BitmapDrawable y habilitar la repetición
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        // Aplicar al ImageView
        groundImage.setImageDrawable(bitmapDrawable);
    }
}
