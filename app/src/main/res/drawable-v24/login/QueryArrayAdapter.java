package com.example.xh.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */

public class QueryArrayAdapter extends ArrayAdapter<com.example.xh.login.Order> {
    private List<com.example.xh.login.Order> objects;
    private Context context;
    private int resource;

    public QueryArrayAdapter(Context context, int resource, List<com.example.xh.login.Order> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }


    public void setObjects(List<com.example.xh.login.Order> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objects.size();
    }

    @Override
    public com.example.xh.login.Order getItem(int position) {
        // TODO Auto-generated method stub
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        com.example.xh.login.OrderHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new com.example.xh.login.OrderHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(resource, null);
            viewHolder.setImageView((ImageView) convertView.findViewById(R.id.order_item_image));
            viewHolder.setTitle((TextView) convertView.findViewById(R.id.order_item_title));
            viewHolder.setDate((TextView) convertView.findViewById(R.id.order_item_date));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (com.example.xh.login.OrderHolder) convertView.getTag();
        }

        com.example.xh.login.Order order = objects.get(position);
        if (null != order) {
            viewHolder.getImageView().setBackgroundResource(order.getmImage());
            viewHolder.getTitle().setText(order.getmPlace());
            viewHolder.getDate().setText(order.getmDate());
        }

        return convertView;
    }

}
