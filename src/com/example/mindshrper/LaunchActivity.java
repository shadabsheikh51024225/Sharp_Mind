package com.example.mindshrper;





import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class LaunchActivity extends Activity {

	View v;
	TextView tv;
	SQLiteDatabase Mydb;
	ProgressBar pgb;
	int progressValue = 0,close=1;
	Handler handle = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("Mind Sharper");
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		if( getIntent().getBooleanExtra("Exit me", false)){
	        stopAudio(v);
			finish();
			
	        return; // add this to prevent from doing unnecessary stuffs
	    }
	Boolean volume = pref.getBoolean("volum", true);
	if(volume==false)		
	{}
	else
	{
		playAudio(v);
	}
	setContentView(R.layout.activity_launch);
		tv = (TextView) findViewById(R.id.tv);
		pgb = (ProgressBar) findViewById(R.id.progressBar1);
	
if(Mydb== null)
	create_database(v);

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 50; i++) {

					progressValue+=2;
					handle.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							pgb.setProgress(progressValue);
							tv.setText("Loading ...."+progressValue+"%");
							if(progressValue==100)
							{
								pgb.setVisibility(2);
							Intent in=new Intent(getApplicationContext(),MainMenuActivity.class);
							if(close==1)
							startActivity(in);
							}
							else
							{}
						}
					});
					
					try{
						
						Thread.sleep(100);
						}
				catch(InterruptedException e)
				{}
				}
			}
			}).start();

		

		
	}
	
	public void create_database(View v)
	{
		
		try {
			TestAdapter mDbHelper = new TestAdapter(getBaseContext());        
			mDbHelper.createDatabase();      
			mDbHelper.open();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void playAudio(View view) {
	    Intent objIntent = new Intent(this, PlayAudio.class);
	    startService(objIntent);
    }
	 public void stopAudio(View view) {
	    	Intent objIntent = new Intent(this, PlayAudio.class);
		    stopService(objIntent);    
	    
	 }
	 public void onBackPressed() {
			// TODO Auto-generated method stub
		 Intent intent = new Intent(getApplicationContext(),LaunchActivity.class);
		    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    close=0;
		    intent.putExtra("Exit me", true);
		    startActivity(intent);
		    finish(); 
	 }
}
