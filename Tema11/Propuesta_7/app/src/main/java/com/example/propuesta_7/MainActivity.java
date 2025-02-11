package com.example.propuesta_7;

import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView mensaje;
    private ScrollView scrollView;
    private ScheduledThreadPoolExecutor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje = findViewById(R.id.mensaje);
        scrollView = findViewById(R.id.scrollView);
        Button btnIniciar = findViewById(R.id.btnIniciar);
        Button btnParar = findViewById(R.id.btnParar);

        executor = new ScheduledThreadPoolExecutor(2);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarTareas();
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararTareas();
            }
        });
    }

    private void iniciarTareas() {
        if (executor.isShutdown() || executor.isTerminated()) {
            executor = new ScheduledThreadPoolExecutor(2);
        }

        mensaje.append("\nINICIANDO TAREAS");

        executor.scheduleAtFixedRate(new Tarea1(), 0, 1, TimeUnit.SECONDS);
        executor.schedule(new Tarea2(), 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new Tarea3(), 0, 2, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new Tarea4(), 0, 2, TimeUnit.SECONDS);

        mensaje.append("\nTAMAÑO DEL POOL: " + executor.getPoolSize());
    }


    private void pararTareas() {
        mensaje.append("\nAPAGANDO EJECUCIÓN DE TAREAS");
        executor.shutdownNow();
    }

    class Tarea1 extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mensaje.append("\nEJECUTANDO TAREA 1");
                    mensaje.append("\nTAREA EN: " + Thread.currentThread().getName());
                }
            });
        }
    }

    class Tarea2 extends TimerTask {
        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mensaje.append("\nEJECUTANDO TAREA 2");
                    mensaje.append("\nTAREA EN: " + Thread.currentThread().getName());
                }
            });
        }
    }

    class Tarea3 implements Runnable {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mensaje.append("\nEJECUTANDO TAREA 3");
                    mensaje.append("\nTAREA EN: " + Thread.currentThread().getName());
                }
            });
        }
    }

    class Tarea4 implements Runnable {
        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mensaje.append("\nEJECUTANDO TAREA 4");
                    mensaje.append("\nTAREA EN: " + Thread.currentThread().getName());
                }
            });
        }
    }

}
