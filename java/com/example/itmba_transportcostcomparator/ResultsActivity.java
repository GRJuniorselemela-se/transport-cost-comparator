package com.example.itmba_transportcostcomparator;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends BaseActivity {

    private TextView tvModeOfTransport;
    private TextView tvDailyTransportCost;
    private TextView tvMonthlyTransportCost;
    private TextView tvMonthlyDistance;
    private TextView tvCostCategory;
    private TextView tvRecommendation;
    private Button btnCalculateAgain;
    private Button btnExit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvModeOfTransport = findViewById(R.id.tvModeOfTransport);
        tvDailyTransportCost = findViewById(R.id.tvDailyTransportCost);
        tvMonthlyTransportCost = findViewById(R.id.tvMonthlyTransportCost);
        tvMonthlyDistance = findViewById(R.id.tvMonthlyDistance);
        tvCostCategory = findViewById(R.id.tvCostCategory);
        tvRecommendation = findViewById(R.id.tvRecommendation);
        btnCalculateAgain = findViewById(R.id.btnCalculateAgain);
        btnExit = findViewById(R.id.btnExit);

        dbHelper = new DatabaseHelper(this);

        long reportId = getIntent().getLongExtra("REPORT_ID", -1);
        if (reportId == -1) {
            Toast.makeText(this, "No report found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayReport(reportId);

        btnCalculateAgain.setOnClickListener(v->finish());
        btnExit.setOnClickListener(v->finishAffinity());
    }

    private void displayReport(long reportId) {
        Cursor cursor = dbHelper.getReport(reportId);
        if (cursor != null && cursor.moveToFirst()) {
            String mode = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MODE));
            double dailyCost = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DAILY_COST));
            double monthlyCost = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MONTHLY_COST));
            double monthlyDistance = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MONTHLY_DISTANCE));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY));
            String recommendation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RECOMMENDATION));

            tvModeOfTransport.setText("Mode: " + mode);
            tvDailyTransportCost.setText(String.format("R %.2f", dailyCost));
            tvMonthlyTransportCost.setText(String.format("R %.2f", monthlyCost));
            tvMonthlyDistance.setText(String.format("%.2f km", monthlyDistance));
            tvCostCategory.setText("Category: " + category);
            tvRecommendation.setText("Tip: " + recommendation);

            cursor.close();
        }
        else {
            Toast.makeText(this, "Error loading report", Toast.LENGTH_SHORT).show();
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
