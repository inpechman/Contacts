package com.sruly.stu.contacts;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.widget.TextView;

import com.sruly.stu.contacts.logic.Contact;
import com.sruly.stu.contacts.logic.DataBaseMgr;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowContacts extends AppCompatActivity {
    TextView infoBar;
    RecyclerView contactsRecyclerView;
    int offset = 1;
    int rowsToLoad = 50;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        infoBar = findViewById(R.id.position_from_all);
        contactsRecyclerView = findViewById(R.id.contacts_recycler_view);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        Intent thisIntent = getIntent();
        int type = thisIntent.getIntExtra("type", 0);
        final ContactsRecyclerViewAdapter adapter = new ContactsRecyclerViewAdapter(getApplicationContext(), type, rowsToLoad);
        contactsRecyclerView.setAdapter(adapter);
        adapter.setOnFinishScrollListener(new OnFinishScrollListener() {
            @Override
            public void onFinishScroll(int type, ArrayList<Contact> contactArrayList) {
                DataBaseMgr dbm = DataBaseMgr.getInstance(getApplicationContext());
                offset += rowsToLoad;
                switch (type){
                    case 1:
                        contactArrayList.addAll(dbm.getByDate(1958, true, offset, rowsToLoad));
                        break;
                    case 2:
                        contactArrayList.addAll(dbm.getByDate(1983, false, offset, rowsToLoad));
                        break;
                    default:
                        contactArrayList.addAll(dbm.getAll(offset, rowsToLoad));
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
