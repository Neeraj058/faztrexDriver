package com.courier365cloud.faztrex.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.model.NavigationMenuModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<NavigationMenuModel> navHeaderMenuList;
    private LinkedHashMap<NavigationMenuModel, List<NavigationMenuModel>> navChildMenuList;

    public ExpandableListAdapter(Context mContext, List<NavigationMenuModel> navHeaderMenuList, LinkedHashMap<NavigationMenuModel, List<NavigationMenuModel>> navChildMenuList) {
        this.mContext = mContext;
        this.navHeaderMenuList = navHeaderMenuList;
        this.navChildMenuList = navChildMenuList;
    }

    @Override
    public int getGroupCount() {
        return navHeaderMenuList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (navChildMenuList.get(navHeaderMenuList.get(groupPosition)) == null)
            return 0;
        else
            return Objects.requireNonNull(navChildMenuList.get(navHeaderMenuList.get(groupPosition))).size();
    }

    @Override
    public NavigationMenuModel getGroup(int groupPosition) {
        return navHeaderMenuList.get(groupPosition);
    }

    @Override
    public NavigationMenuModel getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(navChildMenuList.get(navHeaderMenuList.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = getGroup(groupPosition).menuName;
        Drawable headerIcon = getGroup(groupPosition).menuIcon;

        if (convertView == null) {
            //LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = mLayoutInflater.inflate(R.layout.item_nav_menu_header, null);
        }

        TextView tvHeaderTitle = convertView.findViewById(R.id.tv_header_title);
        //ImageView ivHeaderIcon = convertView.findViewById(R.id.iv_header_icon);

        //tvHeaderTitle.setTypeface(null, Typeface.BOLD);
        tvHeaderTitle.setText(headerTitle);
        //ivHeaderIcon.setImageDrawable(headerIcon);

        return convertView;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;
        final Drawable childIcon = getChild(groupPosition, childPosition).menuIcon;

        if (convertView == null) {
            //LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = mLayoutInflater.inflate(R.layout.item_nav_menu_child, null);
        }

        TextView tvChildTitle = convertView.findViewById(R.id.tv_child_title);
        //ImageView ivChildIcon = convertView.findViewById(R.id.iv_child_icon);

        tvChildTitle.setText(childText);
        //ivChildIcon.setImageDrawable(childIcon);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
