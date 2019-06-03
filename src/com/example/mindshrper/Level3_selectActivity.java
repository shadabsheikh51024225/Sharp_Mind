package com.example.mindshrper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Level3_selectActivity extends Activity implements OnClickListener{

	View v;
	int q=0;
	SQLiteDatabase mydb;
	String[] get;
	String mode="Easy";
	TextView t,tv_life;
	int cnt=0,life=3,Score=0;
	Button buttonarray[];
	 ArrayList<Integer> listIdontWantAnymore = new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level3_select);
		adddata();
	}
	public void adddata()
	{
		try{
			Intent i= getIntent();
			t= (TextView)findViewById(R.id.t);
			tv_life= (TextView)findViewById(R.id.textView3);
			tv_life.setText("Life = 3");
			Button buttonarray[] = new Button[15];
			buttonarray[1]= (Button)findViewById(R.id.button1);
			buttonarray[2]= (Button)findViewById(R.id.button2);
			buttonarray[3]= (Button)findViewById(R.id.button3);
			buttonarray[4]= (Button)findViewById(R.id.button4);
			buttonarray[5]= (Button)findViewById(R.id.button5);
			buttonarray[6]= (Button)findViewById(R.id.button6);
			buttonarray[7]= (Button)findViewById(R.id.button7);
			buttonarray[8]= (Button)findViewById(R.id.button8);
			buttonarray[9]= (Button)findViewById(R.id.button9);
			buttonarray[10]= (Button)findViewById(R.id.button10);
			buttonarray[11]= (Button)findViewById(R.id.button11);
			buttonarray[12]= (Button)findViewById(R.id.button12);
			buttonarray[13]= (Button)findViewById(R.id.button13);
			buttonarray[14]= (Button)findViewById(R.id.button14);
			 
			Bundle b = i.getExtras();
			 get=b.getStringArray("DisWord");
			 Score=b.getInt("Score");
			 mode=b.getString("mode");
			 this.setTitle(mode + "  Level 3");
			// mode(mode);
			 String k[] = word();
			int cn=1;
			 while(cn <=14)
			 {
				 buttonarray[cn].setText(k[cn]);
				 buttonarray[cn].setTextSize(25);
				 buttonarray[cn].setOnClickListener( this);	 
				 cn++;
			 }
			 int rand;
			 Random random = new Random();
			 for(int r=1;r<=8;r++)
			 {
				 do {
						rand = random.nextInt(14);
						} while(listIdontWantAnymore.contains(rand) || rand==0);
						    listIdontWantAnymore.add(rand);
						    t.setText(t.getText()+"  "+get[r]+rand +"  ");
						    buttonarray[rand].setText(get[r]);
			 }
			 
		new CountDownTimer(60000, 1000) {

			     public void onTick(long millisUntilFinished) {
			         t.setText("Time Remaining: " + millisUntilFinished / 1000);
			     }

			     public void onFinish() {
			    	// Toast.makeText(this, "Time OVER", 200).show();
			    	 t.setText("Time up ");
						//Intent in=new Intent(getApplicationContext(),MainMenuActivity.class);
						//startActivity(in);
			     }
			  }.start();

		}
		catch(Exception e)
		{
			Toast.makeText(this, "adddata"+e.toString(),Toast.LENGTH_LONG).show();
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
	@Override
	public void onClick(View v) {
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	 	int flag=0;
		try{
			
		for(int i=1;i<get.length;i++)
		{			
			if(buttonText.toString().equals(get[i]))
			{
				flag++;
				cnt++;
				Score++;
				b.setEnabled(false);					
			}
		}

		if(flag ==0)
		{
			life--;
			tv_life.setText("Life = "+life);
			if(life==0)
			{
		
				Toast.makeText(this, "GAME OVER",Toast.LENGTH_LONG).show();
				Intent in=new Intent(getApplicationContext(),FinishActivity.class);
				in.putExtra("Score",Score);
				
				startActivity(in);
			}
		}
		else if(cnt == 8)
		{
	
			Toast.makeText(this, "Level Sucess", Toast.LENGTH_LONG).show();
			Intent in=new Intent(getApplicationContext(),Level4Activity.class);
			in.putExtra("Score",Score);
			in.putExtra("mode", mode);
			startActivity(in);
		}
		}catch(Exception e)
		{
			Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
		}
		
	};

	public String[] word()
	{
		TestAdapter mDbHelper = new TestAdapter(getBaseContext());
	    mDbHelper.createDatabase();      
		mDbHelper.open();
	    Cursor c = mDbHelper.getTestData(mode);
	    String Data[]= new String[c.getCount()+1];
	    //get1=get;
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
	String k[] = new String[Data.length];
	k=Data;
	Collections.shuffle(Arrays.asList(k));
	return k;
	  

	}
	public static int random(final int lower, final int uppper)
	{
	    return lower + (int)(Math.random() * ((uppper - lower) + 1));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level3_select, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
				Intent in=new Intent(getApplicationContext(),FinishActivity.class);
				in.putExtra("Score",Score);
				startActivity(in);
				
			
		}

}
