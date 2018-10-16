package com.king.slidebar.presenter;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;

import com.king.slidebar.bean.Contact;
import com.king.slidebar.dao.ContactDao;
import com.king.slidebar.view.IContactView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/31
 */

public class ContactPresenter {

    private Context context;

    private IContactView iContactView;

    private AsyncQueryHandler aueryHandler;

    private Handler handler;

    private String key;

    public ContactPresenter(Context context, IContactView iContactView) {
        this.context = context;
        this.iContactView = iContactView;

    }


    public void loadListContact() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final List<Contact> list = ContactDao.getListContact(context);
//                Looper.prepare();
//                iContactView.getListContact(list);
//                Looper.loop();
//            }
//        });

        if (aueryHandler == null) {
            aueryHandler = new AsyncQueryHandler(context.getContentResolver()) {
                @Override
                public void startQuery(int token, Object cookie, Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
                    super.startQuery(token, cookie, uri, projection, selection, selectionArgs, orderBy);

                }

                @Override
                protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
                    super.onQueryComplete(token, cookie, cursor);

//                    List<Contact> list = testData();
                    List<Contact> list = ContactDao.getListContact(cursor);
                    iContactView.getListContact(list);
                }
            };
        }

        aueryHandler.startQuery(1
                , null
                , ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                , ContactDao.projection
                , "data1 is not null"
                , null
                , "sort_key COLLATE LOCALIZED ASC");


    }

    private List<Contact> testData() {
        List<Contact> list = new ArrayList<>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z", "#"};
        int len = letters.length;
        for (int i = 0; i < len; i++) {

            for (int j = 0; j < 4; j++) {
                Contact c = new Contact();
                c.setName(letters[i] + letters[j] + letters[i % len]);
                c.setNumber("151" + i + "888" + j + "" + j * i);
                list.add(c);
            }

        }

        return list;
    }

    public void showLetter(String letter) {
        this.key = letter;
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            iContactView.showLetter(key);
                            handler.removeMessages(2);
                            handler.sendEmptyMessageDelayed(2, 500);
                            break;
                        case 2:
                            iContactView.hideLetter();
                            break;
                    }
                }
            };
        }
        handler.sendEmptyMessage(1);

    }


}
