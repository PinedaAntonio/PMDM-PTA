package com.example.juego2d_exe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView adventurerSprite;
    private Handler handler = new Handler();
    private Bitmap spriteSheet;
    private int frameIndex = 0;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;
    private static final int FRAME_DURATION = 100;
    private static final int MOVE_DISTANCE = 15;
    private static final int JUMP_HEIGHT = 200;
    private static final int GRAVITY = 10;
    private boolean isJumping = false;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private ArrayList<ImageView> obstacles = new ArrayList<>();
    private Random random = new Random();
    private int obstacleSpeed = 5;
    private int spawnRate = 3000;
    private boolean isGameOver = false;
    private int score = 0;
    private TextView tvScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SoundEffects.startGameMusic(this);

        adventurerSprite = findViewById(R.id.adventurer_sprite);

        Button btnRight = findViewById(R.id.btn_right);
        Button btnLeft = findViewById(R.id.btn_left);
        Button btnJump = findViewById(R.id.btn_jump);

        tvScore = findViewById(R.id.tv_score);
        startScoreCounter();

        // Restablecer variables globales al reiniciar
        isGameOver = false;
        isJumping = false;
        obstacles.clear();

        // Asegurar que el contenedor no tenga obstáculos previos
        ViewGroup gameContainer = findViewById(R.id.game_container);
        gameContainer.removeAllViews(); // Limpia todos los elementos de la pantalla
        gameContainer.addView(adventurerSprite); // Reagrega al aventurero

        loadSpriteSheet(R.drawable.adventurer_idle);
        startAnimation();

        btnRight.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                moveRight();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                isMovingRight = false;
                setIdleAnimation();
            }
            return true;
        });

        btnLeft.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                moveLeft();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                isMovingLeft = false;
                setIdleAnimation();
            }
            return true;
        });

        btnJump.setOnClickListener(v -> jump());

        startObstacleSpawner(); // Iniciar la creación de obstáculos desde cero
    }


    private void loadSpriteSheet(int spriteRes) {
        spriteSheet = BitmapFactory.decodeResource(getResources(), spriteRes);
        int sheetWidth = spriteSheet.getWidth();
        frameHeight = spriteSheet.getHeight();
        frameCount = sheetWidth / frameHeight;
        frameWidth = sheetWidth / frameCount;
    }

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

    private void moveCharacter() {
        new Thread(() -> {
            while (isMovingRight || isMovingLeft) {
                runOnUiThread(() -> {
                    float newX = adventurerSprite.getX();
                    float screenWidth = getResources().getDisplayMetrics().widthPixels;
                    float characterWidth = adventurerSprite.getWidth();

                    //Movimiento a la derecha
                    if (isMovingRight && newX + MOVE_DISTANCE + characterWidth <= screenWidth) {
                        adventurerSprite.setX(newX + MOVE_DISTANCE);
                    }

                    //Movimiento a la izquierda
                    if (isMovingLeft && newX - MOVE_DISTANCE >= 0) {
                        adventurerSprite.setX(newX - MOVE_DISTANCE);
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


    private void moveRight() {
        adventurerSprite.setScaleX(1f);
        isMovingRight = true;
        setRunningAnimation();
        moveCharacter();
    }

    private void moveLeft() {
        adventurerSprite.setScaleX(-1f);
        isMovingLeft = true;
        setRunningAnimation();
        moveCharacter();
    }

    private void jump() {
        if (!isJumping && !isGameOver) {
            isJumping = true;
            setJumpingAnimation();

            new Thread(() -> {
                // Movimiento hacia arriba
                for (int i = 0; i < JUMP_HEIGHT / GRAVITY; i++) {
                    if (isGameOver) return;

                    runOnUiThread(() -> {
                        adventurerSprite.setY(adventurerSprite.getY() - GRAVITY);

                        for (ImageView obstacle : obstacles) {
                            if (checkCollision(adventurerSprite, obstacle)) {
                                gameOver();
                                return; // Detiene la ejecución dentro de runOnUiThread
                            }
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isGameOver) return;
                }

                // Movimiento hacia abajo
                for (int i = 0; i < JUMP_HEIGHT / GRAVITY; i++) {
                    if (isGameOver) return;

                    runOnUiThread(() -> {
                        adventurerSprite.setY(adventurerSprite.getY() + GRAVITY);

                        for (ImageView obstacle : obstacles) {
                            if (checkCollision(adventurerSprite, obstacle)) {
                                gameOver();
                                return;
                            }
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isGameOver) return;
                }

                runOnUiThread(() -> {
                    if (!isGameOver) {
                        if (isMovingRight || isMovingLeft) {
                            setRunningAnimation();
                        } else {
                            setIdleAnimation();
                        }
                        isJumping = false;
                    }
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

    private int maxObstaclesPerSpawn = 1;

    private void startObstacleSpawner() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ajustar la dificultad en base a la puntuación
                maxObstaclesPerSpawn = Math.min(1 + (score / 50), 7); // Aumenta cada 50 puntos, máx. 7

                // Generar obstáculos desde arriba
                for (int i = 0; i < maxObstaclesPerSpawn; i++) {
                    spawnObstacle();
                }

                // 1 de cada 3 ciclos genera un obstáculo lateral
                if (random.nextInt(3) == 0) {
                    spawnSideObstacle();
                }

                // Reducir el tiempo de aparición de obstáculos basado en la puntuación
                spawnRate = Math.max(1000, 3000 - (score * 10));

                // Repetir el spawn
                handler.postDelayed(this, spawnRate);
            }
        }, spawnRate);
    }


    private void spawnObstacle() {
        runOnUiThread(() -> {
            ViewGroup gameContainer = findViewById(R.id.game_container);

            if (gameContainer != null) {
                ImageView obstacle = new ImageView(this);
                obstacle.setImageResource(R.drawable.obstacle);

                // Establecer tamaño del obstáculo
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(80, 80);
                obstacle.setLayoutParams(params);

                // Posición del obstáculo en la parte superior de la pantalla, con X aleatoria
                obstacle.setX(random.nextInt(getResources().getDisplayMetrics().widthPixels - 80));
                obstacle.setY(0);

                // Agregar el obstáculo al contenedor del juego
                gameContainer.addView(obstacle);
                obstacles.add(obstacle);

                // Mover el obstáculo
                moveObstacle(obstacle);
            }
        });
    }

    private void spawnSideObstacle() {
        runOnUiThread(() -> {
            ViewGroup gameContainer = findViewById(R.id.game_container);

            if (gameContainer != null) {
                ImageView sideObstacle = new ImageView(this);
                sideObstacle.setImageResource(R.drawable.side_obstacle);

                // Establecer tamaño del obstáculo
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(80, 80);
                sideObstacle.setLayoutParams(params);

                // Determinar si aparece por la izquierda o la derecha
                boolean fromLeft = random.nextBoolean();
                if (fromLeft) {
                    sideObstacle.setX(0); // Aparece en el borde izquierdo
                    sideObstacle.setScaleX(-1f);
                } else {
                    sideObstacle.setX(getResources().getDisplayMetrics().widthPixels - 80); // Aparece en el borde derecho
                    sideObstacle.setScaleX(1f);
                }

                // Posición del obstáculo lateral
                sideObstacle.setY(adventurerSprite.getY() + 20);

                // Agregarlo al contenedor
                gameContainer.addView(sideObstacle);
                obstacles.add(sideObstacle);
                SoundEffects.playArrowSound(this);

                // Mover el obstáculo
                moveSideObstacle(sideObstacle, fromLeft);
            }
        });
    }


    private void moveSideObstacle(ImageView sideObstacle, boolean fromLeft) {
        new Thread(() -> {
            int speed = 15; // Velocidad de los obstáculos laterales
            float screenWidth = getResources().getDisplayMetrics().widthPixels;

            while (!isGameOver) {
                runOnUiThread(() -> {
                    float newX = sideObstacle.getX() + (fromLeft ? speed : -speed);

                    // Verificar colisión con el aventurero
                    if (checkCollision(adventurerSprite, sideObstacle)) {
                        gameOver();
                        return;
                    }

                    // Eliminar el obstáculo si no hay colisión una vez llegue al final
                    if (newX < -sideObstacle.getWidth() || newX > screenWidth) {
                        ViewGroup parent = (ViewGroup) sideObstacle.getParent();
                        if (parent != null) {
                            parent.removeView(sideObstacle);
                        }
                        obstacles.remove(sideObstacle);
                        return;
                    }

                    // Mover el obstáculo
                    sideObstacle.setX(newX);
                });

                try {
                    Thread.sleep(30); // Sleep para controlar la velocidad del movimiento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void moveObstacle(ImageView obstacle) {
        new Thread(() -> {
            while (obstacle.getY() < getResources().getDisplayMetrics().heightPixels) {
                runOnUiThread(() -> {
                    obstacle.setY(obstacle.getY() + obstacleSpeed);

                    // Verificar colisión con el aventurero
                    if (checkCollision(obstacle, adventurerSprite)) {
                        gameOver();
                    }
                });

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Eliminar el obstáculo si no hay colisión una vez llegue al final
            runOnUiThread(() -> {
                ViewGroup parent = (ViewGroup) obstacle.getParent();
                if (parent != null) {
                    parent.removeView(obstacle);
                }
                obstacles.remove(obstacle);
            });

        }).start();
    }

    private boolean checkCollision(ImageView obj1, ImageView obj2) {
        float obj1Left = obj1.getX() + 10;
        float obj1Right = obj1.getX() + obj1.getWidth() - 10;
        float obj1Top = obj1.getY() + 10;
        float obj1Bottom = obj1.getY() + obj1.getHeight() - 10;

        float obj2Left = obj2.getX() + 10;
        float obj2Right = obj2.getX() + obj2.getWidth() - 10;
        float obj2Top = obj2.getY() + 10;
        float obj2Bottom = obj2.getY() + obj2.getHeight() - 10;

        return obj1Left < obj2Right &&
                obj1Right > obj2Left &&
                obj1Top < obj2Bottom &&
                obj1Bottom > obj2Top;
    }

    private void gameOver() {
        if (isGameOver) return;
        isGameOver = true;
        isJumping = false; // Detener animaciones en curso

        runOnUiThread(() -> {
            // Detener todos los obstáculos en pantalla
            for (ImageView obstacle : obstacles) {
                ViewGroup parent = (ViewGroup) obstacle.getParent();
                if (parent != null) {
                    parent.removeView(obstacle);
                }
            }
            obstacles.clear(); // Limpiar la lista de obstáculos

            // Cancelar cualquier evento en el Handler para detener spawners
            handler.removeCallbacksAndMessages(null);

            // Ir a la pantalla de Game Over
            Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            intent.putExtra("score", score); // Enviar puntuación
            startActivity(intent);
            finish();

        });
    }

    private void startScoreCounter() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isGameOver) {
                    score++;
                    tvScore.setText("Puntos: " + score);
                    handler.postDelayed(this, 100); // Aumenta puntos cada 0.1 segundo
                }
            }
        }, 1000);
    }

    //Métodos para el sonido

    @Override
    protected void onPause() {
        super.onPause();
        SoundEffects.pauseGameMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SoundEffects.resumeGameMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundEffects.stopGameMusic();
    }
}