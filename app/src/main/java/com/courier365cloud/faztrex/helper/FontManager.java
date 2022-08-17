package com.courier365cloud.faztrex.helper;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String ICON_NAME = "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}

//Typeface iconFont = FontManager.getTypeface(mContext, FontManager.LINE_ICONS);
//myViewHolder.binding.tvPriority.setTypeface(iconFont);
//myViewHolder.binding.tvPriority.setText(mContext.getResources().getString(R.string.icon_flag));