package pl.wheeel;

import android.app.Activity;
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

	private Chronometer mCounter;
	private Button mStartButton;
	private TextView mPrice;

	private static final int FIRST_HOURLY_PRICE = 2;
	private static final int HOURLY_PRICE = 4;

	private static final long TWENTY_MINUTES = 10 * 1000;
	private static final long ONE_HOUR = 3 * TWENTY_MINUTES;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        initCounter();
        initButtons();
        mPrice = (TextView) findViewById(R.id.counterPrice);
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
				mCounter.setBase(SystemClock.elapsedRealtime());
				mCounter.start();
				mStartButton.setEnabled(false);
			}
		});

		Button mResetButton = (Button) findViewById(R.id.counterResetButton);
		mResetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCounter.stop();
				mStartButton.setEnabled(true);
				mPrice.setText(R.string.counterPriceInitMsg);
			}
		});
	}

	@Override
	public void onChronometerTick(Chronometer chronometer) {
		long time = SystemClock.elapsedRealtime() - chronometer.getBase();
		if(time > TWENTY_MINUTES) {
			if(time > ONE_HOUR) {
				time = (time % ONE_HOUR) - 1;
				mPrice.setText((FIRST_HOURLY_PRICE + time * HOURLY_PRICE) + " zl");
			}
			else {
				mPrice.setText(FIRST_HOURLY_PRICE + " zl");
			}
		}
	}

}