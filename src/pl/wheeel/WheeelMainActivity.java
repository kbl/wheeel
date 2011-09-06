package pl.wheeel;

import pl.wheeel.location.WheeelMapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;

/**
 * @author kbl
 *
 */
public class WheeelMainActivity extends Activity implements OnChronometerTickListener {

	private static final int COUNTER_STOPPED = 0;

	private static final int FIRST_HOURLY_PRICE = 2;
	private static final int HOURLY_PRICE = 4;

	private static final long TWENTY_MINUTES = 20 * 60 * 1000;
	private static final long ONE_HOUR = 3 * TWENTY_MINUTES;

	private static final String CYCLING_PRICE = "cyclingPrice";
	private static final String CYCLING_START = "cyclingStart";

	private static final int SPECIAL_PRICE_HOUR = 1;

	private Chronometer mCounter;
	private Button mStartButton;
	private TextView mPriceTextView;

	private int mPrice;
	private long mStartTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        initCounter();
        initButtons();
        mPriceTextView = (TextView) findViewById(R.id.counterPrice);
    }

	private void initCounter() {
		mCounter = (Chronometer) findViewById(R.id.counterTime);
        mCounter.setOnChronometerTickListener(this);
	}

	private void initButtons() {
		mStartButton = (Button) findViewById(R.id.counterStartButton);
		mStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mStartTime = SystemClock.elapsedRealtime();
				mCounter.setBase(mStartTime);
				mCounter.start();
				mStartButton.setEnabled(false);
			}
		});

		Button resetButton = (Button) findViewById(R.id.counterResetButton);
		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mStartTime = COUNTER_STOPPED;
				mCounter.stop();
				mStartButton.setEnabled(true);
				mPriceTextView.setText(R.string.counterPriceInitMsg);
			}
		});

		Button showLocationButton = (Button) findViewById(R.id.showLocationButton);
		showLocationButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), WheeelMapActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mPrice = savedInstanceState.getInt(CYCLING_PRICE);
		mStartTime = savedInstanceState.getLong(CYCLING_START);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(CYCLING_PRICE, mPrice);
		outState.putLong(CYCLING_START, mStartTime);
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
		long elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();
		countPrice(elapsedTime);
		mPriceTextView.setText(mPrice + " zl");

	}

	protected void countPrice(long elapsedTime) {
		if(elapsedTime > TWENTY_MINUTES) {
			int fullHours = (int) (elapsedTime / ONE_HOUR);
			int startedHour = elapsedTime % ONE_HOUR != 0 ? 1 : 0;
			int hoursWithoutDiscount = fullHours + startedHour - SPECIAL_PRICE_HOUR;
			mPrice = FIRST_HOURLY_PRICE;
			if(hoursWithoutDiscount > 0 ) {
				mPrice += HOURLY_PRICE * hoursWithoutDiscount;
			}
		}
		else {
			mPrice = 0;
		}
	}

	// used in unit tests
	protected int getmPrice() {
		return mPrice;
	}
	protected void setmPrice(int mPrice) {
		this.mPrice = mPrice;
	}

}
