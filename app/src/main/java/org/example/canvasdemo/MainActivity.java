package org.example.canvasdemo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity{

	MyView myView;
	int po = 0;
	int countdown = 200;
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
		Log.d(TAG, "onCreate");
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
						running = true;

						if(pause == true){

							Context context = getApplicationContext();
							CharSequence text = "forsætter";
							int duration = Toast.LENGTH_SHORT;

							Toast toast = Toast.makeText(context, text, duration);
							toast.show();
							running = true;}

						if(stop == true) {

							Context context = getApplicationContext();
							CharSequence text = "nytspil";
							int duration = Toast.LENGTH_SHORT;

							Toast toast = Toast.makeText(context, text, duration);
							toast.show();

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
						Context context = getApplicationContext();
						CharSequence text = "pause";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
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

		if (running = true) {
		gameTimer = new Timer();

		gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {


					TimerMethod();


			}

		}, 0, 200); //0 indicates we start now, 200
		//is the number of miliseconds between each call
	}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onPause");
		gameTimer.cancel();
		pacmanTimer.cancel();
		ghostTimer.cancel();
		Context context = getApplicationContext();
		CharSequence text = "slutter spillet";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}


	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//ALWAYS CALL THE SUPER METHOD - To be nice!
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
		/* Here we put code now to save the state */
		outState.putString("point", savepoints);

	}
	//this is called when our activity is recreated, but
	//AFTER our onCreate method has been called
	//EXTREMELY IMPORTANT DETAIL
	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		//MOST UI elements will automatically store the information
		//if we call the super.onRestoreInstaceState
		//but other data will be lost.
		super.onRestoreInstanceState(savedState);
		Log.d(TAG, "onRestoreInstanceState");
		/*Here we restore any state */
		TextView savedpoint = (TextView) findViewById(R.id.points);
		//in the line below, notice key value matches the key from onSaved
		//this is of course EXTREMELY IMPORTANT
		this.savepoints = savedState.getString("savedpoint");


		savedpoint.setText("point:" + po);

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
				TextView textView3 = (TextView) findViewById(R.id.timer);
				textView3.setText("tid" + countdown + "");


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



				if(countdown <= 0)
				{
					onStop();
				}

				countdown--;
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

		int countcoins = 0;
		TextView textView2 = (TextView) findViewById(R.id.points);

		if(goldCoin.getTakken() == false)
		{
			po++;

			textView2.setText("points" + po + "");
			countcoins++;


		}

		if(countcoins <= 2){


			myView.level++;
			countdown = 200;

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
			Context context = getApplicationContext();
			CharSequence text = "du døde dit liv kom ned på 0";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}
	}

	public void updatelife(){
		TextView textView1 = (TextView) findViewById(R.id.life);
		textView1.setText("life"  + counter );


	}


	public void updateghost(Ghost ghost) {


	}
}


