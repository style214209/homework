package com.homework.ui;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homework.R;
import com.homework.entity.WeatherTime;
import com.homework.ui.adapter.WeatherElementAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherElementFragment extends Fragment {


    private static final String ARG_WEATHER_TIME_LIST = "ARG_WEATHER_TIME_LIST";
    private List<WeatherTime> weatherTimeList = new ArrayList<>();
    private WeatherElementAdapter weatherElementAdapter;

    public static WeatherElementFragment newInstance(List<WeatherTime> weatherTimeList) {
        WeatherElementFragment fragment = new WeatherElementFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_WEATHER_TIME_LIST, (ArrayList<? extends Parcelable>) weatherTimeList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherTimeList = getArguments().getParcelableArrayList(ARG_WEATHER_TIME_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_element, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.customer_list);
        weatherElementAdapter = new WeatherElementAdapter(weatherTimeList, new WeatherElementAdapter.OnWeatherElementClickListener() {
            @Override
            public void onClick(View v, WeatherTime weatherTime) {
                //點擊 type A　item 跳至 DetailFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, DetailFragment.newInstance(weatherTime))
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerView.setAdapter(weatherElementAdapter);
    }
}
