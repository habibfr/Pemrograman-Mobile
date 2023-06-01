package com.habibfr.gojek_clone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentBeranda extends Fragment {

    GridView gvMenu;
    RecyclerView rvImageCard;
    MenuAdapter menuAdapter;
    CardImageAdapter cardImageAdapter;
    List<Menu> listMenu = new ArrayList<>();
    ArrayList<CardImage> cardImageList = new ArrayList<>();

    public FragmentBeranda() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);


        gvMenu = view.findViewById(R.id.gvMenu);
        rvImageCard = view.findViewById(R.id.rvImageCard);

        if (listMenu.isEmpty()) {
            addMenu();
        }
        menuAdapter = new MenuAdapter(view.getContext(), listMenu);
        gvMenu.setAdapter(menuAdapter);

        if (cardImageList.isEmpty()) {
            addImageCard();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rvImageCard.setLayoutManager(linearLayoutManager);

        cardImageAdapter = new CardImageAdapter(view.getContext(), cardImageList);
        rvImageCard.setAdapter(cardImageAdapter);


        gvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    Intent intent = new Intent(getContext(), GoFoodActivity.class);
                    startActivity(intent);
//                    Toast.makeText(adapterView.getContext(), "t " + i, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void addMenu() {
        listMenu.add(new Menu("GoRide", R.drawable.menu8));
        listMenu.add(new Menu("GoCar", R.drawable.menu1));
        listMenu.add(new Menu("GoFood", R.drawable.menu7));
        listMenu.add(new Menu("GoSend", R.drawable.menu6));
        listMenu.add(new Menu("GoMart", R.drawable.menu5));
        listMenu.add(new Menu("GoTagihan", R.drawable.menu4));
        listMenu.add(new Menu("GoCluB", R.drawable.menu3));
        listMenu.add(new Menu("Lainnya", R.drawable.menu2));
    }

    public void addImageCard() {
        cardImageList.add(new CardImage(R.drawable.banner2, "Makin Stres Bro", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."));
        cardImageList.add(new CardImage(R.drawable.banner2, "Wow, I'm So Tired", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."));
    }
}