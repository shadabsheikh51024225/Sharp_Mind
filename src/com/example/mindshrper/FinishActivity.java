package com.example.mindshrper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FinishActivity extends Activity {
int Score=0;
String name=null;
EditText nm;
SQLiteDatabase mydb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		Intent in=getIntent();
		Bundle bn=in.getExtras();
		Score =bn.getInt("Score");
		TextView score = (TextView)findViewById(R.id.textView2);
		nm =(EditText)findViewById(R.id.e1);
		nm.setTextColor(Color.WHITE);
		this.setTitle("Your Score");
		score.setText("Score = "+Score);
	
			}

	public void store(View v)
	{
		try{
		name = nm.getText().toString();
		mydb=openOrCreateDatabase("MindSharper.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
		String create_table="CREATE TABLE if not exists Score (name varchar not null , score integer);  "; 
		mydb.execSQL(create_table);
		mydb.execSQL("insert into Score values('"+name+"',"+Score+"); ");	
		
		Intent in=new Intent(getApplicationContext(),Score.class);
		startActivity(in);
		finish();
		}
		catch(Exception e)
		{
			Toast.makeText(this, "in store"+e.toString(),Toast.LENGTH_LONG).show();	
		}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent in=new Intent(getApplicationContext(),MainMenuActivity.class);
		startActivity(in);
		finish();
			
	}
}
