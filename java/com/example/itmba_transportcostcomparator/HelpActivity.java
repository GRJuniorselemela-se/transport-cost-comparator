package com.example.itmba_transportcostcomparator;

import android.os.Bundle;
import android.widget.Button;

public class HelpActivity extends BaseActivity {
    private Button btnExitHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        btnExitHelp = findViewById(R.id.btnExitHelp);
        btnExitHelp.setOnClickListener(v -> finish());
    }
}
