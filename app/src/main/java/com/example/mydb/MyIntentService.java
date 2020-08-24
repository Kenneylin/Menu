package com.example.mydb;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MyIntentService extends Service {
    private boolean isReady;
    private MediaPlayer mediaPlayer=null;
    public MyIntentService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.music);
        if(mediaPlayer==null){
            return;
        }
        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(){
            @Override
            public boolean onError(MediaPlayer mediaPlayer,int i,int i1){
                mediaPlayer.release();
                stopSelf();
                return false;

            }
        });
        try{
            mediaPlayer.prepare();
            isReady=true;
        }catch(IOException e){
            e.printStackTrace();
            isReady=false;
        }
    }

    //开启服务
    @Override
    public int onStartCommand(Intent intent,   int flags, int startId) {
        if(isReady&&!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            Toast.makeText(this,"开始播放音乐",Toast.LENGTH_SHORT).show();
        }

        return  START_STICKY;
    }
    //关闭服务
    @Override
    public void onDestroy() {
        if(mediaPlayer!=null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            Toast.makeText(this,"停止播放音乐",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
