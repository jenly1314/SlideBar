package com.king.slidebar.bean;

import com.king.view.slidebar.IndexHelper;
import com.king.view.slidebar.PinyinUtil;

public class CustomBean implements IndexHelper.IIndexKey {
    private String communityName;
    private String communityContactPhone;

    public CustomBean(String name, String phone) {
        this.communityName = name;
        this.communityContactPhone = phone;
    }

    public String getName() {
        return communityName;
    }

    public void setName(String name) {
        this.communityName = name;
    }

    public String getPhone() {
        return communityContactPhone;
    }

    public void setPhone(String phone) {
        this.communityContactPhone = phone;
    }

    @Override
    public String getIndexKey() {
        return PinyinUtil.formatAlpha(communityName);
    }
}
