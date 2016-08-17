package com.pengyu.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    private SensorManager manager;
    private SensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        logSensor();
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        /**注册监听*/
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(listener);
    }

    private void logSensor() {
        tv = (TextView) findViewById(R.id.tv);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        String str = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            str += "sensorName()" + sensor.getName() + "   sensorVendor()" + sensor.getVendor() + "\n\t";
        }
        tv.setText(str);
//        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
