package com.habibfr.gojek_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class GoFoodActivity extends AppCompatActivity {
    ArrayList<Filter> filters = new ArrayList<>();
    ArrayList<Resto> restos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_food);


        ImageButton ibtnBack = findViewById(R.id.ibtnBack);
        RecyclerView rvFilter = findViewById(R.id.rvFilter);
        RecyclerView rvResto = findViewById(R.id.rvResto);

        if (filters.isEmpty()) {
            addFilter();
        }

        FilterAdapter filterAdapter = new FilterAdapter(this, filters);
        LinearLayoutManager linearLayoutFilter = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rvFilter.setLayoutManager(linearLayoutFilter);
        rvFilter.setAdapter(filterAdapter);

        if (restos.isEmpty()) {
            addResto();
        }

        RestoAdapter restoAdapter = new RestoAdapter(this, restos);
        LinearLayoutManager linearLayoutResto = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        rvResto.setLayoutManager(linearLayoutResto);
        rvResto.setAdapter(restoAdapter);

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void addResto() {
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.7"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.6"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.2"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.9"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.4"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.3"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.5"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.1"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.6"));
        restos.add(new Resto(R.drawable.resto, "Mie Gacoan, Surabaya Merr", "Noodle, Fast Food", "4.8"));
    }

    public void addFilter() {
        filters.add(new Filter(R.drawable.baseline_filter_list_24));
        filters.add(new Filter("Near Me"));
        filters.add(new Filter(R.drawable.baseline_keyboard_arrow_down_24, "Price"));
        filters.add(new Filter("Dish Promo"));
        filters.add(new Filter(R.drawable.baseline_keyboard_arrow_down_24, "Cuisines"));
        filters.add(new Filter("Open Now"));
        filters.add(new Filter("Pickup"));
    }
}