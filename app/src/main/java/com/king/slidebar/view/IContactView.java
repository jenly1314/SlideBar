package com.king.slidebar.view;

import com.king.slidebar.bean.Contact;

import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/31
 */

public interface IContactView {

    void getListContact(List<Contact> list);

    void showLetter(String letter);

    void hideLetter();
}
