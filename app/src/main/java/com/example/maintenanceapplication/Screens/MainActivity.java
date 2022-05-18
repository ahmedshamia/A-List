package com.example.maintenanceapplication.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maintenanceapplication.Adapters.OrdersAdapter;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Listeners.GetOrderAdapterListener;
import com.example.maintenanceapplication.DB.Listeners.GetOrdersListener;
import com.example.maintenanceapplication.DB.MyViewModel;
import com.example.maintenanceapplication.Dialogs.AddDialog;
import com.example.maintenanceapplication.Dialogs.FilterDialog;
import com.example.maintenanceapplication.Models.OrdersModel;
import com.example.maintenanceapplication.Service.Api;
import com.example.maintenanceapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements GetOrderAdapterListener {
    private ActivityMainBinding binding;
    final String BaseUrl = "https://jsonkeeper.com/";
    OrdersAdapter ordersAdapter;
    LifecycleOwner lifecycleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        lifecycleOwner = this;

        // الادبتر

        ordersAdapter = new OrdersAdapter(new ArrayList<>(), new GetOrdersListener() {
            @Override
            public void GetOrders(Orders orders) {
                Intent intent = new Intent(getBaseContext(), OrderDetails.class);
                intent.putExtra("order", orders);
                startActivity(intent);
            }
        });

        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        binding.rv.setAdapter(ordersAdapter);

        //هنا سيتم جلب البيانات من قاعده البيانات وعرضها في الاتبتر
        {
            LiveData<List<Orders>> ordersArrayList = myViewModel.getAllOrders();
            ordersArrayList.observe(this, new Observer<List<Orders>>() {
                @Override
                public void onChanged(List<Orders> orders) {
                    ordersAdapter.setOrdersList(orders);
                }
            });
        }

        // هنا رح يجيب طلبات الصيانه من ال api وعرضها في الاتبتر


        {
            /*
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            Api apiInterface = retrofit.create(Api.class);

            apiInterface.getOrders().enqueue(new Callback<List<OrdersModel>>() {
                @Override
                public void onResponse(Call<List<OrdersModel>> call, Response<List<OrdersModel>> response) {
                    Log.d("tet", response.body().get(0).convertToOrder().getName());
                    List<Orders> O = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        O.add(response.body().get(i).convertToOrder());
                    }
                    binding.rv.setHasFixedSize(true);
                    binding.rv.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                    binding.rv.setAdapter(ordersAdapter);
                    ordersAdapter.setOrdersList(O);
                }

                @Override
                public void onFailure(Call<List<OrdersModel>> call, Throwable t) {
                    Log.d("tt", "sssssssss");
                }
            });

             */
        }



        //هنا سوف يظهر ديالوق الفلتره
        {
            binding.btnFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FilterDialog filterDialog = FilterDialog.newInstance();
                    filterDialog.show(getSupportFragmentManager(), "filter Dialog");
                }
            });
        }

        //هنا سوف يظهر ديالوق الاضافه
        {
            binding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddDialog addDialog = AddDialog.newInstance();
                    addDialog.show(getSupportFragmentManager(), "add Dialog");
                }
            });
        }

        //هنا البحث في طلبات الصيانه
        {
            binding.MainCitizenName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    myViewModel.findOrdersWithName(newText).observe(lifecycleOwner, new Observer<List<Orders>>() {
                        @Override
                        public void onChanged(List<Orders> order) {
                            ordersAdapter.setOrdersList(order);
                            ordersAdapter.getOrdersList();
                            ordersAdapter.notifyDataSetChanged();

                        }
                    });
                    return false;
                }
            });
            binding.MainCitizenName.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    binding.rv.setHasFixedSize(true);
                    binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    binding.rv.setAdapter(ordersAdapter);
                    return false;
                }
            });

            binding.MainSubscriptionNumber.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    try {
                        myViewModel.findOrdersWithSubscriptionNumber(Integer.parseInt(newText)).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> order) {
                                ordersAdapter.setOrdersList(order);
                                ordersAdapter.notifyDataSetChanged();
                            }
                        });

                    } catch (Exception e) {
                        Log.d("ttt", e.getMessage());
                    }

                    return false;
                }


            });
            binding.MainSubscriptionNumber.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    LiveData<List<Orders>> ordersArrayList = myViewModel.getAllOrders();
                    ordersArrayList.observe(lifecycleOwner, new Observer<List<Orders>>() {
                        @Override
                        public void onChanged(List<Orders> orders) {
                            ordersAdapter.setOrdersList(orders);
                        }
                    });
                    return false;
                }
            });


            binding.btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = binding.MainCitizenName.getQuery().toString();
                    String num = binding.MainSubscriptionNumber.getQuery().toString();
                    if (num.isEmpty()) {
                        return;
                    }
                    myViewModel.findOrdersWithNameAndNum(name, Integer.parseInt(num)).observe(lifecycleOwner, new Observer<List<Orders>>() {
                        @Override
                        public void onChanged(List<Orders> orders) {
                            ordersAdapter.setOrdersList(orders);
                            ordersAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void GetOrders(List<Orders> orders) {
        ordersAdapter.setOrdersList(orders);
        ordersAdapter.notifyDataSetChanged();
    }
}
