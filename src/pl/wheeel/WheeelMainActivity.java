package pl.wheeel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class WheeelMainActivity extends Activity implements OnChronometerTickListener {

    private Chronometer mCounter;
	private Button mResetButton;
	private Button mStartButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mCounter = (Chronometer) findViewById(R.id.counterTime);
        mCounter.setOnChronometerTickListener(this);
        initButtons();
    }

	private void initButtons() {
		mStartButton = (Button) findViewById(R.id.counterStartButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        mCounter.start();
		        mStartButton.setEnabled(false);
			}
		});

        mResetButton = (Button) findViewById(R.id.counterResetButton);
        mResetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        mCounter.stop();
		        mStartButton.setEnabled(true);
			}
		});
	}

	public void onChronometerTick(Chronometer chronometer) {
		CharSequence x = chronometer.getText();
		CharSequence y = mCounter.getText();
	}

}