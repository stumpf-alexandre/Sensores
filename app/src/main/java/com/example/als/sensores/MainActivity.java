package com.example.als.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor grav, umid, press;

    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.gravidade);
        txt2 = findViewById(R.id.umidade);
        txt3 = findViewById(R.id.pressao);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        grav = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (grav != null){
            sensorManager.registerListener(MainActivity.this, grav, sensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gravity listener");
        }
        else {
            txt1.setText("Gravity Not Supported");
        }

        umid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (umid != null){
            sensorManager.registerListener(MainActivity.this, umid, sensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered  Humi listener");
        }
        else {
            txt2.setText("Humi Not Supported");
        }

        press = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (press != null){
            sensorManager.registerListener(MainActivity.this, press, sensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Pressured listener");
        }
        else {
            txt3.setText("Pressure Not Supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_GRAVITY){
            txt1.setText("Gravidade: " + sensorEvent.values[0] + " m/s²");
        }
        else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            txt2.setText("Umidade Relativa: " + sensorEvent.values[0] + " %");
        }
        else if (sensor.getType() == Sensor.TYPE_PRESSURE){
            txt3.setText("Pressão Atmosferica: " + sensorEvent.values[0] + " hPa");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
