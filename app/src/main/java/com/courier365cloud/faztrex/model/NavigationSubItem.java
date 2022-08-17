package com.courier365cloud.faztrex.model;

import android.graphics.drawable.Drawable;

public class NavigationSubItem {

    private String subMenuName;

    private Drawable subMenuIcon;

    private boolean isVisible;

    public NavigationSubItem(String subMenuName, Drawable subMenuIcon, boolean isVisible) {
        this.subMenuName = subMenuName;
        this.subMenuIcon = subMenuIcon;
        this.isVisible = isVisible;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public Drawable getSubMenuIcon() {
        return subMenuIcon;
    }

    public void setSubMenuIcon(Drawable subMenuIcon) {
        this.subMenuIcon = subMenuIcon;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
