package com.habibfr.gojek_clone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    Context context;
    List<Menu> menuList;

    public MenuAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_menu, viewGroup, false);

        ImageView iconMenu = view.findViewById(R.id.iconMenu);
        TextView txtMenu = view.findViewById(R.id.txtMenu);

        Menu currentMenu = (Menu) getItem(i);
        iconMenu.setImageResource(currentMenu.getImgSrc());
        txtMenu.setText(currentMenu.getName());

        return view;
    }
}
