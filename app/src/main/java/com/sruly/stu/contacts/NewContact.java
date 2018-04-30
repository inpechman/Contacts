package com.sruly.stu.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sruly.stu.contacts.logic.Contact;
import com.sruly.stu.contacts.logic.DataBaseMgr;

public class NewContact extends AppCompatActivity {
    EditText firstName, lastName, id, birthYear;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        firstName = findViewById(R.id.enter_first_name_field);
        lastName = findViewById(R.id.enter_last_name_field);
        id = findViewById(R.id.enter_id_field);
        birthYear = findViewById(R.id.enter_birth_year_field);
        saveBtn = findViewById(R.id.save_new_contact_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseMgr dbm = DataBaseMgr.getInstance(getApplicationContext());
                Contact contact = new Contact(firstName.getText().toString(),
                        lastName.getText().toString(),
                        id.getText().toString(),
                        Integer.parseInt("0" + birthYear.getText().toString()));
                dbm.insertContact(contact);
                finish();
            }
        });
    }
}
