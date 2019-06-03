package com.example.mindshrper;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import android.R.integer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Level2_SelectActivity extends Activity implements OnClickListener {
	View v;
	
	int q=0;
	SQLiteDatabase mydb;
	String[] get,temp;
	String[] get1;
	TextView t,tv_life,thint;
	String mode ="Easy";
	RelativeLayout rl;
	int ht=0,cnt=0,life=3,Score=0;
	Button buttonarray[];
	ImageButton ib;
	CountDownTimer c;
	ArrayList<Integer> listIdontWantAnymore = new ArrayList<Integer>();
	ArrayList<String> hintlist = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level2__select);
		adddata();
	}
	
	
public void adddata()
{
	try{
		Intent i= getIntent();
		t= (TextView)findViewById(R.id.t);
		tv_life= (TextView)findViewById(R.id.textView2);
		tv_life.setText("Life = 3");
		Button buttonarray[] = new Button[11];
		buttonarray[8]= (Button)findViewById(R.id.button1);
		buttonarray[1]= (Button)findViewById(R.id.button2);
		buttonarray[2]= (Button)findViewById(R.id.button3);
		buttonarray[3]= (Button)findViewById(R.id.button4);
		buttonarray[4]= (Button)findViewById(R.id.button5);
		buttonarray[5]= (Button)findViewById(R.id.button6);
		buttonarray[6]= (Button)findViewById(R.id.button7);
		buttonarray[7]= (Button)findViewById(R.id.button8);
		buttonarray[9]= (Button)findViewById(R.id.Button9);
		buttonarray[10]= (Button)findViewById(R.id.Button10);
		rl= (RelativeLayout)findViewById(R.id.rl); 
		Bundle b = i.getExtras();
		get=b.getStringArray("DisWord");
		Score =b.getInt("Score");
		mode=b.getString("mode");
		//mode(mode);
		temp=new String[30];
		ht=b.getInt("ht");
		if(ht==0)
		{
			ib.setEnabled(false);
		}
		thint=(TextView)findViewById(R.id.textView1);
		thint.setText(""+ht);
		ib = (ImageButton)findViewById(R.id.hint);
		this.setTitle(mode + " Level 2"); 
		
		String kk[] = word();
		int cn=1;
		 do{
		buttonarray[cn].setText(kk[cn]);
		buttonarray[cn].setTextSize(25);
		buttonarray[cn].setOnClickListener(this);	 
			 cn++;
		}while(cn <=10);
	
			
		 int rand=0;
		 Random random = new Random();
		 for(int r=1;r<=5;r++)
		 {	   
			do {
				rand = random.nextInt(10);
				} while(listIdontWantAnymore.contains(rand) || rand==0);
				    listIdontWantAnymore.add(rand);
				    buttonarray[rand].setText(get[r]);
		}
		 
	 c = new CountDownTimer(60000, 1000) {

		     public void onTick(long millisUntilFinished) {
		      t.setText("Time Remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		    	 //Toast.makeText(this, "Time OVER", 200).show();
		    	 t.setText("Time up ");
					Intent in=new Intent(getApplicationContext(),FinishActivity.class);
					in.putExtra("Score", Score);
					startActivity(in);
		     }
		  }.start();

	}
	catch(Exception e)
	{
		Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
	}
}
public static int random(final int lower, final int uppper)
{
    return lower + (int)(Math.random() * ((uppper - lower) + 1));
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
			Score++;
			cnt++;
			temp[cnt]=buttonText;
			b.setEnabled(false);
			
		}
	}

	if(flag ==0)
	{
		life--;
		tv_life.setText("Life = "+life);
		if(life==0)
		{
			c.cancel();
			Toast.makeText(this, "GAME OVER",Toast.LENGTH_LONG).show();
			Intent in=new Intent(getApplicationContext(),FinishActivity.class);
			in.putExtra("Score", Score);
			startActivity(in);
		}
	}
	else if(cnt == 5)
	{
		c.cancel();
		Toast.makeText(this, "Level Sucess",Toast.LENGTH_LONG).show();
		Intent in=new Intent(getApplicationContext(),Level3Activity.class);
		Bundle bn=new Bundle();
		bn.putString("mode", mode);
		bn.putInt("Score", Score);
		bn.putInt("ht", ht);
		in.putExtras(bn);
		startActivity(in);
	}
	}catch(Exception e)
	{
		Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show();
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
		
		for(i=1;i<=temp.length;i++)
		{
		 do {
			 n= random(1,5);
			 if(get[n]==temp[i])
				{
				 n = random(1,5);
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
	while(cnt <=10)
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
	String Data[]= new String[c.getCount()+1];

get1=get;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level2__select, menu);
		return true;
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	c.cancel();
	super.onBackPressed();
	Intent in=new Intent(getApplicationContext(),FinishActivity.class);
	in.putExtra("Score", Score);
	startActivity(in);
			
		
	}
}
