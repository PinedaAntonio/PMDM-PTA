package com.example.juego2d_exe;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundEffects {
    private static MediaPlayer gameMusic;
    private static float musicVolume = 0.15f; // Volumen para música
    private static float effectsVolume = 1.0f; // Volumen para efectos de sonido

    // Reproducir sonido de flecha con volumen ajustable
    public static void playArrowSound(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.arrow_sound);
        mediaPlayer.setVolume(effectsVolume, effectsVolume);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }

    // Reproducir sonido de introducción (botón de inicio o reinicio)
    public static void playIntroSound(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.intro_sound);
        mediaPlayer.setVolume(effectsVolume, effectsVolume);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }

    // Iniciar música en bucle con volumen ajustable
    public static void startGameMusic(Context context) {
        if (gameMusic == null) {
            gameMusic = MediaPlayer.create(context, R.raw.game_sound);
            gameMusic.setLooping(true);
            gameMusic.setVolume(musicVolume, musicVolume);
            gameMusic.start();
        }
    }

    // Pausar la música cuando la app se minimiza
    public static void pauseGameMusic() {
        if (gameMusic != null && gameMusic.isPlaying()) {
            gameMusic.pause();
        }
    }

    // Reanudar la música cuando la app vuelve al primer plano
    public static void resumeGameMusic() {
        if (gameMusic != null) {
            gameMusic.start();
        }
    }

    // Detener la música cuando termine el juego o haya Game Over
    public static void stopGameMusic() {
        if (gameMusic != null) {
            gameMusic.stop();
            gameMusic.release();
            gameMusic = null;
        }
    }

    // Ajustar el volumen de la música del juego
    public static void setGameMusicVolume(float volume) {
        musicVolume = volume;
        if (gameMusic != null) {
            gameMusic.setVolume(volume, volume);
        }
    }

    // Ajustar el volumen de los efectos de sonido
    public static void setEffectsVolume(float volume) {
        effectsVolume = volume;
    }
}
