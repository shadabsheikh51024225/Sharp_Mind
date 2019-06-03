package com.example.mindshrper;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LevelActivity extends Activity {

	TextView tv,tv_mode;
	ProgressBar pgb;
	int progressValue = 0;
	Handler handle = new Handler();
	String mode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		tv=(TextView)findViewById(R.id.tv);
		tv_mode=(TextView)findViewById(R.id.txt_level);
		pgb = (ProgressBar) findViewById(R.id.progressBar1);
		Intent iin= getIntent();
	    Bundle b = iin.getExtras();
	    String str =(String) b.get("md").toString();
	        tv_mode.setText(str);
	        this.setTitle(str+"  Mode");
	        str= tv_mode.getText().toString();
	       mode=str;
       	        	new Thread(new Runnable() {
	    			@Override
	    			public void run() {
	    				// TODO Auto-generated method stub
	    				for (int i = 0; i < 50; i++) {

	    					progressValue+=5;
	    					handle.post(new Runnable() {
	    						
	    						@Override
	    						public void run() {
	    							// TODO Auto-generated method stub
	    							pgb.setProgress(progressValue);
	    							//tv.setText("Loading ...."+progressValue+"%");
	    							
	    							if(progressValue==100)
	    							{
	    								pgb.setVisibility(2);
	    								//if(tv_mode.getText().equals("Easy"))
	    							
	    							Intent in=new Intent(getApplicationContext(),EasyActivity.class);
	    							in.putExtra("mode",mode);
	    							startActivity(in);
	    							}
	    						}
	    					});
	    					
	    					try{
	    						
	    						Thread.sleep(50);
	    						}
	    				catch(InterruptedException e)
	    				{}
	   				}
	   			}	    			    			
	  		}).start();
	 } 
				
	      
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent in=new Intent(getApplicationContext(),MainMenuActivity.class);
		startActivity(in);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level, menu);
		return true;
	}

}
