package com.zjut.android.Sudoku;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameStart extends Activity {
	boolean mb_Loading=false;
	Button newgame;
	Button record;
	Button rules;
	Button exit;
	public int[] r={0,0,0};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.start);
        findview();
        setlistener();  
        
    }
    private void findview(){
    	 newgame=(Button)findViewById(R.id.Newgame);
    	 record=(Button)findViewById(R.id.Record);
    	 rules=(Button)findViewById(R.id.SudokuRules);
    	 exit=(Button)findViewById(R.id.Exit);
    }
    private void setlistener(){
    	 newgame.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				Intent intent=new Intent(GameStart.this,Choice.class);
 		        startActivity(intent);
 			}
         	
         });
    	 rules.setOnClickListener(new OnClickListener(){

  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				String temp="����һ��С�͵�������Ϸ���Ź��񣩲�֪���Ĺ���Ŀ�googleһ��.\n��ɺ���menu���ύ���Ĵ�~";
  				new AlertDialog.Builder(GameStart.this).setTitle("����").setMessage(temp).setPositiveButton("ȷ��", null).show();
  			}
          	
          });
    	  
         record.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				String temp="";
 		        SharedPreferences sp=getSharedPreferences("record",0);
 		        r[0]=sp.getInt("easy", 9999999);
 		        r[1]=sp.getInt("middle", 9999999);
 		        r[2]=sp.getInt("hard", 9999999);
 				temp=temp+"����ʱ�� = "+r[0]/100+" : "+r[0]%100+"\n"+"�м�ʱ�� = "+r[1]/100+" : "+r[1]%100+"\n"+"�߼�ʱ�� = "+r[2]/100+" : "+r[2]%100+"\n";
 				new AlertDialog.Builder(GameStart.this).setTitle("��¼").setMessage(temp).setPositiveButton("ȷ��", null).show();
 			}
         	
         });
    	 exit.setOnClickListener(new OnClickListener(){

 			@Override
 			public void onClick(View arg0) {
 				// TODO Auto-generated method stub
 				finish();
 			}
         	
         });
    	 
    }
  
 
}