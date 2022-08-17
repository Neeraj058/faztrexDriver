package com.courier365cloud.faztrex.baseclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.courier365cloud.faztrex.BR;
import com.courier365cloud.faztrex.databinding.ItemHyperLocalRequestBinding;
import com.courier365cloud.faztrex.listener.HyperLocalItemClickListener;
import com.courier365cloud.faztrex.listener.general.CommonActionListener;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalList;

import java.util.ArrayList;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    private final int rawLayoutId;
    private final Context mContext;
    private ArrayList<?> itemsArrayList;
    private CommonActionListener commonActionListener;
    private boolean isView;
    private HyperLocalItemClickListener hyperLocalItemClickListener;

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.commonActionListener = commonActionListener;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, HyperLocalItemClickListener hyperLocalItemClickListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.hyperLocalItemClickListener = hyperLocalItemClickListener;
    }

    public BaseAdapter(Context mContext, ArrayList<?> itemsArrayList, int rawLayoutId, boolean isView, CommonActionListener commonActionListener) {
        this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
        this.rawLayoutId = rawLayoutId;
        this.isView = isView;
        this.commonActionListener = commonActionListener;
    }

    @SuppressLint("NonConstantResourceId")
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), rawLayoutId, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(itemsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsArrayList != null ? itemsArrayList.size() : 0;
    }

    public void filterList(ArrayList<?> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        public MyViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint({"SimpleDateFormat", "UseCompatLoadingForDrawables", "SetTextI18n"})
        void setBinding(Object obj) {

            binding.setVariable(BR.data, obj);
            binding.executePendingBindings();

            if (binding instanceof ItemHyperLocalRequestBinding) {

                HyperLocalList hyperLocalList = (HyperLocalList) obj;

                    /*if (!hyperLocalList.getStatusName().equalsIgnoreCase("DELIVERED"))
                        ((ItemHyperLocalRequestBinding) binding).ivAction.setVisibility(View.GONE);
                    else
                        ((ItemHyperLocalRequestBinding) binding).ivAction.setVisibility(View.VISIBLE);*/

                if (hyperLocalItemClickListener == null)
                    hyperLocalItemClickListener = (HyperLocalItemClickListener) mContext;

                ((ItemHyperLocalRequestBinding) binding).tvHLRNo.setText("#" + (getLayoutPosition() + 1 + ". " + hyperLocalList.getNo()));

                ((ItemHyperLocalRequestBinding) binding).ivAction.setOnClickListener(v -> hyperLocalItemClickListener.onActionClick(getLayoutPosition(), hyperLocalList, ((ItemHyperLocalRequestBinding) binding).ivAction));
            }
        }
    }
}
