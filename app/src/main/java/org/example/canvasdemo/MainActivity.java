package org.example.canvasdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	MyView myView;
	int po = 0;



	public void setRunning(boolean running) {
		this.running = running;
	}

	private boolean running = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.moveButton);
		Button buttonleft = (Button) findViewById(R.id.moveleftButton);
		Button buttonup = (Button) findViewById(R.id.moveupButton);
		Button buttondown = (Button) findViewById(R.id.movedownButton);
		myView = (MyView) findViewById(R.id.myView);
		 TextView textView1 = (TextView) findViewById(R.id.timer);
		myView.setActivity(this);

		//listener of our pacman
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myView.moveRight(10);
				myView.moveUp(10);
				myView.moveDown(10);
				myView.moveRight(10);


				if (v.getId() == R.id.startButton) {
					running = true;
				} else if (v.getId() == R.id.stopButton) {
					running = false;
				} else if (v.getId() == R.id.resetButton) {

					myView.refreshDrawableState();

				}
			}
		});


		buttonleft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myView.moveLeft(10);
			}
		});


		buttonup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myView.moveUp(10);
			}
		});


		buttondown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myView.moveDown(10);
			}
		});



	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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

		if(countcoins < 1 ){

			myView.level++;
		}

	}


public void hitgohst(Ghost gost)
{



}


}


