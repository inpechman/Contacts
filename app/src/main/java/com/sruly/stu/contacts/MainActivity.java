package com.sruly.stu.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sruly.stu.contacts.logic.Contact;
import com.sruly.stu.contacts.logic.DataBaseMgr;

public class MainActivity extends AppCompatActivity {
    Button newContact, showOld, showYoung, showAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genRandData();
        newContact = findViewById(R.id.new_contact_btn);
        showOld = findViewById(R.id.show_old_btn);
        showYoung = findViewById(R.id.show_young_btn);
        showAll = findViewById(R.id.show_all_btn);

        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewContact.class);
                startActivity(intent);
            }
        });

        showOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        showYoung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowContacts.class);
                intent.putExtra("type", 0);
                startActivity(intent);
            }
        });
    }
    public void genRandData(){
        for (int i = 0; i < 2000; i++) {
            Contact contact = new Contact("f" + i, "l" + i, "" + i, (int) (Math.random()*100 + 1918));
            DataBaseMgr.getInstance(getApplicationContext()).insertContact(contact);
        }
    }
}
