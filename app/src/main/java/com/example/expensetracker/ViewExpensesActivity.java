package com.example.expensetracker;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewExpensesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ExpenseDBHelper dbHelper;
    ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        recyclerView = findViewById(R.id.recyclerViewExpenses);
        dbHelper = new ExpenseDBHelper(this);

        List<Expense> expenseList = dbHelper.getAllExpenses();
        adapter = new ExpenseAdapter(expenseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
