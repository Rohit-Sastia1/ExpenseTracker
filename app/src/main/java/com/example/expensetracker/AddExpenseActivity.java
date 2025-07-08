package com.example.expensetracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    EditText amountInput, categoryInput, dateInput, descriptionInput;
    Button saveBtn;
    ExpenseDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        amountInput = findViewById(R.id.editTextAmount);
        categoryInput = findViewById(R.id.editTextCategory);
        dateInput = findViewById(R.id.editTextDate);
        descriptionInput = findViewById(R.id.editTextDescription);
        saveBtn = findViewById(R.id.buttonSave);

        dbHelper = new ExpenseDBHelper(this);

        saveBtn.setOnClickListener(v -> {
            String amountStr = amountInput.getText().toString();
            String category = categoryInput.getText().toString();
            String date = dateInput.getText().toString();
            String description = descriptionInput.getText().toString();

            if (amountStr.isEmpty() || category.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);

            Expense expense = new Expense(category, amount, date, description);
            dbHelper.insertExpense(expense);
            Toast.makeText(this, "Expense Saved!", Toast.LENGTH_SHORT).show();

            amountInput.setText("");
            categoryInput.setText("");
            dateInput.setText("");
            descriptionInput.setText("");
        });
    }
}
