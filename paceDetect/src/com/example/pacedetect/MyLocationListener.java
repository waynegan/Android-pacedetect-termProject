package com.example.pacedetect;



import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyLocationListener implements LocationListener  {

	public void onLocationChanged(Location location) {
		MainActivity.updateGui(location);
	}
	
	public void onProviderDisabled(String provider) {
//        debug("locationListener onProviderDisabled: " + provider);
	}

	public void onProviderEnabled(String provider) {
//        debug("locationListener onProviderEnabled: " + provider);
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
//        debug("locationListener onStatusChanged: " + provider + "status: " + status);
	}

}


