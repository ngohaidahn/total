package com.example.camera;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camera.adapter.Adapter;
import com.example.camera.datamodel.itemDataModel;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<itemDataModel> data;
    itemDataModel itemDatamodel;
    // list of Expensesname
    String[] Expensesname = {"Rent","Coffee","Lunch"};
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_expenses);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for(int i =0;i<Expensesname.length;i++){
            itemDatamodel = new itemDataModel(i, Expensesname[i]
            );
            data.add(itemDatamodel);
        }
        // call Adapter class by passing ArrayList data
        adapter = new Adapter(data);
        // set adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}