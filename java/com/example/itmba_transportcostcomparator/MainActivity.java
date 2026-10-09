package com.example.itmba_transportcostcomparator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private Button btnStartCalculation;
    private Button btnListenToMe;
    private Button btnExit;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartCalculation = findViewById(R.id.btnStartCalculation);
        btnListenToMe = findViewById(R.id.btnListenToMe);
        btnExit = findViewById(R.id.btnExit);

        btnStartCalculation.setOnClickListener(v-> {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
        });

        btnListenToMe.setOnClickListener(v-> playAudio());

        btnExit.setOnClickListener(v-> {
                finish();
                System.exit(0);
        });
    }

    private void playAudio() {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.transport_audio);
            }

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnListenToMe.setText("Listen to Me");
            }
            else {
                mediaPlayer.start();
                btnListenToMe.setText("Pause");
                mediaPlayer.setOnCompletionListener(mp -> btnListenToMe.setText("Listen to Me"));
            }

            }
            catch(Exception e){
                Toast.makeText(this, "Audio file not found", Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}