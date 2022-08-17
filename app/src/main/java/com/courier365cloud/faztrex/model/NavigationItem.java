package com.courier365cloud.faztrex.model;

import android.graphics.drawable.Drawable;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class NavigationItem implements Parent<NavigationSubItem> {

    private String menuName;

    private Drawable menuIcon;

    private boolean isVisible;

    private List<NavigationSubItem> menuSubItemList;

    public NavigationItem(String menuName, Drawable menuIcon, boolean isVisible, List<NavigationSubItem> menuSubItemList) {
        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.isVisible = isVisible;
        this.menuSubItemList = menuSubItemList;
    }

    @Override
    public List<NavigationSubItem> getChildList() {
        return menuSubItemList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Drawable getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(Drawable menuIcon) {
        this.menuIcon = menuIcon;
    }

    public List<NavigationSubItem> getMenuSubItemList() {
        return menuSubItemList;
    }

    public void setMenuSubItemList(List<NavigationSubItem> menuSubItemList) {
        this.menuSubItemList = menuSubItemList;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
