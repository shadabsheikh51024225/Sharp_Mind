package com.example.mindshrper;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainMenuActivity extends Activity {
Button play,inst,exit,vol,score;
int i=0;
Boolean volum;

SharedPreferences sharepref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		this.setTitle("Mind Sharper");
		play=(Button)findViewById(R.id.play);
		//btn1=(Button)findViewById(R.id.button1);
		inst=(Button)findViewById(R.id.inst);
		exit=(Button)findViewById(R.id.exit);
		score=(Button)findViewById(R.id.score);
		vol=(Button)findViewById(R.id.vol);
		vol.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			i++;
			if(i%2!=0)
			{
				stopAudio(v);	
				volum=false;
				vol.setBackgroundResource(R.drawable.mute);
			}	
			else if(i%2==0)
			{
				playAudio(v);
				volum=true;
				vol.setBackgroundResource(R.drawable.volum);
			}
			}
		});
	}

	public void play(View v)
	{
	Intent i = new  Intent(getApplicationContext(),ModeActivity.class );
	startActivity(i);
	}
	public void inst(View v)
	{
	Intent in = new  Intent(getApplicationContext(),InstructionActivity.class );
	startActivity(in);
	}
	
	public void score(View v)
	{
	Intent in = new  Intent(getApplicationContext(),Score.class );
	startActivity(in);
	}
	public void exit(View v)
	{
		stopAudio(v);
		Intent intent = new Intent(getApplicationContext(),LaunchActivity.class);
	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    intent.putExtra("Exit me", true);
	    startActivity(intent);
	    finish();	
		
	}
	public void playAudio(View view) {

		Intent objIntent = new Intent(this, PlayAudio.class);
	    startService(objIntent);
    }
	
	 public void stopAudio(View view) {
		 
	    	Intent objIntent = new Intent(this, PlayAudio.class);
		    stopService(objIntent);    
	    
	 }
	 public void exit() {
		 //stopAudio(v);
		 Intent intent = new Intent(getApplicationContext(),LaunchActivity.class);
		    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    intent.putExtra("Exit me", true);
		    startActivity(intent);
		    finish(); 	 
		    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); 
		    Editor editor = pref.edit();
		    editor.putBoolean("volum", volum);
		    editor.commit();
	 }
	 
	 	@Override
	 	public void onBackPressed() {
			// TODO Auto-generated method stub
		 
			exit();
	
	 }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
