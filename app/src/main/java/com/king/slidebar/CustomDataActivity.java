package com.king.slidebar;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.king.slidebar.bean.CustomBean;
import com.king.slidebar.databinding.ActivityCustomDataBinding;
import com.king.view.slidebar.IndexHelper;
import com.king.view.slidebar.PinyinUtil;
import com.king.view.slidebar.SlideBar;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class CustomDataActivity extends AppCompatActivity {
    private SlideBar mSlideBar;
    private RecyclerView mRecyclerView;
    private IndexHelper mIndexHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_data);

        ActivityCustomDataBinding binding = ((ActivityCustomDataBinding) dataBinding);

        mRecyclerView = binding.recyclerView;
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mBaseQuickAdapter);
        mSlideBar = binding.slideBar;
        mSlideBar.setOnTouchLetterChangeListener(new SlideBar.OnTouchLetterChangeListener() {
            @Override
            public void onTouchLetterChange(boolean isTouch, String letter) {
                int position = mIndexHelper.getPositionByLetter(letter);
                RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    ((LinearLayoutManager) manager).scrollToPositionWithOffset(position, 0);
                }
            }
        });

        Type type = new TypeToken<List<CustomBean>>() {
        }.getType();
        List<CustomBean> list = new Gson().fromJson(new InputStreamReader(getResources().openRawResource(R.raw.community)), type);

        mIndexHelper = new IndexHelper();

        mIndexHelper.refreshMap(list, new Comparator<CustomBean>() {
            @Override
            public int compare(CustomBean o1, CustomBean o2) {
                String o1Name = o1.getName();
                String o2Name = o2.getName();

                String o1PinYinName = PinyinUtil.chineneToSpell(o1Name);
                String o2PinYinName = PinyinUtil.chineneToSpell(o2Name);

                return o1PinYinName.compareTo(o2PinYinName);
            }
        });
        mBaseQuickAdapter.replaceData(list);
    }

    private BaseQuickAdapter<CustomBean, BaseViewHolder> mBaseQuickAdapter = new BaseQuickAdapter<CustomBean, BaseViewHolder>(android.R.layout.simple_list_item_2) {
        @Override
        protected void convert(BaseViewHolder helper, CustomBean item) {
            helper.setText(android.R.id.text1, item.getName());
            helper.setText(android.R.id.text2, item.getPhone());
        }
    };


}
