package com.example.pacedetect;
/*
 *  author : Wei Gan
 * */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Switch gpsswitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsswitch = (Switch) findViewById(R.id.gpsswitch);
        
        //set the switch to ON 
        gpsswitch.setChecked(true);
        //attach a listener to check for changes in state
   
        gpsswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	@Override    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
        		
        		 if(gpsswitch.isChecked()){
        	        	Toast.makeText(getApplicationContext(), "GPS is currently on", Toast.LENGTH_SHORT).show();
        	        }
        	        else {
        	        	Toast.makeText(getApplicationContext(), "GPS is currently off", Toast.LENGTH_SHORT).show();
        	        }
        		
            }
        });
         
        //check the current state before we display the screen
       
        
        
    }
 
    
}
