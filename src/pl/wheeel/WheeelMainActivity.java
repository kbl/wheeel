package pl.wheeel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Chronometer;

public class WheeelMainActivity extends Activity {

    private Chronometer counter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        counter = (Chronometer) findViewById(R.id.counterTime);
        counter.start();
    }

}