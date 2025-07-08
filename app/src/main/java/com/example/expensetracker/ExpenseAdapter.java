package com.example.expensetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textCategory, textAmount, textDate, textDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            textCategory = itemView.findViewById(R.id.textCategory);
            textAmount = itemView.findViewById(R.id.textAmount);
            textDate = itemView.findViewById(R.id.textDate);
            textDescription = itemView.findViewById(R.id.textDescription);
        }
    }

    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseAdapter.ViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.textCategory.setText("Category: " + expense.getCategory());
        holder.textAmount.setText("Amount: ₹" + expense.getAmount());
        holder.textDate.setText("Date: " + expense.getDate());
        holder.textDescription.setText("Description: " + expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }
}
