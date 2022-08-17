package com.courier365cloud.faztrex.listener.general;

import android.view.View;

public interface CommonActionListener {

    void onViewClick(int itemPosition, Object object);

    void onInfoClick(int itemPosition, Object object, View... views);

    void onEditClick(int itemPosition, Object object);

    void onDeleteClick(int itemPosition, Object object);

}
