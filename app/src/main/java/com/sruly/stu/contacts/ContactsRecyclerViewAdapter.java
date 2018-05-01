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
    private int rowsToLoad;
    Context context;
    int type;
    ArrayList<Contact> contactArrayList;
    DataBaseMgr dbm;
    TextView infoBar;
    OnFinishScrollListener listener;

    public ContactsRecyclerViewAdapter(Context context, final int type, int rowsToLoad) {
        this.context = context;
        this.type = type;
        dbm = DataBaseMgr.getInstance(context);
        this.rowsToLoad = rowsToLoad;

        switch (type){
            case 1:
                contactArrayList = dbm.getByDate(1958, true, 1, rowsToLoad);
                break;
            case 2:
                contactArrayList = dbm.getByDate(1983, false, 1, rowsToLoad);
                break;
            default:
                contactArrayList = dbm.getAll(1, rowsToLoad);
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
        if (position == getItemCount() - 5 && listener != null) {
            listener.onFinishScroll(type, contactArrayList);
        }
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    public void setOnFinishScrollListener(OnFinishScrollListener onFinishScrollListener) {
        this.listener = onFinishScrollListener;
    }
}
