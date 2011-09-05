/**
 * Sep 5, 2011
 */
package pl.wheeel.test;

import pl.wheeel.R;
import pl.wheeel.WheeelMainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author kbl
 *
 */
public class WheeelMainActivityTest extends ActivityInstrumentationTestCase2<WheeelMainActivity> {

	private WheeelMainActivity activity;
	private Button resetButton;
	private Button startButton;
	private TextView priceTextView;
	private String priceInitMsg;

	public WheeelMainActivityTest() {
		super(WheeelMainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		startButton = (Button) activity.findViewById(R.id.counterStartButton);
		resetButton = (Button) activity.findViewById(R.id.counterResetButton);
		priceTextView = (TextView) activity.findViewById(R.id.counterPrice);
		priceInitMsg = activity.getString(R.string.counterInitMsg);
	}

	public void testPreConfiguration() {
		assertEquals(priceTextView.getText(), priceInitMsg);
	}

}
