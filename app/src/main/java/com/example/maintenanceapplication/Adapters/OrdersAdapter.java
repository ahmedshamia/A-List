package com.example.maintenanceapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Listeners.GetOrdersListener;
import com.example.maintenanceapplication.R;
import com.example.maintenanceapplication.databinding.CustomCardBinding;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Holder> {
    private List<Orders> ordersList;
    GetOrdersListener listener;

    public OrdersAdapter(List<Orders> ordersList, GetOrdersListener listener) {
        this.ordersList = ordersList;
        this.listener = listener;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(ordersList.get(position));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        CustomCardBinding binding;
        Orders orders;

        public Holder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCardBinding.bind(itemView);

            binding.OptionsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.GetOrders(orders);
                }
            });
        }

        public void bind(Orders orders) {
            this.orders = orders;
            binding.converter.setText(orders.getConverter());
            binding.entrance.setText(orders.getEntrance());
            binding.SubscriptionNumber.setText(orders.getSubscriptionNumber() + "");
            binding.txtSNumber.setText(orders.getSignalNumber()+"");
            binding.txtDate.setText(orders.getDate()+"");
            binding.SignalType.setText(orders.getSignalType());
            binding.Place.setText(orders.getPlace());
            binding.name.setText(orders.getName());
            binding.txtMain.setText("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص أو العديد من النصوص الأخرى ...");

        }
    }
}
