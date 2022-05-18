package com.example.maintenanceapplication.Dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.maintenanceapplication.DB.Daos.Int_Dates;
import com.example.maintenanceapplication.DB.Entitys.Orders;
import com.example.maintenanceapplication.DB.Listeners.GetOrderAdapterListener;
import com.example.maintenanceapplication.DB.MyViewModel;
import com.example.maintenanceapplication.R;
import com.example.maintenanceapplication.databinding.CustomFilterDialogBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FilterDialog extends DialogFragment {
    final Calendar CalendarFrom = Calendar.getInstance();
    final Calendar CalendarTO = Calendar.getInstance();
    LifecycleOwner lifecycleOwner;
    Int_Dates int_dates;
    CustomFilterDialogBinding binding;
    GetOrderAdapterListener getOrderAdapterListener;
    StringBuilder string;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof GetOrderAdapterListener) {
            getOrderAdapterListener = (GetOrderAdapterListener) context;
        }
    }

    public static FilterDialog newInstance() {
        FilterDialog fragment = new FilterDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.custom_filter_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = CustomFilterDialogBinding.bind(view);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        lifecycleOwner = this;

        //زر اغلاق الديالوق
        binding.FilterClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //تفريغ الحقول
        {
            binding.FilterEmptyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.FilterCitizenName.setText("");
                    binding.FilterEntryName.setText("");
                    binding.FilterChooseConverter.setText("");
                    binding.FilterSignalNumber.setText("");
                    binding.FilterSubscriptionNumber.setText("");
                    binding.FilterChooseLocation.setText("");
                    binding.FilterChooseRegion.setText("");
                    binding.FilterChooseProvince.setText("");
                    binding.FilterSignalType.setText("");
                    binding.FilterFrom.setText("");
                    binding.FilterTo.setText("");
                    binding.FilterSerialNumber.setText("");
                }
            });
        }

        //بيانات وهميه لعناصر اليست
        {
            ArrayList<String> Locations = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Locations.add("الشارع" + i);
            }
            binding.FilterChooseLocation.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Locations));

            ArrayList<String> Regions = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Regions.add("المنطقة" + i);
            }
            binding.FilterChooseRegion.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Regions));

            ArrayList<String> Provinces = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Provinces.add("غزه" + i);
            }
            binding.FilterChooseProvince.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Provinces));

            ArrayList<String> Converters = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Converters.add("المحول" + i);
            }
            binding.FilterChooseConverter.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Converters));

            ArrayList<String> SignalTypes = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                SignalTypes.add("بيتا" + i);
            }
            binding.FilterSignalType.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, SignalTypes));

        }

        //التوايخ
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                CalendarFrom.set(Calendar.YEAR, year);
                CalendarFrom.set(Calendar.MONTH, month);
                CalendarFrom.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        binding.FilterFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, CalendarFrom.get(Calendar.YEAR), CalendarFrom.get(Calendar.MONTH), CalendarFrom.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                CalendarTO.set(Calendar.YEAR, year);
                CalendarTO.set(Calendar.MONTH, month);
                CalendarTO.set(Calendar.DAY_OF_MONTH, day);
                updateLabel2();
            }
        };
        binding.FilterTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date2, CalendarTO.get(Calendar.YEAR), CalendarTO.get(Calendar.MONTH), CalendarTO.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        binding.FilterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.FilterCitizenName.getText().toString().isEmpty() ||
                        !binding.FilterEntryName.getText().toString().isEmpty() ||
                        !binding.FilterChooseConverter.getText().toString().isEmpty() ||
                        !binding.FilterSubscriptionNumber.getText().toString().isEmpty() ||
                        !binding.FilterChooseLocation.getText().toString().isEmpty() ||
                        !binding.FilterChooseRegion.getText().toString().isEmpty() ||
                        !binding.FilterChooseProvince.getText().toString().isEmpty() ||
                        !binding.FilterSignalType.getText().toString().isEmpty() ||
                        !binding.FilterFrom.getText().toString().isEmpty() ||
                        !binding.FilterSignalNumber.getText().toString().isEmpty() ||
                        !binding.FilterTo.getText().toString().isEmpty()) {


                    String CitizenName = binding.FilterCitizenName.getText().toString();
                    String EntryName = binding.FilterEntryName.getText().toString();
                    String Converter = binding.FilterChooseConverter.getText().toString();
                    String Location = binding.FilterChooseLocation.getText().toString();
                    String Region = binding.FilterChooseRegion.getText().toString();
                    String Province = binding.FilterChooseProvince.getText().toString();
                    String SignalType = binding.FilterSignalType.getText().toString();
                    String FilterFrom = binding.FilterFrom.getText().toString();
                    String FilterTo = binding.FilterTo.getText().toString();
                    String SignalNumber = binding.FilterSignalNumber.getText().toString();
                    String SubscriptionNumber = binding.FilterSubscriptionNumber.getText().toString();


                    int_dates = new Int_Dates() {
                        @Override
                        public void getData(List<Integer> integerList) {
                            myViewModel.findOrdersDatesFilter(CalendarFrom.getTime(), CalendarTO.getTime(), integerList).observe(lifecycleOwner, new Observer<List<Orders>>() {
                                @Override
                                public void onChanged(List<Orders> orders) {
                                    getOrderAdapterListener.GetOrders(orders);
                                    Toast.makeText(getActivity(), "getData", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    };

                    if (!SignalNumber.isEmpty() && !SubscriptionNumber.isEmpty()) {
                        myViewModel.findOrdersSubscriptionANDSignalNumberFilter(Integer.parseInt(SubscriptionNumber), Integer.parseInt(SignalNumber)).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                getOrderAdapterListener.GetOrders(orders);
                            }
                        });

                    } else if (SignalNumber.isEmpty() && !SubscriptionNumber.isEmpty()) {
                        myViewModel.findOrdersWithSubscriptionNumber(Integer.parseInt(SubscriptionNumber)).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                getOrderAdapterListener.GetOrders(orders);
                            }
                        });
                    } else if (!SignalNumber.isEmpty() && SubscriptionNumber.isEmpty()) {
                        myViewModel.findOrdersWithSignalNumber(Integer.parseInt(SignalNumber)).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                getOrderAdapterListener.GetOrders(orders);
                            }
                        });
                    } else if (!FilterFrom.isEmpty() && !FilterTo.isEmpty()) {
                        myViewModel.OrdersDatesFilter(CalendarFrom.getTime(), CalendarTO.getTime()).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                getOrderAdapterListener.GetOrders(orders);
                            }
                        });
                    } else {
                        myViewModel.findOrdersStringsFilter(CitizenName, EntryName, Region, Location, SignalType, Converter, Province).observe(lifecycleOwner, new Observer<List<Orders>>() {
                            @Override
                            public void onChanged(List<Orders> orders) {
                                getOrderAdapterListener.GetOrders(orders);
                                List<Integer> signalnums = new ArrayList<>();
                                for (int i = 0; i < orders.size(); i++) {
                                    signalnums.add(orders.get(i).getSignalNumber());
                                }
                                int_dates.getData(signalnums);
                                if (signalnums != null && !SignalNumber.isEmpty() && !SubscriptionNumber.isEmpty()) {

                                    myViewModel.findOrdersIntFilter(Integer.parseInt(SubscriptionNumber), Integer.parseInt(SignalNumber), signalnums).observe(lifecycleOwner, new Observer<List<Orders>>() {
                                        @Override
                                        public void onChanged(List<Orders> orders) {
                                            List<Integer> signalnums1 = new ArrayList<>();
                                            for (int i = 0; i < orders.size(); i++) {
                                                signalnums1.add(orders.get(i).getSignalNumber());
                                            }
                                            getOrderAdapterListener.GetOrders(orders);
                                        }
                                    });
                                } else {
                                    getOrderAdapterListener.GetOrders(orders);
                                }

                            }
                        });
                    }

                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "you must fill one input at least", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //تحديث حقل التاربخ
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.FilterFrom.setText(dateFormat.format(CalendarFrom.getTime()));
    }

    private void updateLabel2() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.FilterTo.setText(dateFormat.format(CalendarTO.getTime()));
    }

}
