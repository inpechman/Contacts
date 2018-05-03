package com.sruly.stu.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sruly.stu.contacts.logic.Contact;

import java.util.LinkedList;

public class ShowContacts extends AppCompatActivity {
    TextView infoBar;
    Button goTo;
    EditText position;
    RecyclerView contactsRecyclerView;
    ContactsRecyclerViewAdapter adapter;
    int offset = 1;
    int rowsToLoad = 500;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        infoBar = findViewById(R.id.position_from_all);
        goTo = findViewById(R.id.go_to_pos_btn);
        position = findViewById(R.id.go_to_pos_num_field);
        position.clearFocus();
        infoBar.requestFocus();
        contactsRecyclerView = findViewById(R.id.contacts_recycler_view);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        Intent thisIntent = getIntent();
        int type = thisIntent.getIntExtra("type", 0);
        adapter = new ContactsRecyclerViewAdapter(getApplicationContext(), type, rowsToLoad);
        contactsRecyclerView.setAdapter(adapter);
        adapter.setOnFinishScrollListener(new OnFinishScrollListener() {
            @Override
            public void onFinishScroll(int type, LinkedList<Contact> contactArrayList) {
                offset += rowsToLoad;
                adapter.loadFromDB(offset, rowsToLoad);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dest = Integer.parseInt("0" + position.getText().toString());
                int destCailed = (int) (Math.ceil(dest / (double) rowsToLoad)) * rowsToLoad;
                offset += rowsToLoad;
                int destRowsToLoad = destCailed - offset + 1;
                adapter.loadFromDB(offset, destRowsToLoad);
                offset += destRowsToLoad - rowsToLoad;
                contactsRecyclerView.scrollToPosition(Math.min(dest, adapter.getItemCount() - 1));
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(position.getWindowToken(), 0);
            }
        });
    }
}
