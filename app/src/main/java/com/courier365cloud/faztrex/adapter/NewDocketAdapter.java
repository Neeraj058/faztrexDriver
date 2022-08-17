package com.courier365cloud.faztrex.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.databinding.ItemNewDrsDocketListBinding;
import com.courier365cloud.faztrex.network.model.response.NewDriverDocket;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.utils.AppConstant;

import java.util.ArrayList;

public class NewDocketAdapter extends RecyclerView.Adapter<NewDocketAdapter.MyViewHolder> {

    private final Context mContext;
    private final ArrayList<NewDriverDocket> newDriverDocketArrayList;

    public NewDocketAdapter(Context mContext, ArrayList<NewDriverDocket> newDriverDocketArrayList) {
        this.mContext = mContext;
        this.newDriverDocketArrayList = newDriverDocketArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemNewDrsDocketListBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_new_drs_docket_list, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        NewDriverDocket drsDocket = newDriverDocketArrayList.get(i);

        // set data using binding
        myViewHolder.setBinding(drsDocket);

        // get drawable background
        GradientDrawable mGradientDrawable = (GradientDrawable) myViewHolder.binding.tvDeliveryStatus.getBackground();

        // set drawable background color
        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorThemeRed));

        if (drsDocket.getDeliveryStatusName() != null && drsDocket.getDeliveryStatusName().equalsIgnoreCase(AppConstant.STATUS_DELIVERED)) {

            myViewHolder.binding.tvDeliveryStatus.setVisibility(View.VISIBLE);
            myViewHolder.binding.containerSection5.setVisibility(View.GONE);

        } else {

            myViewHolder.binding.tvDeliveryStatus.setVisibility(View.GONE);
            myViewHolder.binding.containerSection5.setVisibility(View.VISIBLE);

        }

        // Customer Type
        switch (drsDocket.getCustomerType()) {

            case AppConstant.CUSTOMER_TYPE_CREDIT:

                myViewHolder.binding.ivCustomerType.setVisibility(View.GONE);
                myViewHolder.binding.tvPaymentType.setVisibility(View.GONE);
                break;

            case AppConstant.CUSTOMER_TYPE_RETAIL:

                myViewHolder.binding.ivCustomerType.setVisibility(View.VISIBLE);
                myViewHolder.binding.tvPaymentType.setVisibility(View.VISIBLE);

                // get drawable background
                mGradientDrawable = (GradientDrawable) myViewHolder.binding.tvPaymentType.getBackground();

                switch (drsDocket.getPaymentType()) {
                    case AppConstant.PAY_TYPE_PAID:
                        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreenShade1));
                        myViewHolder.binding.tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorLightWhite));
                        break;

                    case AppConstant.PAY_TYPE_TO_PAY:
                    case AppConstant.PAY_TYPE_COD:
                        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreyShade3));
                        myViewHolder.binding.tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorTextPrimary));
                        break;
                }
                break;
        }

        // Dispatch Mode
        switch (drsDocket.getDispatchMode()) {

            case AppConstant.MODE_SURFACE:

                myViewHolder.binding.ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mode_surface));
                break;

            case AppConstant.MODE_AIR:

                myViewHolder.binding.ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mode_air));
                break;
        }

        myViewHolder.binding.ivAction.setOnClickListener(v -> {
            OnNewDocketDetailsClickListener onDocketDetailsClickListener = (OnNewDocketDetailsClickListener) mContext;
            onDocketDetailsClickListener.onOptionClick(myViewHolder.getLayoutPosition(), drsDocket, myViewHolder.binding.ivAction);
        });

       /* myViewHolder.binding.tvDeliveryStatus.setOnClickListener(v -> {
            OnDocketDetailsClickListener onDocketDetailsClickListener = (OnDocketDetailsClickListener) mContext;
            onDocketDetailsClickListener.onSecureDeliveryClick(myViewHolder.getLayoutPosition());
        });*/


    }

    @Override
    public int getItemCount() {
        return newDriverDocketArrayList != null ? newDriverDocketArrayList.size() : 0;
    }

    public interface OnNewDocketDetailsClickListener {
        void onOptionClick(int position, NewDriverDocket drsDocket, View view);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemNewDrsDocketListBinding binding;

        MyViewHolder(ItemNewDrsDocketListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void setBinding(NewDriverDocket drsDocket) {
            binding.setDrsDocket(drsDocket);
            binding.executePendingBindings();
            binding.tvDocketNo.setText(("#" + (getLayoutPosition() + 1)) + ". " + drsDocket.getDocketNo());

            if (drsDocket.getDeliveryStatusName() != null && !drsDocket.getDeliveryStatusName().equals("")) {
                binding.containerSection5.setVisibility(View.VISIBLE);
            } else {
                binding.containerSection5.setVisibility(View.GONE);
            }
        }
    }
}
