package com.courier365cloud.faztrex.model;

import android.graphics.drawable.Drawable;

public class NavigationMenuModel {

    public String menuName;
    public Drawable menuIcon;
    public boolean hasChildren, isGroup;

    public NavigationMenuModel(String menuName, Drawable menuIcon, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;
        this.menuIcon = menuIcon;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
