package com.example.mindshrper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ModeActivity extends Activity {

	Button easy,medium,hard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode);
		easy=(Button)findViewById(R.id.easy);
		medium=(Button)findViewById(R.id.medium);
		hard=(Button)findViewById(R.id.hard);
		this.setTitle("Select Mode");
		easy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i = new  Intent(getApplicationContext(),LevelActivity.class );
			String mode= "Easy";
			i.putExtra("md",mode);
			startActivity(i);
			}
			
			});
		medium.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i = new  Intent(getApplicationContext(),LevelActivity.class );
			String mode= "Medium";
			i.putExtra("md",mode);
			startActivity(i);
			}
			
			});
		hard.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i = new  Intent(getApplicationContext(),LevelActivity.class );
			String mode= "Hard";
			i.putExtra("md",mode);
			startActivity(i);
			}
			
			});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mode, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	
		Intent in=new Intent(getApplicationContext(),MainMenuActivity.class);
		startActivity(in);
	}
}
