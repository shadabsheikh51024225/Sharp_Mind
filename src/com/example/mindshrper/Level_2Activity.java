package com.example.mindshrper;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Level_2Activity extends Activity {
	SQLiteDatabase mydb;
	String dis_word [] = new String[30];
	String mode;
	int Score=0,ht=0;
	
	
	LinearLayout myLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_2);
		mode="Easy";
		Intent in=getIntent();
		Bundle bn = in.getExtras(); 
		Score=bn.getInt("Score");
		mode=bn.getString("mode");
		ht=bn.getInt("ht");
		this.setTitle(mode + " Level 2");
		//mode(mode);
		show();
	}
	public void show()
	{
		myLayout = (LinearLayout) findViewById(R.id.ll);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		TextView [] pairs=new TextView[6];
	    for(int l=1; l<6; l++)
		{
		    pairs[l] = new TextView(this);
		    pairs[l].setTextSize(30);
	        pairs[l].setLayoutParams(lp);
		    pairs[l].setId(l);
		    pairs[l].setTextColor(Color.WHITE);
		    myLayout.addView(pairs[l]);
		    
		    
		}
	    try{
	   // mydb = openOrCreateDatabase("MindSharper.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
		//Cursor c= mydb.rawQuery("select easy from WordMaster", null);
	    	TestAdapter mDbHelper = new TestAdapter(getBaseContext());
	        mDbHelper.createDatabase();      
			mDbHelper.open();
	        Cursor c = mDbHelper.getTestData(mode);
	    	String Data[]= new String[c.getCount()+1];
		
		if(c!=null)
		{
			c.moveToFirst();
			int i=1;
			do{
				String easy = c.getString(0);
				Data[i]=easy; 
				i++;
				
			}while(c.moveToNext());
		}
		//int flag = 0;
		String randString;
		for(int i=1;i<=5;i++)
		{
		Random random = new Random(); // or create a static random field...
		 randString = Data[random.nextInt(Data.length)];
		for(int j=1;j<dis_word.length;j++)
		{
			if(randString=="")
			 randString = Data[random.nextInt(Data.length)];
			
			if(randString==dis_word[j])
			{
				randString = Data[random.nextInt(Data.length)];
			}	
		}
		 
		dis_word[i]=randString;
		pairs[i].setText(randString);
		
		}
	}catch(Exception e)
	{
		Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
	}
	 } 
	public void mode(String mode)
		{
			View m = (RelativeLayout) findViewById(R.id.rl);
			if(mode.equals("Easy"))
				m.setBackgroundResource(R.drawable.z1);
			else if(mode.equals("Medium"))
				m.setBackgroundResource(R.drawable.z4);
			else if(mode.equals("Hard"))
				m.setBackgroundResource(R.drawable.black);
		}
	
	public static int Random(final int lower, final int uppper)
	{
	    return lower + (int)(Math.random() * ((uppper - lower) + 1));
	}
	
	public void next(View v)
	{
		try{
		Intent i=new Intent(getApplicationContext(),Level2_SelectActivity.class);
		Bundle b=new Bundle();
		b.putStringArray("DisWord", dis_word);
		b.putString("mode", mode);
		b.putInt("Score", Score);
		b.putInt("ht", ht);
		i.putExtras(b);
		startActivity(i);
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_2, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent in=new Intent(getApplicationContext(),FinishActivity.class);
		in.putExtra("Score", Score);
		startActivity(in);
		finish();
			
	}
}
