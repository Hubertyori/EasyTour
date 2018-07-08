package studio.opencloud.easytour21.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.orders.Order;
import studio.opencloud.easytour21.orders.OrderHolder;
import studio.opencloud.easytour21.orders.SelfOrder;

/**
 * Created by Administrator on 2018/3/24.
 */

public class QueryArrayAdapter extends ArrayAdapter<SelfOrder> {
    private List<SelfOrder> objects;
    private Context context;
    private int resource;

    public QueryArrayAdapter(Context context, int resource, List<SelfOrder> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }


    public void setObjects(List<SelfOrder> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return objects.size();
    }

    @Override
    public SelfOrder getItem(int position) {
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
        OrderHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new OrderHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(resource, null);
            viewHolder.setImageView((ImageView) convertView.findViewById(R.id.order_item_image));
            viewHolder.setTitle((TextView) convertView.findViewById(R.id.order_item_title));
            viewHolder.setDate((TextView) convertView.findViewById(R.id.order_item_date));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderHolder) convertView.getTag();
        }

        SelfOrder order = objects.get(position);
        if (null != order) {
            viewHolder.getImageView().setBackgroundResource(order.getmImage());
            viewHolder.getTitle().setText(order.getmPlace());
            viewHolder.getDate().setText(order.getmDate());
        }

        return convertView;
    }

}
