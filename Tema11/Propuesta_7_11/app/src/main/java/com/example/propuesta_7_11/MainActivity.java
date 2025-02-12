package com.example.propuesta_7_11;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.hardware.Camera;
import android.media.MediaRecorder;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btnGrabar, btnPausar, btnReproducir;
    private Camera camera;
    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    private boolean isPaused = false;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGrabar = findViewById(R.id.btnGrabar);
        btnPausar = findViewById(R.id.btnPausar);
        btnReproducir = findViewById(R.id.btnReproducir);
        surfaceView = findViewById(R.id.cameraPreview);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                requestPermissions();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releaseCamera();
            }
        });

        btnGrabar.setOnClickListener(v -> {
            if (!isRecording) {
                startRecording();
            }
        });

        btnPausar.setOnClickListener(v -> {
            if (isRecording) {
                pauseRecording();
            }
        });

        btnReproducir.setOnClickListener(v -> {
            if (isRecording) {
                stopRecording();
            } else {
                startPlayback();
            }
        });
    }
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                }, REQUEST_CAMERA_PERMISSION);
            }

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        } else {
            initializeCamera();
        }
    }


    private void initializeCamera() {
        try {
            camera = Camera.open();
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startRecording() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }

        File outputFile = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "video.mp4");

        mediaRecorder = new MediaRecorder();
        try {
            camera.unlock();
            mediaRecorder.setCamera(camera);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(outputFile.getAbsolutePath());
            mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            isPaused = false;

            Toast.makeText(MainActivity.this, "Grabación iniciada", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            releaseCamera();
            Toast.makeText(MainActivity.this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (isRecording) {
            try {
                mediaRecorder.stop();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            Toast.makeText(MainActivity.this, "Grabación detenida", Toast.LENGTH_SHORT).show();
        }
    }

    private void pauseRecording() {
        if (isRecording && !isPaused) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaRecorder.pause();
                isPaused = true;
                Toast.makeText(MainActivity.this, "Grabación pausada", Toast.LENGTH_SHORT).show();
            } else {
                stopRecording();
                startRecording();
                Toast.makeText(MainActivity.this, "Grabación detenida. Se reiniciará la grabación.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startPlayback() {
        File outputFile = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "video.mp4");

        if (outputFile.exists()) {
            VideoView videoView = findViewById(R.id.videoView);
            Uri videoUri = Uri.fromFile(outputFile);
            videoView.setVideoURI(videoUri);

            videoView.setOnPreparedListener(mp -> videoView.start());
            videoView.setOnErrorListener((mp, what, extra) -> {
                Toast.makeText(MainActivity.this, "Error al reproducir el video", Toast.LENGTH_SHORT).show();
                return true;
            });
        } else {
            Toast.makeText(this, "El video no existe", Toast.LENGTH_SHORT).show();
        }
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeCamera();
            }
        }
    }
}
