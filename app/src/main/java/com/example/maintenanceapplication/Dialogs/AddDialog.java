package com.example.maintenanceapplication.Dialogs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.MyViewModel;
import com.example.maintenanceapplication.R;
import com.example.maintenanceapplication.databinding.CustomAddDialogBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDialog extends DialogFragment {
    final Calendar CalendarDate = Calendar.getInstance();
    CustomAddDialogBinding binding;

    public static AddDialog newInstance() {
        AddDialog fragment = new AddDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.custom_add_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = CustomAddDialogBinding.bind(view);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //زر اغلاق الديالوق
        binding.AddClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        //تفريغ الحقول
        {
            binding.AddEmptyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.AddCitizenName.setText("");
                    binding.AddEntryName.setText("");
                    binding.AddChooseConverter.setText("");
                    binding.AddSignalNumber.setText("");
                    binding.AddSubscriptionNumber.setText("");
                    binding.AddChooseLocation.setText("");
                    binding.AddChooseRegion.setText("");
                    binding.AddChooseProvince.setText("");
                    binding.AddSignalType.setText("");
                    binding.date.setText("");
                }
            });
        }

        //بيانات وهميه للعناصر اليست
        {
            ArrayList<String> Locations = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Locations.add("الشارع" + i);
            }
            binding.AddChooseLocation.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Locations));

            ArrayList<String> Regions = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Regions.add("المنطقة" + i);
            }
            binding.AddChooseRegion.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Regions));

            ArrayList<String> Provinces = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Provinces.add("غزه" + i);
            }
            binding.AddChooseProvince.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Provinces));

            ArrayList<String> Converters = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Converters.add("المحول" + i);
            }
            binding.AddChooseConverter.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Converters));

            ArrayList<String> SignalTypes = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                SignalTypes.add("بيتا" + i);
            }
            binding.AddSignalType.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SignalTypes));

        }


        //اختيار التاريخ
        {
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    CalendarDate.set(Calendar.YEAR, year);
                    CalendarDate.set(Calendar.MONTH, month);
                    CalendarDate.set(Calendar.DAY_OF_MONTH, day);
                    updateLabel();
                }
            };
            binding.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(getActivity(), date, CalendarDate.get(Calendar.YEAR), CalendarDate.get(Calendar.MONTH), CalendarDate.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }

        //حفظ البيانات في الداتا بيز وععرضها في الادبتر
        {
            binding.AddSaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!binding.AddCitizenName.getText().toString().isEmpty() &&
                            !binding.AddEntryName.getText().toString().isEmpty() &&
                            !binding.AddChooseConverter.getText().toString().isEmpty() &&
                            !binding.AddSubscriptionNumber.getText().toString().isEmpty() &&
                            !binding.AddChooseLocation.getText().toString().isEmpty() &&
                            !binding.AddChooseRegion.getText().toString().isEmpty() &&
                            !binding.AddChooseProvince.getText().toString().isEmpty() &&
                            !binding.AddSignalType.getText().toString().isEmpty() &&
                            !binding.date.getText().toString().isEmpty()) {

                        String CitizenName = binding.AddCitizenName.getText().toString();
                        String EntryName = binding.AddEntryName.getText().toString();
                        String ChooseConverter = binding.AddChooseConverter.getText().toString();
                        int SubscriptionNumber = Integer.parseInt(binding.AddSubscriptionNumber.getText().toString());
                        int SignalNumber = Integer.parseInt(binding.AddSignalNumber.getText().toString());
                        String ChooseLocation = binding.AddChooseLocation.getText().toString();
                        String ChooseRegion = binding.AddChooseRegion.getText().toString();
                        String ChooseProvince = binding.AddChooseProvince.getText().toString();
                        String SignalType = binding.AddSignalType.getText().toString();

                        //Date date = (Date) binding.date.getText();


                        Orders orders = new Orders(SignalNumber, SubscriptionNumber, CitizenName, ChooseLocation, ChooseConverter, SignalType, EntryName, ChooseRegion, ChooseProvince, CalendarDate.getTime());
                        myViewModel.insertOrder(orders);
                        dismiss();


                    } else {
                        Toast.makeText(getActivity(), "you must fill all the inputs", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    //تحديث حقل التاربخ
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.date.setText(dateFormat.format(CalendarDate.getTime()));
    }
}