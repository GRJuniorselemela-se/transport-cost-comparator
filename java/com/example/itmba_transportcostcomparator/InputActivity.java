package com.example.itmba_transportcostcomparator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InputActivity extends BaseActivity {

    private EditText etModeOfTransport;
    private EditText etDistancePerDay;
    private EditText etCostPerKm;
    private TextView tvTravelDays;
    private Spinner spinnerTransportType;
    private Button btnCalculateSave;
    private Button btnClear;
    private DatabaseHelper dbHelper;
    private String selectedTransportType;
    private Calendar selectedDate;
    private int travelDays = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etModeOfTransport = findViewById(R.id.etModeOfTransport);
        etDistancePerDay = findViewById(R.id.etDistancePerDay);
        etCostPerKm = findViewById(R.id.etCostPerKm);
        spinnerTransportType = findViewById(R.id.spinnerTransportType);
        tvTravelDays = findViewById(R.id.tvTravelDays);
        btnCalculateSave = findViewById(R.id.btnCalculateSave);
        btnClear = findViewById(R.id.btnClear);

        setupSpinner();

        dbHelper = new DatabaseHelper(this);
        selectedDate = Calendar.getInstance();

        tvTravelDays.setOnClickListener(v -> showDatePickerDialog());

        btnCalculateSave.setOnClickListener(v -> validateAndCalculate());
        btnClear.setOnClickListener(v -> clearFields());
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.transport_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransportType.setAdapter(adapter);

        spinnerTransportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTransportType = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTransportType = "Private Vehicle";
            }
        });
    }

    private void showDatePickerDialog() {
        int year = selectedDate.get(Calendar.YEAR);
        int month = selectedDate.get(Calendar.MONTH);
        int day = selectedDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog startDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {

            final Calendar startCal = Calendar.getInstance();
            startCal.set(year1, month1, dayOfMonth);

            DatePickerDialog endDialog = new DatePickerDialog(this, (view2, year2, month2, day2) -> {

                Calendar endCal = Calendar.getInstance();
                endCal.set(year2, month2, day2);

                long diff = endCal.getTimeInMillis() - startCal.getTimeInMillis();
                travelDays = (int) (diff / (24 * 60 * 60 * 1000)) + 1;
                if (travelDays <= 0) travelDays = 1;

                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.US);
                tvTravelDays.setText(sdf.format(startCal.getTime()) + " - " + sdf.format(endCal.getTime()));

            }, year1, month1, dayOfMonth);

            endDialog.setTitle("Select End Date");
            endDialog.getDatePicker().setCalendarViewShown(false);
            endDialog.getDatePicker().setSpinnersShown(true);
            endDialog.show();

        }, year, month, day);

        startDialog.setTitle("Select Start Date");
        startDialog.getDatePicker().setCalendarViewShown(false);
        startDialog.getDatePicker().setSpinnersShown(true);
        startDialog.show();
    }

    private void validateAndCalculate() {
        String mode = etModeOfTransport.getText().toString().trim();
        String distanceStr = etDistancePerDay.getText().toString().trim();
        String costStr = etCostPerKm.getText().toString().trim();

        if (mode.isEmpty()) {
            etModeOfTransport.setError("Mode of transport is required");
            etModeOfTransport.requestFocus();
            return;
        }

        if (distanceStr.isEmpty()) {
            etDistancePerDay.setError("Distance is required");
            etDistancePerDay.requestFocus();
            return;
        }

        if (costStr.isEmpty()) {
            etCostPerKm.setError("Cost per kilometre is required");
            etCostPerKm.requestFocus();
            return;
        }

        double distance;
        double cost;
        try {
            distance = Double.parseDouble(distanceStr);
            cost = Double.parseDouble(costStr);
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numeric values", Toast.LENGTH_LONG).show();
            return;
        }

        if (distance <= 0) {
            etDistancePerDay.setError("Distance must be greater than 0");
            etDistancePerDay.requestFocus();
            return;
        }

        if (cost <= 0) {
            etCostPerKm.setError("Cost must be greater than 0");
            etCostPerKm.requestFocus();
            return;
        }
        if (tvTravelDays.getText().toString().equals("Select date")) {
            Toast.makeText(this, "Please select a travel date", Toast.LENGTH_SHORT).show();
            return;
        }

        calculateAndSave(mode, distance, cost, travelDays);
    }

    private void calculateAndSave(String mode, double distance, double cost, int days) {
        TransportCalculator calculator = new TransportCalculator();
        TransportCalculator.CalculationResult result = calculator.calculateAll(distance, cost, days);

        Recommendation recommendation = new Recommendation();
        Recommendation.RecommendationResult recResult = recommendation.getCategoryAndRecommendation(result.monthlyCost);

        long id = dbHelper.insertReport(mode, selectedTransportType, result.dailyCost, result.monthlyCost, result.monthlyDistance, recResult.category, recResult.recommendation);
        if (id == -1) {
            Toast.makeText(this, "Failed to save report", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Report saved successfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(InputActivity.this, ResultsActivity.class);
        intent.putExtra("REPORT_ID", id);
        startActivity(intent);
    }

    private void clearFields() {
        etModeOfTransport.setText("");
        etDistancePerDay.setText("");
        etCostPerKm.setText("");
        spinnerTransportType.setSelection(0);
        tvTravelDays.setText("Select date");
        travelDays = 1;
        etModeOfTransport.requestFocus();
    }
}