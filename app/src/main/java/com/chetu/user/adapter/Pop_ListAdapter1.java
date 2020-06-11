package com.chetu.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetu.user.R;
import com.chetu.user.model.GouXuanModel;

import java.util.List;

/**
 * Created by zyz on 2016/7/6.
 * Email：1125213018@qq.com
 * description：pop adapter
 */
public class Pop_ListAdapter1 extends BaseAdapter {
    private Context context;
    private List<GouXuanModel> list;
    private int selectIndex = 0;

    public Pop_ListAdapter1(Context context, List<GouXuanModel> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelectItem(int selectItem) {
        this.selectIndex = selectItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pop_list1, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(list.get(position).getTitle());
        if (list.get(position).isIsgouxuan()) {
            holder.textView1.setSelected(true);
            holder.textView1.setPressed(true);
            holder.textView1.setTextColor(context.getResources().getColor(R.color.blue));
            holder.imageView.setImageResource(R.mipmap.ic_yixuan_juxing);
//            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.pop_bg6));
        } else {
            holder.textView1.setSelected(false);
            holder.textView1.setPressed(false);
            holder.textView1.setTextColor(context.getResources().getColor(R.color.black));
            holder.imageView.setImageResource(R.mipmap.ic_weixuan_juxing);
//            holder.textView1.setBackgroundColor(context.getResources().getColor(R.color.pop_bg5));
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView textView1;
        ImageView imageView;
    }
}
