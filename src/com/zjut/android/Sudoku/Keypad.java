package com.zjut.android.Sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Keypad extends Dialog {

	private final View keys[]=new View[10];
	public View keypad;
	private final PuzzleView puzzleView;
	
	
	
	public Keypad(Context context,PuzzleView puzzleView) {
		super(context);
		this.puzzleView=puzzleView;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keypad);
		findViews();
		setListener();
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		int number=0;
		switch(keyCode){
		case KeyEvent.KEYCODE_0:
		case KeyEvent.KEYCODE_SPACE: number=0; break;
		case KeyEvent.KEYCODE_1: 	 number=1; break;
		case KeyEvent.KEYCODE_2: 	 number=2; break;
		case KeyEvent.KEYCODE_3: 	 number=3; break;
		case KeyEvent.KEYCODE_4: 	 number=4; break;
		case KeyEvent.KEYCODE_5: 	 number=5; break;
		case KeyEvent.KEYCODE_6: 	 number=6; break;
		case KeyEvent.KEYCODE_7: 	 number=7; break;
		case KeyEvent.KEYCODE_8: 	 number=8; break;
		case KeyEvent.KEYCODE_9: 	 number=9; break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		
			returnResult(number);
		return true;
		
	}
	
	private void returnResult(int number){
		puzzleView.setSelectedNumber(number);
		dismiss();
	}
	
	private void findViews(){
		keypad=findViewById(R.id.keypad);
		keys[1]=findViewById(R.id.keypad_1);
		keys[2]=findViewById(R.id.keypad_2);
		keys[3]=findViewById(R.id.keypad_3);
		keys[4]=findViewById(R.id.keypad_4);
		keys[5]=findViewById(R.id.keypad_5);
		keys[6]=findViewById(R.id.keypad_6);
		keys[7]=findViewById(R.id.keypad_7);
		keys[8]=findViewById(R.id.keypad_8);
		keys[9]=findViewById(R.id.keypad_9);
		keys[0]=findViewById(R.id.keypad_0);
		
	}
	private void setListener(){
		for (int i=0;i<keys.length;i++){
			final int t=i;
			keys[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					returnResult(t);
				}
			});
		}
	}

}
