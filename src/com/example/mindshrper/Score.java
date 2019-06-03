package com.example.mindshrper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends Activity {
SQLiteDatabase mydb=null;
String TableName="Score";
String tag="TAG";
TextView tv;
int exit=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		tv=(TextView)findViewById(R.id.textView2);
		tv.setTextColor(Color.WHITE);
		this.setTitle("High Score");
		try{
		mydb=openOrCreateDatabase("MindSharper.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
		String create_table="CREATE TABLE if not exists Score (name varchar not null , score integer);  "; 
		mydb.execSQL(create_table);
		Cursor c= mydb.rawQuery("select * from Score order by score desc", null);
		String Data[]= new String[c.getCount()+1];
		if(c!=null)
		{
			Data[0] = "Name  \t\t   Score";
			c.moveToFirst();
			int i=1;
			do{
				String name = c.getString(0);
				int score= c.getInt(1);
		
				Data[i]=i+")\t"+name+"\t\t"+score+"\n"; 
				tv.setText(tv.getText().toString() + Data[i]);
				i++;
				
			}while(c.moveToNext());
		}
	
	}
	catch(Exception e)
	{
		Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
	}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
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
