package com.zjut.android.Sudoku;

public class TimeThread extends Thread {
	
	private int min,sec;
	public TimeThread(){
		min=0;
		sec=0;
	}
	public void run(){
		while(true){
			try{
				Thread.sleep(1000);
				sec++;
				if (sec>=60){
					min++;
					sec=0;
				}
			}
			catch(Exception e){
			
			}
		}
	}
	public int returnsec(){
		return sec;
	}
	public int returnmin(){
		return min;
	}
}
