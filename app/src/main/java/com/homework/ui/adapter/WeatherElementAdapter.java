package com.homework.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homework.R;
import com.homework.entity.WeatherTime;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherElementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherTime> weatherTimeList;
    private final OnWeatherElementClickListener listener;

    public WeatherElementAdapter(List<WeatherTime> weatherTimeList, OnWeatherElementClickListener listener) {
        this.weatherTimeList = weatherTimeList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        //0是typeA，1是typeB
        return position % 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isTypeA(viewType)) {
            View item_list_text = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_text, parent, false);
            return new TextViewHolder(item_list_text);
        } else {
            View item_list_image = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_image, parent, false);
            return new ImageViewHolder(item_list_image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (isTypeA(holder.getItemViewType())) {
            //因為資料總數*2，故取資料需/2。
            final WeatherTime weatherTime = weatherTimeList.get(position / 2);
            TextViewHolder viewHolder = (TextViewHolder) holder;
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, weatherTime);
                }
            });
            String parameter = weatherTime.getParameter().getParameterName() + weatherTime.getParameter().getParameterUnit();
            viewHolder.startTextView.setText(weatherTime.getStartTime());
            viewHolder.endTimeTextView.setText(weatherTime.getEndTime());
            viewHolder.parameterTextView.setText(parameter);
        }
    }

    @Override
    public int getItemCount() {
        //總數*2是為了在各資料之間塞入type B，-1為最後一項不顯示type B。
        return weatherTimeList.size() * 2 - 1;
    }

    private boolean isTypeA(int type) {
        return type == 0;
    }

    //type A ViewHolder
    private static class TextViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView startTextView;
        public TextView endTimeTextView;
        public TextView parameterTextView;

        public TextViewHolder(View view) {
            super(view);
            this.view = view;
            startTextView = view.findViewById(R.id.start_time);
            endTimeTextView = view.findViewById(R.id.end_time);
            parameterTextView = view.findViewById(R.id.parameter);
        }
    }

    //type B ViewHolder
    private static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageViewHolder(View view) {
            super(view);
        }
    }

    //type A click listener
    public interface OnWeatherElementClickListener {
        void onClick(View v, WeatherTime weatherTime);
    }

}
