package com.zjut.android.Sudoku;


import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Sudoku extends Activity {

	protected static final int MENU_CHECK=Menu.FIRST;
	protected static final int MENU_TIME= Menu.FIRST+1;
	PuzzleView puzzleview;
	int puzzle[]=new int[81];
	int puzzle2[]=new int [81];
	int[] checkarray=new int[9];
	boolean check=false;
	public static final String KEY_DIFFICULTY="difficulty";
	private int diff;
	GameStart gs=new GameStart();
	public static final int DIFFICULTY_EASY=0;
	String puzzletest="000050208094300000000000000000062500000000094000000000260000100000400070500000000";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        puzzleview=new PuzzleView(this);
        SudokuCreate sc=new SudokuCreate();
        
        diff=getIntent().getIntExtra(KEY_DIFFICULTY,DIFFICULTY_EASY);
        
        puzzletest=sc.getSudokuString(diff);//计算产生等级不同的随机数独题目
        string2int(puzzletest);
        setContentView(puzzleview);
        puzzleview.requestFocus();
    }
    private void string2int(String temp){
    	for (int i=0;i<temp.length();i++){
    		puzzle[i]=(int)temp.charAt(i)-(int)'0';
    		puzzle2[i]=puzzle[i];
    		
    	}
    }
    public int getTitleString(int i,int j){
    	return puzzle2[j*9+i];
    }
    public void showKeypad(){
    	Dialog v=new Keypad(this,puzzleview);
		v.show();
    }
    public void setNumber(int selX,int selY,int number){
    	puzzle2[selX+selY*9]=number;//设置当前解的数字
    }
    public boolean getUsed(int i,int j){
    	if (puzzle[j*9+i]!=0) return true;
    	else return false;
    }
    public int getTitleStringOrg(int i,int j){
    	return puzzle[j*9+i];	//返回题目中的原始数字
    }
    public int[] checkNumber(){
    	//测试用户的解是否有错
    	int [] c={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    	int[][]result=changeTo2d();
    	int counter=0;
    	
    	int i,j;
        for (i=0;i<9;i++){
        	
        	int count[]={1,0,0,0,0,0,0,0,0,0};
    		for (j=0;j<9;j++){
    			count[result[i][j]]++;
    		}
    		
    		for (int it=0;it<10;it++){
    			if (count[it]>=2){
    				for (int x=0;x<9;x++ ){
    					if (result[i][x]==it) 
    					{
    						c[counter]=i*10+x;
    						counter++;
    					}
    				}
    				counter=0;
    				return c;
    			}
    		}
    		
    	}
        for (j=0;j<9;j++){
        	
        	int count[]={1,0,0,0,0,0,0,0,0,0};
        	for (i=0;i<9;i++){
        			count[result[i][j]]++;
        		}
        		for (int it=0;it<10;it++){
        			if (count[it]>=2){
        				for (int x=0;x<9;x++ ){
        					if (result[x][j]==it) {
        						c[counter]=j+x*10;
        						counter++;
        					}
        				}
        				counter=0;
        				return c;
        			}
        	}
        }
        return c;
        
    }
    private int[][] changeTo2d(){
    	int[][]temp=new int[9][9];
    	for (int i=0;i<9;i++){
    		for (int j=0;j<9;j++){
    			temp[i][j]=puzzle2[i*9+j];
    		}
    	}
    	return temp;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0,MENU_CHECK,0,"完成");
    	menu.add(0,MENU_TIME,0,"暂停");
    	return super.onCreateOptionsMenu(menu);
    }
    private int []record={0,0,0};
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case MENU_CHECK:
    		checkarray= checkNumber();
    		check=true;
    		if (checkarray[0]==-1){
    			SharedPreferences sp=getSharedPreferences("record",0);
    			SharedPreferences.Editor editor=sp.edit();
    			record[0]=sp.getInt("easy", 9999999);
    			record[1]=sp.getInt("middle", 9999999);
    			record[2]=sp.getInt("hard", 9999999);
    			
    			int resultmin,resultsec,total=0;
    			resultmin=puzzleview.getMin();
    			resultsec=puzzleview.getSec();
    			total=resultmin*100+resultsec;
    			
    	//		puzzleview.tt.destroy();
    			switch(diff){
    			case 4:
    				if (total<record[0]) {
    					editor.putInt("easy",total);
    	    			editor.commit();
    	    			Toast.makeText(this,"恭喜你破纪录~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    				}
    				else{
    					Toast.makeText(this,"恭喜你完成了~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    				}
    				break;
    			case 6:
    				if (total<record[1]){
    					editor.putInt("middle",total);
    	    			editor.commit();
    	    			Toast.makeText(this,"恭喜你破纪录~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    				}
    				else{
    					Toast.makeText(this,"恭喜你完成了~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    	    			}
    				break;
    			case 9:
    				if (total<record[2]){
    					editor.putInt("easy",total);
    	    			editor.commit();
    	    			Toast.makeText(this,"恭喜你破纪录~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    				}
    				else{
    					Toast.makeText(this,"恭喜你完成了~您花费的时间为  "+resultmin+" : "+resultsec, Toast.LENGTH_SHORT).show();
    				}
    				break;
    			}
    			
    			
    		}
    		break;
    	case MENU_TIME:
    		if (item.getTitle()=="暂停"){
    			puzzleview.tt.suspend();
    			item.setTitle("继续");
    		}
    		else {
    			item.setTitle("暂停");
    			puzzleview.tt.resume();
    		}
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
 
	

}