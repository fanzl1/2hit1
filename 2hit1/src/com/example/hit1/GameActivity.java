package com.example.hit1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 
 * @author superwx
 *
 */
public class GameActivity extends Activity {
 
	 gameView myView ;
	 public static boolean isPlayer1;
	 public static int i_old;
	 public static int j_old;
	 public static int p1_num;
	 public static int p2_num;
	 public static boolean isOver;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new gameView(this);
        isPlayer1 = true;
        i_old=-1;
        j_old=-1;
        p1_num=4;
        p2_num=4;
        isOver=false;
        setContentView(myView);
        myView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				if(!isOver){
				float x = event.getX();
				float y = event.getY();
				float width = myView.width;
				float qiziSize= myView.qiziSize;
				if(y<width*4){
					int i = (int) (y/width);
					int j = (int) (x/width);
					if((x-width*(1+2*j)/2)*(x-width*(1+2*j)/2)+(y-width*(1+2*i)/2)*(y-width*(1+2*i)/2)<qiziSize*qiziSize){
						if(myView.qizi[i][j]==0){
							if((i-i_old)*(i-i_old)+(j-j_old)*(j-j_old)==1){
								myView.qizi[i][j]=myView.qizi[i_old][j_old];
								myView.qizi[i_old][j_old]=0;
				    	     cleanClicked();
				    	     if(isPlayer1){
				    	    	 checkP1(i,j);
				    	     }else{
				    	    	 checkP2(i,j);
				    	     }
				    	     checkOver();
					    	     isPlayer1=!isPlayer1;
							}
						}else if(myView.qizi[i][j]==1&&isPlayer1){
							cleanClicked();
							myView.qizi[i][j]=myView.qizi[i][j]+10;
							i_old=i;
							j_old=j;
						}else if(myView.qizi[i][j]==2&&!isPlayer1){
							cleanClicked();
							myView.qizi[i][j]=myView.qizi[i][j]+10;
							i_old=i;
							j_old=j;
						}
						myView.invalidate();
					}
				}
				}
				return true;
			
			}
			
			
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		 super.onOptionsItemSelected(item);
		 switch(item.getItemId()){
		 case R.id.action_restart:
			 restart();
			 break;
		 }
		 return true;
	}

    public void restart(){
    	for(int i=0;i<4;i++){
			myView.qizi[0][i]=1;
			myView.qizi[1][i]=0;
			myView.qizi[2][i]=0;
			myView.qizi[3][i]=2;
		}
    	isPlayer1 = true;
        i_old=-1;
        j_old=-1;
        p1_num=4;
        p2_num=4;
        isOver=false;
        myView.invalidate();
    	
    }
    public void cleanClicked(){
    
    	
    	if(i_old!=-1){
    		if(myView.qizi[i_old][j_old]>10)
    	     myView.qizi[i_old][j_old]=myView.qizi[i_old][j_old]-10;
    	     i_old=-1;
    	     j_old=-1;
    	}
    	for(int i=0;i<4;i++)
    		for(int j=0;j<4;j++){
    			if(myView.qizi[i][j]>10){
    				myView.qizi[i][j]=myView.qizi[i][j]-10;
    			}
    		}
    	
    }
    public void checkP1(int hang,int lie){
    	//lie��瑰��
    	int p1 = 0;
    	int p2 = 0;
    	for(int i=0;i<4;i++){
    		if(myView.qizi[i][lie]==1){
    			p1++;
    			}else if(myView.qizi[i][lie]==2){
        			p2++;
        			}
    	}
//    	if(p1==3 && p2==1){
//    		for(int i=0;i<4;i++){
//    			if(myView.qizi[i][lie]==2){
//    				if(hang==0&&i!=2){
//    					break;
//    				}else if(hang==3&&i!=1){
//    					break;
//    				}else{
//    				myView.qizi[i][lie]=0;
//    				p2_num--;
//    				break;
//    				}
//    			}
//    		}
//    	}else 
    	if(p1==2 && p2==1){
    		for(int i=0;i<4;i++){
    			if(myView.qizi[i][lie]==2){
    				if(i<2){
    					if(myView.qizi[i+1][lie]==1&&myView.qizi[i+2][lie]==1){
    						myView.qizi[i][lie]=0;
    	    				p2_num--;
    					}
    				}else{
    					if(myView.qizi[i-1][lie]==1&&myView.qizi[i-2][lie]==1){
    						myView.qizi[i][lie]=0;
    	    				p2_num--;
    					}
    				}
    				
    				break;
    			}
    		}
    	}
    	//hang��瑰��
    	p1 = 0;
    	p2 = 0;
    	for(int i=0;i<4;i++){
    		if(myView.qizi[hang][i]==1){
    			p1++;
    			}else if(myView.qizi[hang][i]==2){
        			p2++;
        			}
    	}
//    	if(p1==3 && p2==1){
//    		for(int i=0;i<4;i++){
//    			if(myView.qizi[hang][i]==2){
//    				if(lie==0&&i!=2){
//    					break;
//    				}else if(lie==3&&i!=1){
//    					break;
//    				}else{
//    				myView.qizi[hang][i]=0;
//    				p2_num--;
//    				break;
//    				}
//    			}
//    		}
//    	}else 
    	if(p1==2 && p2==1){
    		for(int i=0;i<4;i++){
    			if(myView.qizi[hang][i]==2){
    				if(i<2){
    					if(myView.qizi[hang][i+1]==1&&myView.qizi[hang][i+2]==1){
    						myView.qizi[hang][i]=0;
    	    				p2_num--;
    					}
    				}else{
    					if(myView.qizi[hang][i-1]==1&&myView.qizi[hang][i-2]==1){
    						myView.qizi[hang][i]=0;
    	    				p2_num--;
    					}
    				}
    				
    				break;
    			}
    		}
    	}
    	
    	
    	
    }
    public void checkP2(int hang,int lie){
    	//lie��瑰��
    	int p1 = 0;
    	int p2 = 0;
    	for(int i=0;i<4;i++){
    		if(myView.qizi[i][lie]==1){
    			p1++;
    			}else if(myView.qizi[i][lie]==2){
        			p2++;
        			}
    	}
//    	if(p2==3 && p1==1){
//    		for(int i=0;i<4;i++){
//    			if(myView.qizi[i][lie]==1){
//    				if(hang==0&&i!=2){
//    					break;
//    				}else if(hang==3&&i!=1){
//    					break;
//    				}else{
//    				myView.qizi[i][lie]=0;
//    				p1_num--;
//    				break;
//    				}
//    			}
//    		}
//    	}else 
    	if(p2==2 && p1==1){
    		for(int i=0;i<4;i++){
    			if(myView.qizi[i][lie]==1){
    				if(i<2){
    					if(myView.qizi[i+1][lie]==2&&myView.qizi[i+2][lie]==2){
    						myView.qizi[i][lie]=0;
    	    				p1_num--;
    					}
    				}else{
    					if(myView.qizi[i-1][lie]==2&&myView.qizi[i-2][lie]==2){
    						myView.qizi[i][lie]=0;
    	    				p1_num--;
    					}
    				}
    				
    				break;
    			}
    		}
    	}
    	//hang��瑰��
    	p1 = 0;
    	p2 = 0;
    	for(int i=0;i<4;i++){
    		if(myView.qizi[hang][i]==1){
    			p1++;
    			}else if(myView.qizi[hang][i]==2){
        			p2++;
        			}
    	}
//    	if(p2==3 && p1==1){
//    		for(int i=0;i<4;i++){
//    			if(myView.qizi[hang][i]==1){
//    				if(lie==0&&i!=2){
//    					break;
//    				}else if(lie==3&&i!=1){
//    					break;
//    				}else{
//    				myView.qizi[hang][i]=0;
//    				p1_num--;
//    				break;
//    				}
//    			}
//    		}
//    	}else 
    	if(p2==2 && p1==1){
    		for(int i=0;i<4;i++){
    			if(myView.qizi[hang][i]==1){
    				if(i<2){
    					if(myView.qizi[hang][i+1]==2&&myView.qizi[hang][i+2]==2){
    						myView.qizi[hang][i]=0;
    	    				p1_num--;
    					}
    				}else{
    					if(myView.qizi[hang][i-1]==2&&myView.qizi[hang][i-2]==2){
    						myView.qizi[hang][i]=0;
    	    				p1_num--;
    					}
    				}
    				
    				break;
    			}
    		}
    	}
    	
    	
    	
    }
    public void checkOver(){
    	if(p1_num<2){
    		isOver = true;
    		myView.text="�����硅��锛�";
    	}else if(p2_num<2){
    		isOver=true;
    		myView.text="绾㈡�硅��锛�";
    	}else if(isPlayer1){
    		isOver=true;
    		for(int i=0;i<4&&isOver;i++)
    			for(int j=0;j<4&&isOver;j++){
    				if(myView.qizi[i][j]==2){
    					for(int m=0;m<4&&isOver;m++)
    						for(int n=0;n<4&&isOver;n++){
    							if(myView.qizi[m][n]==0){
    								if((i-m)*(i-m)+(j-n)*(j-n)==1){
    									isOver=false;
    									break;
    								}
    							}
    						}
    				}
    			}
    		if(isOver){
    			myView.text="�����硅����垫�荤孩��硅��锛�";
    		}
    	}else{
    		isOver=true;
    		for(int i=0;i<4&&isOver;i++)
    			for(int j=0;j<4&&isOver;j++){
    				if(myView.qizi[i][j]==1){
    					for(int m=0;m<4&&isOver;m++)
    						for(int n=0;n<4&&isOver;n++){
    							if(myView.qizi[m][n]==0){
    								if((i-m)*(i-m)+(j-n)*(j-n)==1){
    									isOver=false;
    									break;
    								}
    							}
    						}
    				}
    			}
    		if(isOver){
    			myView.text="绾㈡�硅����垫�昏����硅��锛�";
    		}
    	}
    	
    }
   
   
    
}
