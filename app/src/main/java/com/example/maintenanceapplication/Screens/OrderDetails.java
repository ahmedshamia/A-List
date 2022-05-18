package com.example.maintenanceapplication.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.maintenanceapplication.Adapters.MechanismsAdapter;
import com.example.maintenanceapplication.Adapters.OrdersAdapter;
import com.example.maintenanceapplication.DB.Entitys.MaterialsUsed;
import com.example.maintenanceapplication.DB.Entitys.Mechanisms;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Entitys.ReferenceMaterial;
import com.example.maintenanceapplication.DB.Entitys.WorkTasks;
import com.example.maintenanceapplication.DB.Listeners.GetOrdersListener;
import com.example.maintenanceapplication.DB.MyViewModel;
import com.example.maintenanceapplication.Dialogs.AddDialog;
import com.example.maintenanceapplication.Models.MaterialsModel;
import com.example.maintenanceapplication.Models.MechanismsModel;
import com.example.maintenanceapplication.Models.WorkTasksModel;
import com.example.maintenanceapplication.R;
import com.example.maintenanceapplication.Service.Api;
import com.example.maintenanceapplication.databinding.ActivityOrderDetailsBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderDetails extends AppCompatActivity {
    private ActivityOrderDetailsBinding binding;
    private int progr;
    final String BaseUrl = "https://jsonkeeper.com/";
    int SerialNumber;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 3600000;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //مده المهمه
        startStop();


        //هنا سوف يظهر ديالوق الاضافه
        {
            binding.btnAddOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddDialog winnerDialog = AddDialog.newInstance();
                    winnerDialog.show(getSupportFragmentManager(), "Winner Dialog");
                }
            });
        }


        //هنا سيتم جلب بيانات الطلب الذي تم الضغط عليه وعرضه
        {
            Intent intent = getIntent();
            if (intent.getSerializableExtra("order") != null) {
                Orders order = (Orders) intent.getSerializableExtra("order");
                SerialNumber = order.getSeries();
                binding.converter.setText(order.getConverter());
                binding.entrance.setText(order.getEntrance());
                binding.SubscriptionNumber.setText(order.getSubscriptionNumber() + "");
                binding.txtSNumber.setText(order.getSignalNumber() + "");
                binding.txtDate.setText(order.getDate()+"");
                binding.SignalType.setText(order.getSignalType());
                binding.Place.setText(order.getPlace());
                binding.name.setText(order.getName());
                binding.txtMain.setText("هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص أو العديد من النصوص الأخرى ...");
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            }
        }


        //هنا سيتم جلب مهام العمل من ال api وعرضهم
        {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            Api apiInterface = retrofit.create(Api.class);

            apiInterface.getWorkTasks().enqueue(new Callback<List<WorkTasksModel>>() {
                @Override
                public void onResponse(Call<List<WorkTasksModel>> call, Response<List<WorkTasksModel>> response) {

                    WorkTasks workTasks = (response.body().get(0).convertToWorkTasks());
                    binding.OrderDDuration.setText(workTasks.getDuration_Minutes_Hours());
                    binding.OrderDEndTime.setText(workTasks.getEndTime());
                    binding.OrderDEntryDate.setText(workTasks.getEntryDate());
                    binding.OrderDEntryName.setText(workTasks.getEntryName());
                    binding.OrderDMission.setText(workTasks.getMission());
                    binding.OrderDStartTime.setText(workTasks.getStartTime());
                    binding.OrderDJobDescription.setText(workTasks.getTaskDescription());
//                  binding.OrderDSerialNumber.setText(workTasks.getSerialNumber());
                    binding.OrderDStartingDate.setText(workTasks.getExecutionDate());

                }

                @Override
                public void onFailure(Call<List<WorkTasksModel>> call, Throwable t) {
                    Log.d("tt", "sssssssss");
                }
            });

        }


        //هنا سيتم الاليات من ال api وعرضهم
        {
            Retrofit retrofit1 = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            Api api = retrofit1.create(Api.class);

            api.getMechanisms().enqueue(new Callback<List<MechanismsModel>>() {
                @Override
                public void onResponse(Call<List<MechanismsModel>> call, Response<List<MechanismsModel>> response) {
                    Log.d("tet", response.body().get(0).convertToMechanisms().getMechanisms());
                    List<Mechanisms> O = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        O.add(response.body().get(i).convertToMechanisms());
//                    Mechanisms mechanisms = response.body().get(i).convertToMechanisms();
//                    mechanisms.getMechanisms();
//                    myViewModel.insertMechanism(new Mechanisms(mechanisms.getMechanisms()));
                    }
                    binding.rvMechanisms.setHasFixedSize(true);
                    binding.rvMechanisms.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.rvMechanisms.setAdapter(new MechanismsAdapter(O));

                }

                @Override
                public void onFailure(Call<List<MechanismsModel>> call, Throwable t) {
                    Log.d("tt", "sssssssss");
                }
            });
        }


        //هنا سيتم جلب المواد المرجعه و المستخدمه من ال api وعرضهم
        {
            Retrofit retrofit2 = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
            Api api1 = retrofit2.create(Api.class);

            api1.getMaterials().enqueue(new Callback<List<MaterialsModel>>() {
                @Override
                public void onResponse(Call<List<MaterialsModel>> call, Response<List<MaterialsModel>> response) {
                    MaterialsUsed materialsUsed = (response.body().get(0).convertToMaterialsUsed());
                    ReferenceMaterial referenceMaterial = (response.body().get(0).convertToReferenceMaterials());

                    String Used1 = binding.ReferenceMaterialUsed.getText().toString();
                    String Used2 = binding.materialsUsedUsed.getText().toString();
                    float used = Float.parseFloat(Used1 + Used2);
                    String Reference1 = binding.ReferenceMaterialReview.getText().toString();
                    String Reference2 = binding.materialsUsedReview.getText().toString().toString();
                    float Reference = Float.parseFloat(Reference1 + Reference2);

                    //هنا سيتم عرض المستخدم والمرجع الي رح يتم جلبهم من ال api في المخطط
                    PieChart pieChart = binding.PieChart;
                    binding.PieChart.setUsePercentValues(true);
                    pieChart.setHoleRadius(25f);
                    pieChart.setTransparentCircleRadius(25f);

                    List<PieEntry> values = new ArrayList<>();
                    values.add(new PieEntry(Reference, "المرجع"));
                    values.add(new PieEntry(used, "المستخدم"));


                    PieDataSet pieDataSet = new PieDataSet(values, null);
                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);

                    pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    pieChart.animateXY(1400, 1400);

                    //تعبئه للمواد المرجعه
                    binding.ReferenceMaterialCost.setText(referenceMaterial.getCost() + "");
                    binding.ReferenceMaterialItemNo.setText(referenceMaterial.getItemNo() + "");
                    binding.ReferenceMaterialItemStatus.setText(referenceMaterial.getItemStatus());
                    binding.ReferenceMaterialProductName.setText(referenceMaterial.getItemName());
                    binding.ReferenceMaterialQuantity.setText(referenceMaterial.getQuantity() + "");
                    binding.ReferenceMaterialReview.setText(referenceMaterial.getReference() + "");
                    binding.ReferenceMaterialUnit.setText(referenceMaterial.getUnit() + "");
                    binding.ReferenceMaterialUsed.setText(referenceMaterial.getUsed() + "");

                    //تعبئه للمواد المسخدمه
                    binding.materialsUsedCost.setText(materialsUsed.getCost() + "");
                    binding.materialsUsedItemNo.setText(materialsUsed.getItemNo() + "");
                    binding.materialsUsedItemStatus.setText(materialsUsed.getItemStatus());
                    binding.materialsUsedProductName.setText(materialsUsed.getItemName());
                    binding.materialsUsedQuantity.setText(materialsUsed.getQuantity() + "");
                    binding.materialsUsedReview.setText(materialsUsed.getReference() + "");
                    binding.materialsUsedUnit.setText(materialsUsed.getUnit() + "");
                    binding.materialsUsedUsed.setText(materialsUsed.getUsed() + "");
                }

                @Override
                public void onFailure(Call<List<MaterialsModel>> call, Throwable t) {

                }
            });
        }


        //بيانات الفرق الفنيه
        {
            ArrayList<String> atrBand = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                atrBand.add("atrBand " + i);
            }
            binding.atrBandSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, atrBand));
        }


        // هان كنت اجرب اخزن ع الداتا بيز
      /*
        myViewModel.insertMechanism(new Mechanisms("فحص الاحمال"),new Mechanisms("تركيب العداد"),new Mechanisms("فحص العداد"));
        myViewModel.insertMaterialsUsed(new MaterialsUsed(112, 2, 3, 2, 800, 3, "عداد كهرباء", "جيد"));
        myViewModel.insertReferenceMaterial(new ReferenceMaterial(4, 44, 55, 3, "عداد الكهرباء", "جيد", 750, 16));
        myViewModel.insertWorkTask(new WorkTasks("تركيب عداد", "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك أن تولد مثل هذا النص أو العديد من النصوص الأخرى ...", "16:45", "17:30", "5.30", "محمد احمد", "09/04/2022","09/04/2022"));
        LiveData<List<Mechanisms>> mechanismsArrayList = myViewModel.getAllMechanisms();
        mechanismsArrayList.observe(this, new Observer<List<Mechanisms>>() {
            @Override
            public void onChanged(List<Mechanisms> mechanisms) {
                binding.rvMechanisms.setHasFixedSize(true);
                binding.rvMechanisms.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                binding.rvMechanisms.setAdapter(new MechanismsAdapter(mechanisms));
            }
        });

       */


    }

    //هاي تبعت الوقت في مهام العمل

    private void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateTimer();


            }

            @Override
            public void onFinish() {

            }
        }.start();
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000;

        binding.progressBar.setProgress(minutes);
        binding.progressBar.setMax(60);
        //binding.progressBar.setMax(100);

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        binding.textViewProgress.setText(timeLeftText);
    }

}