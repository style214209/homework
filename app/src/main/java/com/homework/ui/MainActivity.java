package com.homework.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.homework.BaseSharedPreference;
import com.homework.R;
import com.homework.entity.WeatherElement;
import com.homework.entity.WeatherEntity;
import com.homework.entity.WeatherTime;
import com.homework.request.Request;
import com.homework.request.RequestException;
import com.homework.request.WeatherRequest;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String elementName = "MinT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIsFirstTime();
        requestWeatherData();
    }

    private void checkIsFirstTime() {
        //取出sharedPreference判斷是否為第一次進入
        if (BaseSharedPreference.getIsFirstTime(this)) {
            //第一次進入，將sharedPreference改為false
            BaseSharedPreference.setIsFirstTime(this, false);
        } else {
            //非第一次進入，顯示Toast
            Toast.makeText(this, R.string.welcome, Toast.LENGTH_LONG).show();
        }
    }

    //請求中央氣象局未來36小時&臺北市資料
    private void requestWeatherData() {
        WeatherRequest weatherRequest = new WeatherRequest(new Request.RequestListener<WeatherEntity>() {
            @Override
            public void onRequestSuccess(WeatherEntity entity) {
                for (WeatherElement weatherElement : entity.getRecords().getLocation().get(0).getWeatherElement()) {
                    //取出MinT欄位的氣溫資料給WeatherElementFragment顯示
                    if (weatherElement.getElementName().equals(elementName)) {
                        List<WeatherTime> weatherTimeList = weatherElement.getTime();
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.frameLayout, WeatherElementFragment.newInstance(weatherTimeList))
                                .commit();
                        break;
                    }
                }
            }

            @Override
            public void onRequestFail(RequestException exception) {
            }
        });

        weatherRequest.request();
    }
}
