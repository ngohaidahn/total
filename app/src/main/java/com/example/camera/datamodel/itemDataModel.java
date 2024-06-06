package com.example.camera.datamodel;

public class itemDataModel {
    int ExpenseId;
    String ExpenseName;
    public itemDataModel(int expenseId, String expenseName) {
        ExpenseId = expenseId;
        ExpenseName = expenseName;
    }
    public int getExpenseId() {
        return ExpenseId;
    }
    public String getExpenseName() {
        return ExpenseName;
    }
}
