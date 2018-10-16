package com.king.slidebar;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.king.slidebar.bean.Contact;
import com.king.slidebar.databinding.ActivityCommunityBinding;
import com.king.slidebar.presenter.ContactPresenter;
import com.king.slidebar.view.IContactView;
import com.king.view.slidebar.IndexHelper;
import com.king.view.slidebar.SlideBar;

import java.util.List;

public class CommunityActivity extends AppCompatActivity {
    private static final String TAG = CommunityActivity.class.getSimpleName();
    private SlideBar mSlideBar;
    private RecyclerView mRecyclerView;
    private ContactPresenter mPresenter;
    private IndexHelper mIndexHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_community);

        ActivityCommunityBinding binding = ((ActivityCommunityBinding) dataBinding);
        mRecyclerView = binding.recyclerView;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mSlideBar = binding.slideBar;

        mRecyclerView.setAdapter(mBaseQuickAdapter);

        mSlideBar.setOnTouchLetterChangeListener(new SlideBar.OnTouchLetterChangeListener() {
            @Override
            public void onTouchLetterChange(boolean isTouch, String letter) {
                int position = mIndexHelper.getPositionByLetter(letter);
                Log.e(TAG, "onTouchLetterChange: " + position + " " + letter);
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                if (layoutManager != null) {
                    layoutManager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        mIndexHelper = new IndexHelper();
        mPresenter = new ContactPresenter(this, new IContactView() {
            @Override
            public void getListContact(List<Contact> list) {
                mIndexHelper.refreshMap(list, null);
                mBaseQuickAdapter.replaceData(list);
            }

            @Override
            public void showLetter(String letter) {

            }

            @Override
            public void hideLetter() {

            }
        });
        mPresenter.loadListContact();
    }

    private BaseQuickAdapter<Contact, BaseViewHolder> mBaseQuickAdapter = new BaseQuickAdapter<Contact, BaseViewHolder>(android.R.layout.simple_list_item_2) {
        @Override
        protected void convert(BaseViewHolder helper, Contact item) {
            helper.setText(android.R.id.text1, item.getName());
            helper.setText(android.R.id.text2, item.getNumber());
        }
    };
}
