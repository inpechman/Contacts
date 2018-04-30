package com.sruly.stu.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sruly.stu.contacts.logic.Contact;
import com.sruly.stu.contacts.logic.DataBaseMgr;

import java.util.ArrayList;

/**
 * Created by stu on 4/30/2018.
 *
 */

class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactVH> {
    Context context;
    int type;
    ArrayList<Contact> contactArrayList;
    DataBaseMgr dbm;
    TextView infoBar;

    public ContactsRecyclerViewAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
        dbm = DataBaseMgr.getInstance(context);

        switch (type){
            case 1:
                break;
            case 2:
                break;
            default:
                contactArrayList = dbm.getAll();
        }
    }


    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        infoBar = ((View) parent.getParent()).findViewById(R.id.position_from_all);
        return new ContactVH(View.inflate(context, R.layout.show_contacts_row, null));
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        holder.update(contactArrayList.get(position));
        infoBar.setText(position + " / " + getItemCount());
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }
}
