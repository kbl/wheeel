/**
 * Sep 12, 2011
 */
package pl.nitroit.wheeel.location;

import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

/**
 * @author kbl
 *
 */
public class WheeelMapActivityTest extends ActivityInstrumentationTestCase2<WheeelMapActivity> {

	private WheeelMapActivity activity;
	private Solo solo;

	public WheeelMapActivityTest() {
		super(WheeelMapActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testIfToastShowedUp() {
		boolean toastShowedUp = solo.searchText("GPS module is disabled");
		assertTrue(toastShowedUp);
	}

}
