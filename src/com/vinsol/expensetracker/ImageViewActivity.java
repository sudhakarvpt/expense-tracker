package com.vinsol.expensetracker;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageViewActivity extends Activity {
	private Long _id;
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//////   ********  Making Layout Visible   *******  /////////
		setContentView(R.layout.image_view_full_screen);
		
		Bundle getBundleExtras= getIntent().getBundleExtra("intentImageViewActivity");
		mImageView = (ImageView) findViewById(R.id.image_view_full_screen_id);
		
		getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		_id = getBundleExtras.getLong("_id");
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			try{
				System.gc();
				Drawable drawable = Drawable.createFromPath("/mnt/sdcard/ExpenseTracker/"+_id+".jpg");
				mImageView.setImageDrawable(drawable);
			} catch (Exception e){
			}
		} 
		else {
			Toast.makeText(this, "sdcard not available", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	

	///// ******************  Handling back press of key   ********** ///////////
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	     if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	 onBackPressed();
	         return true;
	      }
	    return super.onKeyDown(keyCode, event);
	}

	public void onBackPressed() {
	    // This will be called either automatically for you on 2.0    
	    // or later, or by the code above on earlier versions of the platform.
		finish();
	    return;
	}
}
