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
import com.courier365cloud.faztrex.databinding.ItemDocketListBinding;
import com.courier365cloud.faztrex.network.model.response.docket.Docket;
import com.courier365cloud.faztrex.utils.AppConstant;

import java.util.ArrayList;

public class DocketAdapter extends RecyclerView.Adapter<DocketAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Docket> docketList;

    public DocketAdapter(Context mContext, ArrayList<Docket> docketList) {
        this.mContext = mContext;
        this.docketList = docketList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemDocketListBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_docket_list, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        GradientDrawable mGradientDrawable;

        Docket docket = docketList.get(i);

        // set data using binding
        myViewHolder.setBinding(docket);

        // get drawable background
        mGradientDrawable = (GradientDrawable) myViewHolder.binding.tvDeliveryStatus.getBackground();

        // set drawable background color
        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorThemeRed));

        if (docket.getDeliveryStatus().equalsIgnoreCase(AppConstant.STATUS_DELIVERED)) {
            myViewHolder.binding.tvDeliveryStatus.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.binding.tvDeliveryStatus.setVisibility(View.GONE);
        }

        // Customer Type
        switch (docket.getCustomerType()) {

            case AppConstant.CUSTOMER_TYPE_CREDIT:

                myViewHolder.binding.ivCustomerType.setVisibility(View.GONE);
                myViewHolder.binding.tvPaymentType.setVisibility(View.GONE);
                break;

            case AppConstant.CUSTOMER_TYPE_RETAIL:

                myViewHolder.binding.ivCustomerType.setVisibility(View.VISIBLE);
                myViewHolder.binding.tvPaymentType.setVisibility(View.VISIBLE);

                // get drawable background
                mGradientDrawable = (GradientDrawable) myViewHolder.binding.tvPaymentType.getBackground();

                switch (docket.getPaymentType()) {

                    case AppConstant.PAY_TYPE_PAID:

                        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreenShade1));
                        myViewHolder.binding.tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorLightWhite));
                        break;

                    case AppConstant.PAY_TYPE_TO_PAY:

                        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorGreyShade3));
                        myViewHolder.binding.tvPaymentType.setTextColor(mContext.getResources().getColor(R.color.colorTextPrimary));
                        break;
                }

                break;
        }

        // Dispatch Mode
        switch (docket.getDispatchMode()) {

            case AppConstant.MODE_SURFACE:

                myViewHolder.binding.ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mode_surface));
                break;

            case AppConstant.MODE_AIR:

                myViewHolder.binding.ivDispatchMode.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mode_air));
                break;
        }

        myViewHolder.binding.ivAction.setOnClickListener(v -> {

            OnDocketDetailsClickListener onDocketDetailsClickListener = (OnDocketDetailsClickListener) mContext;
            onDocketDetailsClickListener.onActionClick(myViewHolder.getLayoutPosition(), docket, myViewHolder.binding.ivAction);
        });
    }

    @Override
    public int getItemCount() {
        return docketList != null ? docketList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemDocketListBinding binding;

        MyViewHolder(ItemDocketListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        void setBinding(Docket docket) {
            binding.setDocket(docket);
            binding.executePendingBindings();
            binding.tvDocketNo.setText("#" + (getLayoutPosition() + 1) + ". " + docket.getDocketNo());
            binding.tvPaymentType.setText(docket.getPaymentType());
        }
    }

    public interface OnDocketDetailsClickListener {

        //void onViewClick(int itemPosition, Docket docket);

        void onActionClick(int itemPosition, Docket docket, View view);
    }
}
