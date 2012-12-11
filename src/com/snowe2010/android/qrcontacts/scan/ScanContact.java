package com.snowe2010.android.qrcontacts.scan;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.snowe2010.android.qrcontacts.R;

public class ScanContact extends Activity {

	private Camera camera;
	private CameraFrame frame;
	private Handler autoFocusHandler;
	
	TextView scannedText;
	Button scanButton;
	ImageScanner scanner;
	
	private boolean barcodeScanned = false;
	private boolean previewing = true;
	
	static {
		System.loadLibrary("iconv");
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.scan);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
//		autoFocusHandler = new Handler();
		camera = getCameraInstance();
//		Toast.makeText(getApplicationContext(), Camera.getNumberOfCameras(), Toast.LENGTH_LONG).show();
		Log.d("cameras", new Integer(Camera.getNumberOfCameras()).toString());
		
		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);
		
		frame = new CameraFrame(this, camera, previewCb, autoFocusCB);
		FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
		preview.addView(frame);
		
		
        scannedText = (TextView)findViewById(R.id.scanText);

        scanButton = (Button)findViewById(R.id.ScanButton);

        scanButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (barcodeScanned) {
                        barcodeScanned = false;
                        scannedText.setText("Scanning...");
                        camera.setPreviewCallback(previewCb);
                        camera.startPreview();
                        previewing = true;
                        camera.autoFocus(autoFocusCB);
                    }
                }
            });
	}
	

    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        if (cameraCount > 1) {
        	for (int i = 0; i < cameraCount; i++) {
        		Camera.getCameraInfo(i, cameraInfo);
        		if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
        			try {
        				c = Camera.open(i);
        			} catch (RuntimeException e) {
        				Log.e("CameraFail", "Camera failed to open: " + e.getLocalizedMessage());
        			}
        		}
        	}
        } else if (cameraCount == 1) {
        	c = Camera.open(1);
        } else {
        	Log.d("DBG", "Somehow you don't have a camera");
        }
        	
//        try {
//            c = Camera.open();
//        } catch (Exception e){
//        	System.out.println("camera failed to open");
//        }
        return c;
    }

    
    
    private void releaseCamera() {
        if (camera != null) {
            previewing = false;
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
            public void run() {
                if (previewing)
                    camera.autoFocus(autoFocusCB);
            }
        };

    PreviewCallback previewCb = new PreviewCallback() {
            public void onPreviewFrame(byte[] data, Camera camera) {
                Camera.Parameters parameters = camera.getParameters();
                Size size = parameters.getPreviewSize();

                Image barcode = new Image(size.width, size.height, "Y800");
                barcode.setData(data);

                int result = scanner.scanImage(barcode);
                
                if (result != 0) {
                    previewing = false;
                    camera.setPreviewCallback(null);
                    camera.stopPreview();
                    
                    SymbolSet syms = scanner.getResults();
                    for (Symbol sym : syms) {
                        scannedText.setText("barcode result " + sym.getData());
                        barcodeScanned = true;
                    }
                }
            }
        };

    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
            public void onAutoFocus(boolean success, Camera camera) {
                autoFocusHandler.postDelayed(doAutoFocus, 1000);
            }
        };
	
        

}
