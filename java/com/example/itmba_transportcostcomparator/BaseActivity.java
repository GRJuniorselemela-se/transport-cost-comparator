package com.example.itmba_transportcostcomparator;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            if (this instanceof MainActivity) {
                return true;
            }
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        else if (id == R.id.action_details) {
            if (this instanceof DetailsActivity) return true;
            startActivity(new Intent(this, DetailsActivity.class));
            return true;
        }
        else if (id == R.id.action_reports) {
            if (this instanceof ReportsActivity) return true;
            startActivity(new Intent(this, ReportsActivity.class));
            return true;
        }
        else if (id == R.id.action_help) {
            if (this instanceof HelpActivity) return true;
            startActivity(new Intent(this, HelpActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
