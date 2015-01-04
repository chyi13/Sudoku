package com.zjut.android.Sudoku;



import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;


public class PuzzleView extends View {

	private final Sudoku game;
	TimeThread tt= new TimeThread();
	public PuzzleView(Context context) {
		super(context);
		this.game=(Sudoku)context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		tt.start();
		// TODO Auto-generated constructor stub
	}
	@Override 
	protected void onDraw(Canvas canvas){
		
		Paint back=new Paint();
		back.setColor(Color.WHITE);
		canvas.drawRect(0,0,getWidth(),getHeight(), back);
		//绘制数字
		Paint foreground1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground1.setColor(Color.RED);
		foreground1.setStyle(Style.FILL);
		foreground1.setTextSize(height*0.75f);
		foreground1.setTextScaleX(width/height);
		foreground1.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm=foreground1.getFontMetrics();
		Paint foreground2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground2.setColor(Color.BLUE);
		foreground2.setStyle(Style.FILL);
		foreground2.setTextSize(height*0.75f);
		foreground2.setTextScaleX(width/height);
		foreground2.setTextAlign(Paint.Align.CENTER);
		Paint usednumber=new Paint();
		usednumber.setColor(Color.rgb(210,238,238));
		float x= width/2;
		float y= height/2-(fm.ascent+fm.descent)/2;	
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				int temp;
				boolean used;
				used=this.game.getUsed(i, j);
				
				if (used==true){
					temp=this.game.getTitleStringOrg(i,j);
					canvas.drawRect(i*width,j*height, i*width+width, j*height+height, usednumber);
					canvas.drawText(String.valueOf(temp), i*width+x, j*height+y, foreground2);
				}
				else{
					temp=this.game.getTitleString(i,j);
					if (temp!=0)
						canvas.drawText(String.valueOf(temp), i*width+x, j*height+y, foreground1);
				}
			}
		}
		Paint dark=new Paint();
		dark.setStrokeWidth(3);
		dark.setColor(Color.BLUE);				
		Paint light= new Paint();
		light.setStrokeWidth(1);
		light.setColor(Color.BLUE);
		
		//绘制盘线
		for (int i=0;i<10;i++){
			if (i%3==0){
				canvas.drawLine(0,i*height, getWidth(), i*height, dark);//绘制横线
				canvas.drawLine(i*width,0, i*width, getHeight(), dark);  //绘制竖线
			}
			else{
				canvas.drawLine(0,i*height, getWidth(), i*height, light);//绘制横线
				canvas.drawLine(i*width,0, i*width, getHeight(), light);  //绘制竖线
			}
		}
		Paint wrong=new Paint();
		wrong.setColor(Color.RED);
		wrong.setStyle(Paint.Style.STROKE);
		wrong.setStrokeWidth(3);
		
		Paint selected=new Paint();
		selected.setColor(Color.rgb(25,25,112));
		selected.setStyle(Paint.Style.STROKE);
		selected.setStrokeWidth(3);
		canvas.drawRect(selRect,selected);
		if (this.game.check==true){
				int shit=0;
				while(this.game.checkarray[shit]!=-1){
					int i=this.game.checkarray[shit]/10;
					int	j=this.game.checkarray[shit]%10;
					canvas.drawRect(j*width,i*height, j*width+width, i*height+height, wrong);
					shit++;
				}
			//this.game.check=false;
		}	
		
		

		invalidate();
		

	}
	private float width;//小方格的宽度
	private float height;//小方格的高度
	private int selX;
	private int selY;
	private final Rect selRect=new Rect();
	@Override
	protected void onSizeChanged(int w,int h,int oldw,int oldh){
		
		width=w/9f;
		height=h/9f;
		getRect(selX,selY,selRect);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	private void getRect(int x,int y,Rect rect){
		rect.set((int)(x*width+2), (int)(y*height+2),(int)(x*width+width-1), (int)(y*height+height-1));
	}
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selY-1<0)	
				select(selX,selY);
			else
				select(selX,selY-1);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (selY+1>8)	
				select(selX,selY);
			else
				select(selX,selY+1);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selX-1<0)
				select(selX,selY);
			else
				select(selX-1,selY);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (selX+1>8)
				select(selX,selY);
			else
				select(selX+1,selY);
			break;
		case KeyEvent.KEYCODE_0:
		case KeyEvent.KEYCODE_SPACE: setSelectedNumber(0); break;
		case KeyEvent.KEYCODE_1: setSelectedNumber(1); break;
		case KeyEvent.KEYCODE_2: setSelectedNumber(2); break;
		case KeyEvent.KEYCODE_3: setSelectedNumber(3); break;
		case KeyEvent.KEYCODE_4: setSelectedNumber(4); break;
		case KeyEvent.KEYCODE_5: setSelectedNumber(5); break;
		case KeyEvent.KEYCODE_6: setSelectedNumber(6); break;
		case KeyEvent.KEYCODE_7: setSelectedNumber(7); break;
		case KeyEvent.KEYCODE_8: setSelectedNumber(8); break;
		case KeyEvent.KEYCODE_9: setSelectedNumber(9); break;
		case KeyEvent.KEYCODE_ENTER: 
		case KeyEvent.KEYCODE_DPAD_CENTER:
		{
			if (this.game.getUsed(selX,selY)==false)
			game.showKeypad();
		}
		
		default:
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}
	
	public void setSelectedNumber(int number){
		game.setNumber(selX,selY,number);
		invalidate();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		if (event.getAction()!=MotionEvent.ACTION_DOWN){
			return super.onTouchEvent(event);
		}
		select((int)(event.getX()/width),(int)(event.getY()/height));
		if (this.game.getUsed(selX,selY)==false)
			game.showKeypad();
		return true;
	}
	private void select(int x,int y){
		//x为横向格数位置y为纵向
		invalidate(selRect);
		selX=Math.min(Math.max(x, 0), 8);
		selY=Math.min(Math.max(y, 0), 8);
		getRect(x,y,selRect);
		invalidate(selRect);
	}
	public int getMin(){
		return tt.returnmin();
	}
	public int getSec(){
		return tt.returnsec();
	}
	
	

}
