/**
 * Sep 5, 2011
 */
package pl.nitroit.wheeel;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

/**
 * @author kbl
 *
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;
	private TextView priceTextView;
	private String priceInitMsg;
	private Solo solo;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		priceTextView = (TextView) activity.findViewById(R.id.counterPrice);
		priceInitMsg = activity.getString(R.string.counterPriceInitMsg);
		solo = new Solo(getInstrumentation(), activity);
	}

	public void testPreConfiguration() {
		assertEquals(priceTextView.getText(), priceInitMsg);
	}

	public void testIfStateIsSavedOnDestroyingActivity() {
		activity.setmPrice(123);
		activity.finish();

		activity = getActivity();
		assertEquals(activity.getmPrice(), 123);
	}

	public void testIfPriceAndStartTimeAreReseted() {
		activity.setmPrice(123);
		activity.setmStartTime(123L);

		solo.clickOnButton("Reset");

		assertEquals(activity.getmPrice(), 0);
		assertEquals(activity.getmStartTime(), 0L);
	}

}
