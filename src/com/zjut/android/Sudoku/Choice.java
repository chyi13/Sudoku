package com.zjut.android.Sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;

public class Choice extends Activity {
	
	
	private Button easy;
	private Button medium;
	private Button hard;
//	private Button back;
	
	@Override
	public void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		
	//	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); 
    //  requestWindowFeature(Window.FEATURE_NO_TITLE); 
		
        setContentView(R.layout.choice);
		findView();
		setListener();
	}
	private void findView(){
		easy=(Button)findViewById(R.id.easy1);
		medium=(Button)findViewById(R.id.medium1);
		hard=(Button)findViewById(R.id.hard1);
	//	back=(Button)findViewById(R.id.back);
	}
	private void setListener(){
		easy.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(4);
			}
			
		});
		medium.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(6);
			}
			
		});
		hard.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(9);
			}
			
		});
		
	}
	private void startGame(int i){
		Intent intent = new Intent(Choice.this,Sudoku.class);
		intent.putExtra(Sudoku.KEY_DIFFICULTY, i);
		startActivity(intent);
	}
}
