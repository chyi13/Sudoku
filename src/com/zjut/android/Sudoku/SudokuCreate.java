package com.zjut.android.Sudoku;

public class SudokuCreate {
	private int[][] or={{8,9,7,2,3,1,5,6,4},
						{3,1,2,5,6,4,8,9,7},
						{6,4,5,8,9,7,2,3,1},
						{4,5,6,1,2,3,7,8,9},
						{7,8,9,4,5,6,1,2,3},
						{1,2,3,7,8,9,4,5,6},
						{9,7,8,3,1,2,6,4,5},
						{2,3,1,6,4,5,9,7,8},
						{5,6,4,9,7,8,3,1,2}};
	public SudokuCreate(){
		
	}
	String temp="";
	private void shuffle(int level){
		int i,j;
		int []number={0,0,0,0,0,0,0,0,0};
		int[] newnumber={0,0,0,0,0,0,0,0,0};
		for (i=0;i<9;i++)
		{
			int temp=(int)(Math.random()*9);
			
			while(number[temp]!=0)
			{
				temp=(int)(Math.random()*9);
				
			}
			number[temp]=1;
			newnumber[i]=temp+1;
		}
		for (i=0;i<9;i++)
		{
			for (j=0;j<9;j++)
			{
				int shit=or[i][j];
				or[i][j]=newnumber[shit-1];
			}
			
		}
		for (i=0;i<9;i++)
		{
			int count=level;
			while(count>=0)
			{
				int shittemp=(int)(Math.random()*9);
				or[i][shittemp]=0;
				count--;
			}
		}
	}
	public String getSudokuString(int level){
	//	int count=0;
		shuffle(level);
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				temp=or[i][j]+temp;
			}
		}
		return temp;
	}
}
