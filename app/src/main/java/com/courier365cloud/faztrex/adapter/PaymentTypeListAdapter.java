package com.courier365cloud.faztrex.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.databinding.ItemPaymentTypeListBinding;
import com.courier365cloud.faztrex.network.model.request.PaymentTypeModel;

import java.util.ArrayList;

public class PaymentTypeListAdapter extends RecyclerView.Adapter<PaymentTypeListAdapter.MyViewHolder> {

    private final Context mContext;

    private final ArrayList<PaymentTypeModel> paymentTypeModelArrayList;

    private OnPaymentTypeClickListener listener;

    public PaymentTypeListAdapter(Context mContext, ArrayList<PaymentTypeModel> paymentTypeModelArrayList) {
        this.mContext = mContext;
        this.paymentTypeModelArrayList = paymentTypeModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPaymentTypeListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(
                        parent.getContext()), R.layout.item_payment_type_list, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PaymentTypeModel paymentTypeModel = paymentTypeModelArrayList.get(position);

        ItemPaymentTypeListBinding binding = holder.binding;
        binding.ivEdit.setOnClickListener(v -> {
            if (listener == null)
                listener = (OnPaymentTypeClickListener) mContext;
            listener.onUpdate(holder.getLayoutPosition(), paymentTypeModel);
        });

        binding.ivDelete.setOnClickListener(v -> {
            if (listener == null)
                listener = (OnPaymentTypeClickListener) mContext;
            listener.onDelete(holder.getLayoutPosition(), paymentTypeModel);
        });

        holder.setBinding(paymentTypeModel);

    }

    @Override
    public int getItemCount() {
        return paymentTypeModelArrayList != null ? paymentTypeModelArrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemPaymentTypeListBinding binding;

        AdapterView.OnItemSelectedListener listener;

        public MyViewHolder(@NonNull ItemPaymentTypeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void setBinding(PaymentTypeModel paymentTypeModel) {
            binding.tvSerialNo.setText("#" + (getLayoutPosition() + 1));
            binding.setData(paymentTypeModel);
            binding.executePendingBindings();

            /*binding.tvPaymentAmount.setText(paymentTypeModel.getPaymentAmount());
            binding.tvPaymentType.setText(paymentTypeModel.getPaymentType());
            binding.tvReferenceValue.setText(paymentTypeModel.getReference());
            binding.tvReferenceNoValue.setText(paymentTypeModel.getReferenceNo());
            binding.tvReferenceDateValue.setText(paymentTypeModel.getReferenceDate());
            binding.tvRemarksValue.setText(paymentTypeModel.getRemarks());*/
        }
    }

    public interface OnPaymentTypeClickListener {

        void onUpdate(int itemPosition, PaymentTypeModel paymentTypeModel);

        void onDelete(int itemPosition, PaymentTypeModel paymentTypeModel);
    }
}
