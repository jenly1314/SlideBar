package com.king.slidebar.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.king.slidebar.R;
import com.king.slidebar.bean.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/31
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;

    private List<Contact> listData;

    private Map<String,Integer> mapLetter;

    public ContactAdapter(Context context, List<Contact> listData){
        this.context = context;
        this.listData = listData;
        updateLetters(listData);
    }

    @Override
    public int getCount() {
        return listData!=null ? listData.size() : 0;
    }

    @Override
    public Contact getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null){
            convertView = inflater(R.layout.list_contact_item);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(listData.get(position));

        return convertView;
    }

    public View inflater(@LayoutRes int id){
        return LayoutInflater.from(context).inflate(id,null);
    }

    public List<Contact> getListData() {
        return listData;
    }

    public void setListData(List<Contact> listData) {
        this.listData = listData;

    }

    @Override
    public void notifyDataSetChanged() {
        updateLetters(listData);
        super.notifyDataSetChanged();
    }

    public void updateLetters(List<Contact> list){
        mapLetter = new HashMap<>();
        if(list!=null){
            for (int i = list.size()-1; i >=0; i--) {
                Contact c = list.get(i);
                mapLetter.put(c.getKey(),i);
            }
        }
    }

    public int getPositionByLetter(String latter){
        if(mapLetter.containsKey(latter))
            return mapLetter.get(latter);
        return -1;
    }

    public static class ViewHolder {

        private View itemView;
        TextView tvName;
        TextView tvNumber;

        public ViewHolder(View itemView){
            this.itemView = itemView;
            tvName = findView(R.id.tvName);
            tvNumber = findView(R.id.tvNumber);
        }

        public void setData(Contact contact){
            tvName.setText(contact.getName());
            tvNumber.setText(contact.getNumber());
        }

        private <T extends View> T findView(@IdRes int id){
            return (T)itemView.findViewById(id);
        }
    }
}
