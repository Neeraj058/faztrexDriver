package com.courier365cloud.faztrex.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.databinding.ItemNavMenuChildBinding;
import com.courier365cloud.faztrex.databinding.ItemNavMenuHeaderBinding;
import com.courier365cloud.faztrex.model.NavigationItem;
import com.courier365cloud.faztrex.model.NavigationSubItem;

import java.util.List;

public class NavigationItemExpandableAdapter extends ExpandableRecyclerAdapter<NavigationItem, NavigationSubItem, NavigationItemExpandableAdapter.ItemParentViewHolder, NavigationItemExpandableAdapter.ItemChildViewHolder> {

    private final String TAG = this.getClass().getSimpleName();

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    private Context mContext;

    private OnNavigationMenuItemClickListener navigationMenuItemClickListener;

    public NavigationItemExpandableAdapter(Context mContext, @NonNull List<NavigationItem> parentList, OnNavigationMenuItemClickListener navigationMenuItemClickListener) {
        super(parentList);
        this.mContext = mContext;
        this.navigationMenuItemClickListener = navigationMenuItemClickListener;
    }

    @NonNull
    @Override
    public ItemParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parentViewGroup.getContext());
        ItemNavMenuHeaderBinding itemNavMenuHeaderBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_nav_menu_header, parentViewGroup, false);

        return new ItemParentViewHolder(itemNavMenuHeaderBinding);
    }

    @NonNull
    @Override
    public ItemChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(childViewGroup.getContext());
        ItemNavMenuChildBinding itemNavMenuChildBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_nav_menu_child, childViewGroup, false);

        return new ItemChildViewHolder(itemNavMenuChildBinding);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ItemParentViewHolder parentViewHolder, int parentPosition, @NonNull NavigationItem navigationItem) {

        try {

            if (navigationItem.isVisible()) {

                parentViewHolder.itemNavMenuHeaderBinding.tvHeaderTitle.setText(navigationItem.getMenuName());
                parentViewHolder.itemNavMenuHeaderBinding.ivHeaderIcon.setImageDrawable(navigationItem.getMenuIcon());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onBindChildViewHolder(@NonNull ItemChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull NavigationSubItem navigationSubItem) {

        try {

            if (navigationSubItem.isVisible()) {

                childViewHolder.itemNavMenuChildBinding.tvChildTitle.setText(navigationSubItem.getSubMenuName());
                childViewHolder.itemNavMenuChildBinding.ivChildIcon.setImageDrawable(navigationSubItem.getSubMenuIcon());

                childViewHolder.itemView.setOnClickListener(v -> navigationMenuItemClickListener.onChildMenuClick(childViewHolder.getParentAdapterPosition(), childViewHolder.getChildAdapterPosition(), navigationSubItem));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    class ItemParentViewHolder extends ParentViewHolder {

        private ItemNavMenuHeaderBinding itemNavMenuHeaderBinding;

        ItemParentViewHolder(ItemNavMenuHeaderBinding itemNavMenuHeaderBinding) {

            super(itemNavMenuHeaderBinding.getRoot());

            this.itemNavMenuHeaderBinding = itemNavMenuHeaderBinding;
        }

        @SuppressLint("NewApi")
        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);

            if (expanded) {
                itemNavMenuHeaderBinding.ivArrowExpandMenu.setRotation(ROTATED_POSITION);
            } else {
                itemNavMenuHeaderBinding.ivArrowExpandMenu.setRotation(INITIAL_POSITION);
            }
        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);

            RotateAnimation rotateAnimation;

            if (expanded) {

                // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

            } else {

                // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(300);
            rotateAnimation.setFillAfter(false);
            itemNavMenuHeaderBinding.ivArrowExpandMenu.startAnimation(rotateAnimation);
        }
    }

    class ItemChildViewHolder extends ChildViewHolder {

        private ItemNavMenuChildBinding itemNavMenuChildBinding;

        ItemChildViewHolder(ItemNavMenuChildBinding itemNavMenuChildBinding) {

            super(itemNavMenuChildBinding.getRoot());

            this.itemNavMenuChildBinding = itemNavMenuChildBinding;
        }
    }

    public interface OnNavigationMenuItemClickListener {

        void onChildMenuClick(int parentPosition, int childPosition, NavigationSubItem navigationSubItem);
    }
}
