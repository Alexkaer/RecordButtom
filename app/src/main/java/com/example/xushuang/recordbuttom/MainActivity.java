package com.example.xushuang.recordbuttom;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private RecordView record_view;
	private int count = 0;
	private boolean isRecording=false;

	private Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (isRecording) {
				count++;
				record_view.setProgress(count);
				myHandler.sendEmptyMessageDelayed(0, 100);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		record_view = (RecordView) findViewById(R.id.record_view);

		record_view.setMax(10);
		record_view.setOnGestureListener(new RecordView.OnGestureListener() {
			@Override
			public void onLongClick() {
				isRecording=true;
				myHandler.sendEmptyMessageDelayed(0, 100);
			}

			@Override
			public void onClick() {

			}

			@Override
			public void onLift() {

			}

			@Override
			public void onOver() {
				isRecording=false;
				count=0;
				record_view.closeButton();
			}
		});
	}
}
