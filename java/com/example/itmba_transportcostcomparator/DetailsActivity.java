package com.example.itmba_transportcostcomparator;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DetailsActivity extends BaseActivity {

    private ListView listViewReports;
    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        listViewReports = findViewById(R.id.listViewReports);
        dbHelper = new DatabaseHelper(this);

        Cursor cursor = dbHelper.getAllReports();
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No saved reports yet", Toast.LENGTH_SHORT).show();
            return;
        }


        String[] from = {
                DatabaseHelper.COLUMN_MODE,
                DatabaseHelper.COLUMN_MONTHLY_COST,
                DatabaseHelper.COLUMN_DATE
        };
        int[] to = {R.id.tvMode, R.id.tvCost, R.id.tvDate};
        adapter = new SimpleCursorAdapter(this, R.layout.list_item_report, cursor, from, to, 0);
        listViewReports.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            Cursor c = adapter.getCursor();
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
    }
}
