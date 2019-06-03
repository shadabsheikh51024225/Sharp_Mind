package com.example.mindshrper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import android.R.color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public  class Easy_SelectActivity extends Activity implements OnClickListener {
	View v;
	int ht=3;
	SQLiteDatabase mydb;
	String[] get, temp;
	TextView t,tv_life,thint;
	String mode = "Easy",t2;
	int Score=0,cn=0,life=3,k=0;
	RelativeLayout myLayout;
	CountDownTimer c;
	ImageButton ib;
	Button buttonarray[];
	ArrayList<Integer> listIdontWantAnymore = new ArrayList<Integer>();
	ArrayList<String> hintlist = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		temp=new String[30];
		setContentView(R.layout.activity_easy__select);
		myLayout=(RelativeLayout)findViewById(R.id.a);
		Intent i= getIntent();
		t= (TextView)findViewById(R.id.t);
		tv_life= (TextView)findViewById(R.id.textView2);
		tv_life.setText("Life = 3");
		buttonarray = new Button[10];
		buttonarray[1]= (Button)findViewById(R.id.button1);
		buttonarray[2]= (Button)findViewById(R.id.button2);
		buttonarray[3]= (Button)findViewById(R.id.button3);
		buttonarray[4]= (Button)findViewById(R.id.button4);
		buttonarray[5]= (Button)findViewById(R.id.button5);
		buttonarray[6]= (Button)findViewById(R.id.button6);
		buttonarray[7]= (Button)findViewById(R.id.button7);
		buttonarray[8]= (Button)findViewById(R.id.button8);
		ib = (ImageButton)findViewById(R.id.hint);
		thint=(TextView)findViewById(R.id.textView1);
		thint.setText("3");
		thint.setTextColor(Color.WHITE);
		//ib.setEnabled(false);
		 Bundle b = i.getExtras();
		try{ 
		 get=b.getStringArray("DisWord");
		 mode=b.getString("mode");
		// mode(mode);
		 this.setTitle(mode + " Level 1"); 
		 String kk[] = word();
		
		int cnt =1;
		while(cnt <=8)
		{
			buttonarray[cnt].setText(kk[cnt]);
			buttonarray[cnt].setTextSize(25);
			buttonarray[cnt].setOnClickListener(this);	 
			cnt++;
		}
		 }
	catch(Exception e)
	{
		Toast.makeText(this,"on crate"+ e.toString(),Toast.LENGTH_LONG).show();
	}
				
		 int rand=0;
		 Random random= new Random();
		 for(int r=1;r<=3;r++)
		 {
			 do {
					
					rand = random.nextInt(8);
					} while(listIdontWantAnymore.contains(rand) || rand==0);
					    listIdontWantAnymore.add(rand);
					    buttonarray[rand].setText(get[r]);
		 }
		 
		 c = new CountDownTimer(60000, 1000) {

		     public void onTick(long millisUntilFinished) {
		       t.setText("Time Remaining: " + millisUntilFinished / 1000);
		     }
		      public void onFinish() {
		         t.setText("Time up ");
				Intent in=new Intent(getApplicationContext(),FinishActivity.class);
				in.putExtra("Score", Score);
				startActivity(in);
		     }
		  }.start();
	}
	
	@Override
	public void onClick(View v) {
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	 	int flag=0;
	 	try{
		for(int i=1;i<=get.length-1;i++)
		{			
			if(buttonText.toString().equals(get[i]))
			{
				
				flag++;
				Score++;
				cn++;
				temp[cn]=buttonText;
				b.setEnabled(false);					
			}
		}
		if(flag ==0)
		{
			life--;
			tv_life.setText("Life = "+life);
			if(life<=0)
			{
				c.cancel();
				Toast.makeText(this, "GAME OVER", Toast.LENGTH_LONG).show();
				Intent in=new Intent(getApplicationContext(),FinishActivity.class);
				in.putExtra("Score", Score);
				startActivity(in);
			}
		}
		else if(cn == 3)
		{
		c.cancel();
		Toast.makeText(this, "Level Sucess",Toast.LENGTH_LONG).show();
		Intent in=new Intent(getApplicationContext(),Level_2Activity.class);
		Bundle bn = new Bundle();
		bn.putString("mode", mode);
		bn.putInt("ht", ht);
		bn.putInt("Score", Score);
		in.putExtras(bn);
		startActivity(in);
		}
		}catch(Exception e)
		{
			Toast.makeText(this,"on click"+ e.toString(),Toast.LENGTH_LONG).show();
		}
	};
	
	
	public void hint(View v)
	{
	int i=1;
	String s = null;
	if(ht!=0)
	{
	try{
			int n;
			/*for(i=1;i<=temp.length;i++)
			{
				if(get[n]!=temp[i])
				{
					s=get[n];
					break;
				}
				else
					
			}*/
			for(i=1;i<=temp.length;i++)
			{
			 do {
				 n= random(1,3);
				 if(get[n]==temp[i])
					{
					 n = random(1,3);
					}
				} while(hintlist.contains(get[n]));
					    hintlist.add(get[n]);
					    s=get[n];
					    break;
			}
	}
	
	catch(Exception e)
	{
		Toast.makeText(this,"on hint for 1"+ e.toString(),Toast.LENGTH_LONG).show();
		
	}
			//Toast.makeText(this,s.toString(),Toast.LENGTH_LONG).show();
	try{	
		String str;
		int cnt =1;
		while(cnt <=8)
		{
			str= buttonarray[cnt].getText().toString();
			if(str.equals(s))
			{
				buttonarray[cnt].setTextColor(Color.RED);
				ht--;
				thint.setText(""+ht);
				thint.setTextColor(Color.WHITE);
				break;
			}
			cnt++;
		}
	}catch(Exception e)
	{
		Toast.makeText(this,"on hint for 2"+ e.toString(),Toast.LENGTH_LONG).show();
		
	}
	}// ht!=0
	else if(ht==0)
	{
		ib.setEnabled(false);
	}
}	
public String[] word()
{
	 TestAdapter mDbHelper = new TestAdapter(getBaseContext());
      mDbHelper.createDatabase();      
		mDbHelper.open();
      Cursor c = mDbHelper.getTestData(mode);
	String k[];
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
	k = new String[Data.length];
	k=Data;
	Collections.shuffle(Arrays.asList(k));
	return k;
}

public void mode(String mode)
{
	//View m = (RelativeLayout)findViewById(R.id.a); 
	if(mode.equals("Easy"))
		myLayout.setBackgroundResource(R.drawable.z1);
	else if(mode.equals("Medium"))
		myLayout.setBackgroundResource(R.drawable.z4);
	else if(mode.equals("Hard"))
		myLayout.setBackgroundResource(R.drawable.black);
}

public static int random(final int lower, final int uppper)
{
	
    return lower + (int)(Math.random() * ((uppper - lower) + 1));
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.easy__select, menu);
		return true;
	}
	  
			
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		//c.cancel();
		Intent in=new Intent(getApplicationContext(),FinishActivity.class);
		in.putExtra("Score",Score);
		startActivity(in);		
		}
}
