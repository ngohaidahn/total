package com.example.camera.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camera.R;
import com.example.camera.datamodel.itemDataModel;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    View rootView;

    Context context;

    private ArrayList<itemDataModel> dataSet;

    public Adapter(ArrayList<itemDataModel> dataSet) {
        this.dataSet = dataSet;
    }

    // Usered Entered Expense amt will be stored in ExpAmtArray
    ArrayList<String> ExpAmtArray = new ArrayList<String>();
    ArrayList<String> ExpNameArray = new ArrayList<String>();

    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    TextView textviewTotalExpense;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prod_list, parent, false);
        context = parent.getContext();
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        textviewTotalExpense = (TextView) rootView.findViewById(R.id.totalExpense);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView expensesName = holder.expensesName;
        EditText expHeld = holder.expHeld;
        Button imageButton = holder.imageButton;
        int id = dataSet.get(position).getExpenseId();
        expensesName.setText(dataSet.get(position).getExpenseName());
        expHeld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                ExpenseFinalTotal = 0;
                if (isOnTextChanged) {
                    isOnTextChanged = false;
                    try {
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i <= id; i++) {
//                            int inpo = position;
                            if (i != id) {
                                ExpNameArray.add("0");
                                ExpAmtArray.add("0");
                            } else {
                                ExpNameArray.add("0");
                                ExpAmtArray.add("0");
                                ExpNameArray.set(id, dataSet.get(id).getExpenseName());

                                ExpAmtArray.set(id, s.toString());
                            }
                        }
                        for (int i = 0; i <= ExpAmtArray.size() - 1; i++) {
                            int tempTotal = Integer.parseInt(ExpAmtArray.get(i));
                            ExpenseFinalTotal = (ExpenseFinalTotal + tempTotal) ;
                        }
                        textviewTotalExpense.setText("Total Expenses: " + String.valueOf(ExpenseFinalTotal));
                    } catch (NumberFormatException e) {
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i <= id; i++) {
                            Log.d("TimesRemoved", " : " + i);
//                            int newposition = position;
                            if (i == id) {
                                ExpAmtArray.set(id, "0");
                                ExpNameArray.set(id, "0");
                            }
                        }
                        for (int i = 0; i <= ExpAmtArray.size() - 1; i++) {

                            int tempTotal = Integer.parseInt(ExpAmtArray.get(i));
                            ExpenseFinalTotal = (ExpenseFinalTotal + tempTotal) ;

                        }
                        textviewTotalExpense.setText("Total Expenses: ₹ " + String.valueOf(ExpenseFinalTotal));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expensesName;
        EditText expHeld;
        Button imageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expensesName = (TextView) itemView.findViewById(R.id.textViewExpName);
            expHeld = (EditText) itemView.findViewById(R.id.ExpHeld);
            imageButton = (Button) itemView.findViewById(R.id.ExpBimageSelect);

        }
    }
}
