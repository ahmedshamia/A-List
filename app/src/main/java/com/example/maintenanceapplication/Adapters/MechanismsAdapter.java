package com.example.maintenanceapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.R;
import com.example.maintenanceapplication.databinding.CustomMechanismsLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class MechanismsAdapter extends RecyclerView.Adapter<MechanismsAdapter.Holder>{
    private List<Mechanisms> mechanisms;

    public MechanismsAdapter(List<Mechanisms> mechanisms) {
        this.mechanisms = mechanisms;
    }

    public List<Mechanisms> getMechanisms() {
        return mechanisms;
    }

    public void setMechanisms(List<Mechanisms> mechanisms) {
        this.mechanisms = mechanisms;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_mechanisms_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(mechanisms.get(position));
    }

    @Override
    public int getItemCount() {
        return mechanisms.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private CustomMechanismsLayoutBinding binding;
        public Holder(@NonNull View itemView) {
            super(itemView);
            binding  = CustomMechanismsLayoutBinding.bind(itemView);
            //Mechanisms mechanism;
        }
        public void bind(Mechanisms mechanisms){
            binding.txtCounter.setText(mechanisms.getSerialNumber()+"");
            binding.txtMechanisms.setText(mechanisms.getMechanisms());

        }
    }
}
