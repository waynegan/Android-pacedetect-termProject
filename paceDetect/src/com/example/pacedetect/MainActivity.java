package com.example.pacedetect;
/*
 *  author : Wei Gan
 * */


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Switch gpsswitch;
	//private TextView tvAccuracy;
	//private TextView tvAltitude;
	private TextView tvSpeed;
	//private TextView tvGpsLat;
	//private TextView tvGpsLon;
	//private ViewGroup mainLayout;
	private MyLocationListener mLocationListener;
	private LocationManager mLocationManager;
	private static int currentSpeed;
	//private WakeLock wl;
	//private ViewGroup linLayout;
	//private MyDrawView myDrawView;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsswitch = (Switch) findViewById(R.id.gpsswitch);
        tvSpeed = (TextView)findViewById(R.id.tvSpeed);
        //set the switch to ON 
        gpsswitch.setChecked(true);
        //attach a listener to check for changes in state
   
        gpsswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	@Override    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
        		
        		 if(gpsswitch.isChecked()){
        	        	Toast.makeText(getApplicationContext(), "GPS is currently on", Toast.LENGTH_SHORT).show();
        	        	LocationManager locationManager = (LocationManager)
        	        			getSystemService(Context.LOCATION_SERVICE);
        	        	LocationListener locationListener = new MyLocationListener();
        	        	locationManager.requestLocationUpdates(
        	        	LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        		 }
        	        else {
        	        	Toast.makeText(getApplicationContext(), "GPS is currently off", Toast.LENGTH_SHORT).show();
        	        }
        		
            }
        });
         
        //check the current state before we display the screen
       
        
        
    }
    public static void updateGui(Location location) {

    	if (location != null) {
    		//tvAccuracy.setText("Accuracy: " + location.getAccuracy());
		//	tvAltitude.setText("Altitude: " + location.getAltitude() + " m");
			//tvGpsLat.setText(String.format("%3.8f", location.getLatitude()));
			//tvGpsLon.setText(String.format("%3.8f", location.getLongitude()));
		//	tvSpeed.setText(formatSpeed(location.getSpeed()));
			currentSpeed = Math.round(location.getSpeed() * (float)3.6);
			// the above is conversion from meters per sec to km per hour
    	}
    		
			//mainLayout.setBackgroundColor(getResources().getColor(R.color.speeding_0));
		//	tvSpeed.setTextColor(Color.YELLOW);

	}
 
    
}
