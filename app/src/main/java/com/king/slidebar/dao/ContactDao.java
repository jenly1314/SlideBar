package com.king.slidebar.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.king.slidebar.bean.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/31
 */

public class ContactDao {

    public static final Uri CONTACTS_URI = Uri.parse("content://com.android.contacts/data/phones");

    public static final String[] projection = {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.DATA1, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY};

    public static List<Contact> getListContact(Context context) {

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                , projection
                , "data1 is not null"
                , null
                , "sort_key COLLATE LOCALIZED ASC");

        return getListContact(cursor);

    }

    public static List<Contact> getListContact(Cursor cursor) {

        List<Contact> list = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Contact c = new Contact();
                c.setId(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)));
                c.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                c.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA1)));
                c.setSort(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY)));
                list.add(c);
            }

            cursor.close();
        }

        return list;
    }
}
