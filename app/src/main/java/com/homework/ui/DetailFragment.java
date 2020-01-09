package com.homework.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homework.R;
import com.homework.entity.WeatherTime;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private static final String ARG_WEATHER_TIME = "ARG_WEATHER_TIME";
    private WeatherTime weatherTime;

    public static DetailFragment newInstance(WeatherTime weatherTime) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_WEATHER_TIME, weatherTime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherTime = getArguments().getParcelable(ARG_WEATHER_TIME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView startTimeTextView = view.findViewById(R.id.start_time);
        TextView endTimeTextView = view.findViewById(R.id.end_time);
        TextView parameterTextView = view.findViewById(R.id.parameter);

        String parameter = weatherTime.getParameter().getParameterName() + weatherTime.getParameter().getParameterUnit();

        startTimeTextView.setText(weatherTime.getStartTime());
        endTimeTextView.setText(weatherTime.getEndTime());
        parameterTextView.setText(parameter);
        return view;
    }



}
