package org.example.canvasdemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity{

	MyView myView;
	int po = 0;
	boolean pause = false;
	boolean stop = false;
	public int counter = 3;
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean running = false;
	private Timer gameTimer;
	private Timer pacmanTimer;
	private Timer ghostTimer;
	private static final String TAG = "com.example.StateChange" ;
	private String savepoints= "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button = (Button) findViewById(R.id.moveButton);
		final Button buttonright = (Button) findViewById(R.id.moverightButton);
		final Button buttonleft = (Button) findViewById(R.id.moveleftButton);
		final Button buttonup = (Button) findViewById(R.id.moveupButton);
		final Button buttondown = (Button) findViewById(R.id.movedownButton);
		final Button buttonstartspil = (Button) findViewById(R.id.startButton);
		final Button buttonstopspil = (Button) findViewById(R.id.stopButton);
		final Button buttonpausespil = (Button) findViewById(R.id.pauseButton);
		myView = (MyView) findViewById(R.id.myView);
		updatelife();
		myView.setActivity(this);
		gameTimer = new Timer();
		pacmanTimer = new Timer();
		ghostTimer = new Timer();
		running = true;
		//listener of our pacman


		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myView.moveRight(10);
				myView.moveUp(10);
				myView.moveDown(10);
				myView.moveRight(10);
				/*if (v.getId() == R.id.startButton) {
					myView.newGame = true;
				} else if (v.getId() == R.id.stopButton) {
					running = false;
				} else if (v.getId() == R.id.resetButton) {

					myView.refreshDrawableState();

				}
			}
		})


*/

				buttonstartspil.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {


						if(pause == true){running = true;}

						if(stop == true) {

							Intent i = getBaseContext().getPackageManager()
									.getLaunchIntentForPackage( getBaseContext().getPackageName() );
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);



						}

					}
				});


				buttonstopspil.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						stop = true;
						onStop();
					}
				});

				buttonpausespil.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(running = true) {
							running = false;
							pause = true;
						}

					}
				});


				buttonleft.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						up = false;
						down = false;
						right = false;
						left = true;

					}
				});

				/*buttonright.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						up = false;
						down = false;
						right = true;
						left = false;

					}
				});*/


				buttonup.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						left = false;
						down = false;
						right = false;
						up = true;

					}
				});


				buttondown.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						left = false;
						up = false;
						right = false;
						down = true;


					}
				});


			}
		});

		gameTimer = new Timer();
		running = true; //should the game be running?
		//We will call the timer 5 times each second
		gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();

			}

		}, 0, 200); //0 indicates we start now, 200
		//is the number of miliseconds between each call
	}


	@Override
	protected void onStop() {
		super.onStop();
		//just to make sure if the app is killed, that we stop the timer.
		gameTimer.cancel();
		pacmanTimer.cancel();
		ghostTimer.cancel();
	}

	private void TimerMethod()
	{
		//updat(); crasher
		this.runOnUiThread(Timer_Tick);
	}


	private Runnable Timer_Tick = new Runnable() {
		public void run() {


			if (running)
			{
			//	updat(); tiden vikker ikke
				if(right == true) {
					myView.moveRight(20);
					up = false;
					down = false;
					left = false;
				}

				if(up == true) {
					myView.moveUp(20);
					right = false;
					down = false;
					left = false;
				}

				if(down == true) {
					myView.moveDown(20);
					up = false;
					right = false;
					left = false;
				}

				if(left == true) {
					myView.moveLeft(20);
					up = false;
					down = false;
					right = false;
				}

				/*for(ghostTimer){
					myView.ghostinfo();

				}*/
				}
			}

		};



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	public void updatepoint(GoldCoin goldCoin){

		int countcoins = 10;
		TextView textView2 = (TextView) findViewById(R.id.points);

		if(goldCoin.getTakken() == false)
		{
			po++;

			textView2.setText("points" + po + "");
			countcoins--;

		}

		if(countcoins < 11){

			myView.level++;

		}

	}


	public void hitgohst(Ghost gost)
{

	if(gost.getdead() == false)
	{
		counter--;
		updatelife();

		if(counter <= 0){
			updatelife();
			onStop();
			running = false;
		}
	}
	}

	public void updatelife(){
		TextView textView1 = (TextView) findViewById(R.id.life);
		textView1.setText("life"  + counter );


	}

}


