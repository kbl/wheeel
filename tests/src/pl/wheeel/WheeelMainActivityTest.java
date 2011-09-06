/**
 * Sep 5, 2011
 */
package pl.wheeel;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * @author kbl
 *
 */
public class WheeelMainActivityTest extends ActivityInstrumentationTestCase2<WheeelMainActivity> {

	private WheeelMainActivity activity;
	private TextView priceTextView;
	private String priceInitMsg;

	public WheeelMainActivityTest() {
		super(WheeelMainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		priceTextView = (TextView) activity.findViewById(R.id.counterPrice);
		priceInitMsg = activity.getString(R.string.counterPriceInitMsg);
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




	}

}
