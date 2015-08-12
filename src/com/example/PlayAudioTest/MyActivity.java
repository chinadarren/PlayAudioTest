package com.example.PlayAudioTest;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button play;
    private Button pause;
    private Button stop;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener( this);
        pause.setOnClickListener(this);
        stop.setOnClickListener( this);
   //初始化MediaPlayer
    initMediaPlayer();
    }

    private void initMediaPlayer() {

        try{
            File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
            //指定音频文件的路径
            mediaPlayer.setDataSource(file.getPath());
            //让MediaPlayer进入准备状态
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
   switch (v.getId()){
       case R.id.play:
           //如果MediaPlayer没有正在播放音频则条用start方法
           if(!mediaPlayer.isPlaying()){
               mediaPlayer.start();//开始播放
           }
           break;
       case R.id.pause:
           if(mediaPlayer.isPlaying()){
               mediaPlayer.pause();//暂停播放
           }
           break;
       case R.id.stop:
           if (mediaPlayer.isPlaying()){
                   mediaPlayer.reset();//停止播放
                   initMediaPlayer();
           }
           break;
       default:
           break;
   }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
