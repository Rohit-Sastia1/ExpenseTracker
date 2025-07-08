package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all SQLite CRUD operations for the Expense Tracker.
 */
public class ExpenseDBHelper extends SQLiteOpenHelper {

    // ──────────────────────────────────────────────────────────
    //  DATABASE & TABLE DETAILS
    // ──────────────────────────────────────────────────────────
    private static final String DATABASE_NAME = "ExpenseTracker.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME      = "expenses";
    public static final String COL_ID          = "id";
    public static final String COL_CATEGORY    = "category";
    public static final String COL_AMOUNT      = "amount";
    public static final String COL_DATE        = "date";
    public static final String COL_DESCRIPTION = "description";

    // ──────────────────────────────────────────────────────────
    //  CONSTRUCTOR
    // ──────────────────────────────────────────────────────────
    public ExpenseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ──────────────────────────────────────────────────────────
    //  LIFECYCLE CALLBACKS
    // ──────────────────────────────────────────────────────────
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COL_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_CATEGORY    + " TEXT NOT NULL, " +
                        COL_AMOUNT      + " REAL NOT NULL, " +
                        COL_DATE        + " TEXT NOT NULL, " +   // ISO‑8601 (yyyy‑MM‑dd) is easiest
                        COL_DESCRIPTION + " TEXT" +
                        ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now simply drop & recreate; add ALTER TABLEs here when schema evolves
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ──────────────────────────────────────────────────────────
    //  INSERT
    // ──────────────────────────────────────────────────────────
    public long insertExpense(Expense expense) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY,    expense.getCategory());
        values.put(COL_AMOUNT,      expense.getAmount());
        values.put(COL_DATE,        expense.getDate());
        values.put(COL_DESCRIPTION, expense.getDescription());

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;   // returns row‑ID (or −1 if failed)
    }

    // ──────────────────────────────────────────────────────────
    //  QUERY ALL
    // ──────────────────────────────────────────────────────────
    public List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor c = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_DATE + " DESC", null)) {

            if (c.moveToFirst()) {
                do {
                    int    id          = c.getInt   (c.getColumnIndexOrThrow(COL_ID));
                    String category    = c.getString(c.getColumnIndexOrThrow(COL_CATEGORY));
                    double amount      = c.getDouble(c.getColumnIndexOrThrow(COL_AMOUNT));
                    String date        = c.getString(c.getColumnIndexOrThrow(COL_DATE));
                    String description = c.getString(c.getColumnIndexOrThrow(COL_DESCRIPTION));

                    list.add(new Expense(id, category, amount, date, description));
                } while (c.moveToNext());
            }
        }
        db.close();
        return list;
    }

    // ──────────────────────────────────────────────────────────
    //  OPTIONAL HELPERS (future use)
    // ──────────────────────────────────────────────────────────
    public int deleteExpense(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public double getTotalAmount() {
        double total = 0;
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery(
                "SELECT SUM(" + COL_AMOUNT + ") FROM " + TABLE_NAME, null)) {
            if (c.moveToFirst()) total = c.getDouble(0);
        }
        db.close();
        return total;
    }

    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor c = db.query(
                TABLE_NAME,
                null,
                COL_CATEGORY + "=?",
                new String[]{category},
                null, null,
                COL_DATE + " DESC")) {

            if (c.moveToFirst()) {
                do {
                    list.add(new Expense(
                            c.getInt   (c.getColumnIndexOrThrow(COL_ID)),
                            c.getString(c.getColumnIndexOrThrow(COL_CATEGORY)),
                            c.getDouble(c.getColumnIndexOrThrow(COL_AMOUNT)),
                            c.getString(c.getColumnIndexOrThrow(COL_DATE)),
                            c.getString(c.getColumnIndexOrThrow(COL_DESCRIPTION))
                    ));
                } while (c.moveToNext());
            }
        }
        db.close();
        return list;
    }
}
