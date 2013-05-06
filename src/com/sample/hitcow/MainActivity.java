package com.sample.hitcow;

import java.util.Random;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener{
	//View
	private ImageView cowImg;
	//音声ファイル
	private SoundPool sp;
	private int normalSoundId;
	private int megaSoundId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Viewの初期化
		cowImg = (ImageView)findViewById(R.id.cow_img);
		cowImg.setOnTouchListener(this);
		//音声ファイルの初期化
		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		normalSoundId = sp.load(getApplicationContext(), R.raw.hit, 1);
		megaSoundId = sp.load(getApplicationContext(), R.raw.mega_hit, 1);
	}
	
	//画面が非表示になった時
	@Override
	public void onPause() {
		super.onPause();
		sp.release();
		sp = null;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		//乱数を作成し、1つだけ取得する
		Random rdm = new Random();
		int num = rdm.nextInt(5);
		
		switch(arg1.getAction()) {
		//画面が押された時
		case MotionEvent.ACTION_DOWN :
			if(num != 0) {
				//画像を変更
				cowImg.setImageResource(R.drawable.hit_img);
				//音声ファイルの再生
				sp.play(normalSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
			} else {
				//画像を変更
				cowImg.setImageResource(R.drawable.mega_hit_img);
				//音声ファイルの再生
				sp.play(megaSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
			}
			return true;
		//画面から指が離れたとき
		case MotionEvent.ACTION_UP :
			//画像を変更
			cowImg.setImageResource(R.drawable.normal_img);
			return true;
		}
		return false;
	}
	
	
}
