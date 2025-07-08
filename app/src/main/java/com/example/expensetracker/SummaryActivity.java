package com.example.expensetracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shows a Pie Chart of total amount spent per category
 */
public class SummaryActivity extends AppCompatActivity {

    private PieChart pieChart;
    private ExpenseDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        pieChart = findViewById(R.id.pieChart);
        dbHelper = new ExpenseDBHelper(this);

        loadPieChart();
    }

    private void loadPieChart() {
        List<Expense> allExpenses = dbHelper.getAllExpenses();
        Map<String, Double> categoryTotals = new HashMap<>();

        // Group expenses by category
        for (Expense expense : allExpenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            if (categoryTotals.containsKey(category)) {
                categoryTotals.put(category, categoryTotals.get(category) + amount);
            } else {
                categoryTotals.put(category, amount);
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            entries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
        dataSet.setColors(new int[] {
                Color.rgb(255, 99, 132),
                Color.rgb(54, 162, 235),
                Color.rgb(255, 206, 86),
                Color.rgb(75, 192, 192),
                Color.rgb(153, 102, 255),
                Color.rgb(255, 159, 64)
        });

        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.WHITE);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(14f);
        pieChart.setCenterText("Expenses by Category");
        pieChart.setCenterTextSize(18f);

        Description desc = new Description();
        desc.setText("");
        pieChart.setDescription(desc);

        pieChart.animateY(1000);
        pieChart.invalidate(); // refresh chart
    }
}
