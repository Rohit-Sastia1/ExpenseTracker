package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Simple launch screen with navigation buttons.
 * Buttons assumed to exist in activity_main.xml:
 *  - buttonAddExpense     → AddExpenseActivity
 *  - buttonViewExpenses   → ViewExpensesActivity
 *  - (optional) buttonSummary → SummaryActivity  (charts – Part 3)
 */
public class MainActivity extends AppCompatActivity {

    private Button btnAddExpense;
    private Button btnViewExpenses;
    private Button btnSummary;      // comment out if you haven’t built SummaryActivity yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ───────────────────────
        //  FIND VIEWS
        // ───────────────────────
        btnAddExpense   = findViewById(R.id.buttonAddExpense);
        btnViewExpenses = findViewById(R.id.buttonViewExpenses);
        btnSummary      = findViewById(R.id.buttonSummary);   // safe even if absent; just don’t click

        // ───────────────────────
        //  NAVIGATION HANDLERS
        // ───────────────────────
        btnAddExpense = findViewById(R.id.buttonAddExpense);
        if (btnAddExpense != null) {
            btnAddExpense.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, AddExpenseActivity.class)));
        }

        btnViewExpenses = findViewById(R.id.buttonViewExpenses);
        if (btnViewExpenses != null) {
            btnViewExpenses.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, ViewExpensesActivity.class)));
        }

        btnSummary = findViewById(R.id.buttonSummary);
        if (btnSummary != null) {
            btnSummary.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, SummaryActivity.class)));
        }

        // You can comment this out until SummaryActivity is implemented
        if (btnSummary != null) {
            btnSummary.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, SummaryActivity.class)));
        }
    }
}
