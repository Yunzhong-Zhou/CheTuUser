package com.chetu.user.adapter;

import android.content.Context;
import android.view.View;

import com.chetu.user.R;
import com.chetu.user.model.NotebookModel;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;

import java.util.List;

/**
 * 这是普通的分组Adapter 每一个组都有头部、尾部和子项。
 */
public class NoteBookAdapter extends GroupedRecyclerViewAdapter {

    protected List<NotebookModel.ListBeanX> mGroups;

    public NoteBookAdapter(Context context, List<NotebookModel.ListBeanX> groups) {
        super(context);
        mGroups = groups;
    }

    //返回组的数量
    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    //返回当前组的子项数量
    @Override
    public int getChildrenCount(int groupPosition) {
//        ArrayList<ChildEntity> children = mGroups.get(groupPosition).getChildren();
        List<NotebookModel.ListBeanX.ListBean> children = mGroups.get(groupPosition).getList();
        return children == null ? 0 : children.size();
    }

    public void clear() {
        mGroups.clear();
        notifyDataChanged();
    }

    public void setGroups(List<NotebookModel.ListBeanX> groups) {
        mGroups = groups;
        notifyDataChanged();
    }

    //当前组是否有头部
    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    //当前组是否有尾部
    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    //返回头部的布局id。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.item_notebook_head;
    }

    //返回尾部的布局id。(如果hasFooter返回false，这个方法不会执行)
    @Override
    public int getFooterLayout(int viewType) {
//        return R.layout.adapter_footer;
        return 0;
    }

    //返回子项的布局id。
    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_notebook;
    }

    //绑定头部布局数据。(如果hasHeader返回false，这个方法不会执行)
    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        NotebookModel.ListBeanX entity = mGroups.get(groupPosition);
        holder.setText(R.id.tv_year, entity.getYear()+"年");
    }

    //绑定尾部布局数据。(如果hasFooter返回false，这个方法不会执行)
    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
//        NotebookModel.ListBeanX entity = mGroups.get(groupPosition);
//        holder.setText(R.id.tv_footer, entity.getFooter());
    }

    //绑定子项布局数据。
    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        NotebookModel.ListBeanX.ListBean entity = mGroups.get(groupPosition).getList().get(childPosition);

        //拆分日期  年     月日    \n    时分秒
        String[] strArr1 = entity.getCreateDate().split(" ");//拆分空格把日期和时间分开
        String[] strArr2 = strArr1[0].split("-");//拆分日期 得到年月日
//        String year = strArr2[0];//年
        String day = strArr2[1] + "月" + strArr2[2] + "日";//提取月日
        String time = strArr1[1];//时间
        holder.setText(R.id.tv_time, day + "\n" + time);
        holder.setText(R.id.tv_title, entity.getYTitle());
        holder.setText(R.id.tv_content, entity.getIMsg());
//        holder.setText(R.id.tv_moeny, "¥"+entity.getVMoney());
        holder.setText(R.id.tv_moeny, ""+entity.getYTag());

        //隐藏最后的竖线
        View view = holder.get(R.id.view);
        if (childPosition == (mGroups.get(groupPosition).getList().size() -1)){
            view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }
    }


}
