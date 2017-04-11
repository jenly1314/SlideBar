package com.king.slidebar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.king.slidebar.adapter.ContactAdapter;
import com.king.slidebar.bean.Contact;
import com.king.slidebar.presenter.ContactPresenter;
import com.king.slidebar.util.PinyinUtil;
import com.king.slidebar.view.IContactView;
import com.king.view.slidebar.SlideBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IContactView{

    private SlideBar slideBar;

    private TextView tvLetter;

    private ListView listView;

    private ContactAdapter contactAdapter;

    private ContactPresenter presenter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        context = this;
        slideBar = (SlideBar) findViewById(R.id.slideBar);
        tvLetter = (TextView) findViewById(R.id.tvLetter);
        listView = (ListView) findViewById(R.id.listView);
        contactAdapter = new ContactAdapter(context,null);
        listView.setAdapter(contactAdapter);

        presenter = new ContactPresenter(context,this);
        presenter.loadListContact();

        slideBar.setOnTouchLetterChangeListenner(new SlideBar.OnTouchLetterChangeListenner() {
            @Override
            public void onTouchLetterChange(boolean isTouch, String letter) {
                int pos = contactAdapter.getPositionByLetter(letter);
                listView.setSelection(pos);
                presenter.showLetter(letter);
                Log.d("Jenly|onTouchLetterChange",String.format("%d|%s",pos,letter));

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String letter = contactAdapter.getItem(position).getKey();
                String pinyin = PinyinUtil.chineneToSpell(contactAdapter.getItem(position).getName());
                Log.d("Jenly|onItemClick",String.format("%d|%s",position,letter));
            }
        });
    }

    @Override
    public void getListContact(List<Contact> list) {
        contactAdapter.setListData(list);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLetter(String letter) {
        tvLetter.setText(letter);
        if(tvLetter.getVisibility()!=View.VISIBLE){
            tvLetter.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hideLetter() {
        tvLetter.setVisibility(View.GONE);
        tvLetter.setText("");
    }
}
