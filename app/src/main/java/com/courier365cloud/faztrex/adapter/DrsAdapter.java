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
import com.courier365cloud.faztrex.databinding.ItemDrsListBinding;
import com.courier365cloud.faztrex.network.model.response.drs.Drs;
import com.courier365cloud.faztrex.utils.AppConstant;

import java.util.ArrayList;

public class DrsAdapter extends RecyclerView.Adapter<DrsAdapter.MyViewHolder> {

    private final Context mContext;
    private final ArrayList<Drs> drsList;

    public DrsAdapter(Context mContext, ArrayList<Drs> drsList) {
        this.mContext = mContext;
        this.drsList = drsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemDrsListBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_drs_list, viewGroup, false);

        return new MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Drs drs = drsList.get(i);

        // set data using binding
        myViewHolder.setBinding(drs);

        // get drawable background
        GradientDrawable mGradientDrawable = (GradientDrawable) myViewHolder.binding.tvDrsStatus.getBackground();
        mGradientDrawable.setColor(mContext.getResources().getColor(R.color.colorThemeRed));

        if (drs.getDrsStatus().equalsIgnoreCase(AppConstant.STATUS_CLOSED)) {
            myViewHolder.binding.tvDrsStatus.setVisibility(View.VISIBLE);
            //myViewHolder.binding.ivAction.setVisibility(View.GONE);
        } else {
            myViewHolder.binding.tvDrsStatus.setVisibility(View.GONE);
            // myViewHolder.binding.ivAction.setVisibility(View.VISIBLE);
        }

        if (drs.getClosedDrs() != null && drs.getClosedDrs())
            myViewHolder.binding.ivAction.setVisibility(View.VISIBLE);
        else
            myViewHolder.binding.ivAction.setVisibility(View.GONE);

        myViewHolder.itemView.setOnClickListener(v -> {

            OnDrsDetailsClickListener onDrsDetailsClickListener = (OnDrsDetailsClickListener) mContext;
            onDrsDetailsClickListener.onViewClick(myViewHolder.getAdapterPosition(), drs);
        });

        myViewHolder.binding.ivAction.setOnClickListener(v -> {

            OnDrsDetailsClickListener onDrsDetailsClickListener = (OnDrsDetailsClickListener) mContext;
            onDrsDetailsClickListener.onOptionClick(myViewHolder.getAdapterPosition(), drs, myViewHolder.binding.ivAction);
        });
    }

    @Override
    public int getItemCount() {
        return drsList != null ? drsList.size() : 0;
    }

    public interface OnDrsDetailsClickListener {
        void onViewClick(int itemPosition, Drs drs);

        void onOptionClick(int itemPosition, Drs drs, View view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemDrsListBinding binding;

        MyViewHolder(ItemDrsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void setBinding(Drs drs) {
            binding.setDrs(drs);
            binding.executePendingBindings();
            binding.tvDrsNo.setText("#" + (getLayoutPosition() + 1) + ". " + drs.getDrsNo());
        }
    }
}
