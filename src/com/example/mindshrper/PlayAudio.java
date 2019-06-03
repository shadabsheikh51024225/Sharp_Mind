package com.example.mindshrper;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
public class PlayAudio extends Service {
	private static final String LOGCAT = null;
	MediaPlayer objPlayer;
	public void onCreate(){
	    super.onCreate();
	    Log.d(LOGCAT, "Service Started!");
	    objPlayer = MediaPlayer.create(this,R.raw.audio);
	    objPlayer.setLooping(true);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		objPlayer.start();
	    Log.d(LOGCAT, "Media Player started!");
	    if(objPlayer.isLooping() != true){
	        Log.d(LOGCAT, "Problem in Playing Audio");
	    }
	    return 1;
	}
		
	public void onPause(){
		objPlayer.pause();
		objPlayer.release();
	}
	
	public void onDestroy(){
		objPlayer.stop();
		objPlayer.release();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
