package com.example.silviobravocado.acelerometro;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {


    private SensorManager   manejador;
    private Sensor          sensorAcelerometro;
    private TextView        ejeX, ejeY, ejeZ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejeX = (TextView) this.findViewById(R.id.textViewX);
        ejeY = (TextView) this.findViewById(R.id.textViewY);
        ejeZ = (TextView) this.findViewById(R.id.textViewZ);


        this.sensorInitialize();
    }

    private void sensorInitialize(){
        this.manejador      = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        sensorAcelerometro  = manejador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.manejador.registerListener(this, this.sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            this.showSensorValues(event);
        }

    }


    private void showSensorValues(SensorEvent event){

        float[] values  = event.values;
        float  x        = values[0];
        float  y        = values[1];
        float  z        = values[2];

        this.ejeX.setText(x+" x");
        this.ejeY.setText(y+" y");
        this.ejeZ.setText(z+" z");


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public MainActivity() {
        super();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.manejador.registerListener(this, this.sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.manejador.unregisterListener(this);
    }
}
