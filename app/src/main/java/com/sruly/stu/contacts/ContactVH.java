package com.sruly.stu.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sruly.stu.contacts.logic.Contact;

/**
 * Created by stu on 4/30/2018.
 *
 */

class ContactVH extends RecyclerView.ViewHolder {
    TextView firstName, lastName, id, age;

    public ContactVH(View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.row_first_name);
        lastName = itemView.findViewById(R.id.row_last_name);
        id = itemView.findViewById(R.id.row_id);
        age = itemView.findViewById(R.id.row_age);
    }

    public void update(Contact contact){
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        id.setText(contact.getId());
        age.setText("" + contact.getAge(2018));
    }
}
