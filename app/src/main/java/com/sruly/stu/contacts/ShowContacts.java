package com.sruly.stu.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class ShowContacts extends AppCompatActivity {
    TextView infoBar;
    RecyclerView contactsRecyclerView;
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
        contactsRecyclerView.setAdapter(new ContactsRecyclerViewAdapter(getApplicationContext(), type));
    }
}
