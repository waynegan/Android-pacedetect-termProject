package com.example.pacedetect;
/*
 *  author : Wei Gan
 * */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Switch gpsswitch;
	//private TextView tvAccuracy;
	//private TextView tvAltitude;
	private static TextView tvSpeed;
	//private TextView tvGpsLat;
	//private TextView tvGpsLon;
	//private ViewGroup mainLayout;



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
        		
        		 if(gpsswitch.isChecked()){turnGPSOn();
        	        	Toast.makeText(getApplicationContext(), "GPS is currently on", Toast.LENGTH_SHORT).show();
        	        	LocationManager locationManager = (LocationManager)
        	        			getSystemService(Context.LOCATION_SERVICE);
        	        	LocationListener locationListener = new MyLocationListener();
        	        	locationManager.requestLocationUpdates(
        	        	LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        	        
        		 }
        	        else {onDestroy();
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
			
			
			tvSpeed.setText(formatSpeed(location.getSpeed()));
			// the above is conversion from meters per sec to km per hour
    	}
    		
			//mainLayout.setBackgroundColor(getResources().getColor(R.color.speeding_0));
		//	tvSpeed.setTextColor(Color.YELLOW);

	}
    private static String formatSpeed(float speed) {
		return String.format("%3.1f", speed * (float)3.6);
	}
    public void turnGPSOn(){
        try
        {

        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);


        if(!provider.contains("gps")){ //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
        }
        catch (Exception e) {

        }
    }
    
    public void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);
        }
    }

    // turning off the GPS if its in on state. to avoid the battery drain.
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        turnGPSOff();
    }
    
    
}
