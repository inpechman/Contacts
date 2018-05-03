package com.sruly.stu.contacts;

import com.sruly.stu.contacts.logic.Contact;

import java.util.LinkedList;

/**
 * Created by stu on 5/1/2018.
 *
 */

public interface OnFinishScrollListener {
    public void onFinishScroll(int type, LinkedList<Contact> contactArrayList);
}
