package com.sruly.stu.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sruly.stu.contacts.logic.Contact;
import com.sruly.stu.contacts.logic.DataBaseMgr;

import java.util.LinkedList;

/**
 * Created by stu on 4/30/2018.
 */

class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactVH> {
    private int rowsToLoad;
    Context context;
    int type;
    LinkedList<Contact> contactArrayList = new LinkedList<>();
    DataBaseMgr dbm;
    TextView infoBarAll;
    EditText infoBarPos;
    OnFinishScrollListener listener;

    public ContactsRecyclerViewAdapter(Context context, final int type, int rowsToLoad) {
        this.context = context;
        this.type = type;
        dbm = DataBaseMgr.getInstance(context);
        this.rowsToLoad = rowsToLoad;
        loadFromDB(1, rowsToLoad);
    }

    public int loadFromDB(int offset, int rowsToLoad) {
        switch (type) {
            case 1:
                contactArrayList.addAll(dbm.getByDate(1958, true, offset, rowsToLoad));
                break;
            case 2:
                contactArrayList.addAll(dbm.getByDate(1983, false, offset, rowsToLoad));
                break;
            default:
                contactArrayList.addAll(dbm.getAll(offset, rowsToLoad));
        }
        return rowsToLoad;
    }


    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        infoBarAll = ((View) parent.getParent()).findViewById(R.id.position_from_all);
        infoBarPos = ((View) parent.getParent()).findViewById(R.id.go_to_pos_num_field);
        return new ContactVH(View.inflate(context, R.layout.show_contacts_row, null));
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        holder.update(contactArrayList.get(position));
        infoBarPos.setText("" + position);
        infoBarAll.setText(" / " + getItemCount());
        if (position == getItemCount() - 20 && listener != null) {
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
