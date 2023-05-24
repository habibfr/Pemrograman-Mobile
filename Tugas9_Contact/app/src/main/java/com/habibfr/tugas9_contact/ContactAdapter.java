package com.habibfr.tugas9_contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class ContactAdapter extends BaseAdapter {

    Context context;
    List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contactList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        Contact currentContact = (Contact) getItem(i);

        TextView txtNameContact = view.findViewById(R.id.txtNameContact);
        TextView txtNoHpContact = view.findViewById(R.id.txtNoHpContact);

        txtNameContact.setText(currentContact.getName());
        txtNoHpContact.setText(currentContact.getNoHp());
        return view;
    }
}
