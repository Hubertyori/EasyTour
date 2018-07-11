package com.example.xh.login;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */

public class OrderAdapter_Idle extends ArrayAdapter<com.example.xh.login.Order> {
    private List<com.example.xh.login.Order> objects;
    private Context context;
    private int resource;

    public OrderAdapter_Idle(Context context, int resource, List<com.example.xh.login.Order> objects) {
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
        OrderHolder_Idle viewHolder = null;
        if (convertView == null) {
            viewHolder = new OrderHolder_Idle();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(resource, null);
            viewHolder.setM_ID((EditText) convertView.findViewById(R.id.et_order_ID));
            viewHolder.setM_name((EditText) convertView.findViewById(R.id.et_guest_name));
            viewHolder.setM_date((EditText) convertView.findViewById(R.id.et_start_time));
            viewHolder.setM_des((EditText) convertView.findViewById(R.id.et_destination));
            viewHolder.setM_people((EditText) convertView.findViewById(R.id.et_order_people));
            viewHolder.setM_remark((EditText) convertView.findViewById(R.id.et_remark));
            viewHolder.setM_tel((EditText) convertView.findViewById(R.id.et_guest_tel));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderHolder_Idle) convertView.getTag();
        }

        com.example.xh.login.Order order = objects.get(position);
        if (null != order) {
            viewHolder.getM_ID().setText(order.getmOrderID());
            viewHolder.getM_date().setText(order.getmDate());
            viewHolder.getM_des().setText(order.getmPlace());
            viewHolder.getM_people().setText(order.getNumOFPeople());
            viewHolder.getM_remark().setText(order.getM_Remark());
            viewHolder.getM_tel().setText(order.getM_tel());
            viewHolder.getM_name().setText(order.getmUsername());
        }

        return convertView;
    }

}