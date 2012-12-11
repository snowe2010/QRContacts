package com.snowe2010.android.qrcontacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.snowe2010.android.qrcontacts.generate.ChooseContact;
import com.snowe2010.android.qrcontacts.scan.ScanContact;

public class MainActivity extends Activity  {
	
	Contact c = new Contact();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		setHasOptionsMenu(true);
		
		Button scan = (Button) findViewById(R.id.scanqrbutton);
		Button generate = (Button) findViewById(R.id.generateqrbutton);
		generate.setOnClickListener(onGenerate);
		scan.setOnClickListener(onScan);
	}
	
	private View.OnClickListener onGenerate = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
//			Button generate = (Button) findViewById(R.id.generateqrbutton);
			Intent i = new Intent(MainActivity.this, ChooseContact.class);
			startActivity(i);
		}
	};
	
	private View.OnClickListener onScan = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (checkCameraHardware(getApplicationContext())) {
				Intent i = new Intent(MainActivity.this, ScanContact.class);
	//			startActivityForResult(i, requestCode);
				startActivity(i);
			}
		}
	};
	
	/** Check if this device has a camera */
	private boolean checkCameraHardware(Context context) {
		int numCameras = Camera.getNumberOfCameras();
		if (numCameras > 0) {
		  return true;
	    } else {
	        // no camera on this device
	    	Toast.makeText(getApplicationContext(), R.string.no_camera_toast, Toast.LENGTH_LONG).show();
	        return false;
	    }
	}
	
}
