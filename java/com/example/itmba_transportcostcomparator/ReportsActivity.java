package com.example.itmba_transportcostcomparator;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ReportsActivity extends BaseActivity {

    private TextView tvSummary;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        tvSummary = findViewById(R.id.tvSummary);
        dbHelper = new DatabaseHelper(this);

        Cursor cursor = dbHelper.getAllReports();
        double totalCost = 0;
        int count = 0;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                totalCost += cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MONTHLY_COST));
                count++;
            }
            while (cursor.moveToNext());
            cursor.close();
        }

        String summary = "Total Reports: " + count + "\n" + "Total Monthly Cost: R " + String.format("%.2f", totalCost) + "\n" + "Average Cost: R " + String.format("%.2f", count > 0 ? totalCost / count : 0);
        tvSummary.setText(summary);
    }
}
