package com.habibfr.gojek_clone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class FragmentBeranda extends Fragment {

    GridView gvMenu;
    MenuAdapter menuAdapter;
    List<Menu> listMenu = new ArrayList<>();

    public FragmentBeranda() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        gvMenu = view.findViewById(R.id.gvMenu);
        if (listMenu.isEmpty()) {
            addMenu();
        }
        menuAdapter = new MenuAdapter(view.getContext(), listMenu);
        gvMenu.setAdapter(menuAdapter);

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
}