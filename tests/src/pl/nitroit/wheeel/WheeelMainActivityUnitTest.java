/**
 * Sep 6, 2011
 */
package pl.nitroit.wheeel;

import pl.nitroit.wheeel.WheeelMainActivity;
import junit.framework.TestCase;

/**
 * @author kbl
 *
 */
public class WheeelMainActivityUnitTest extends TestCase {

	private static final long ONE_MINUTE = 60 * 1000;
	private static final long TWENTY_MINUTES = 20 * ONE_MINUTE;
	private static final long TWENTYONE_MINUTES = 21 * ONE_MINUTE;
	private static final long ONE_HOUR = 3 * TWENTY_MINUTES;
	private static final long ONE_HOUR_1_MINUTE = ONE_HOUR + ONE_MINUTE;
	private static final long ONE_HOUR_59_MINUTES = ONE_HOUR + 59 * ONE_MINUTE;
	private static final long TWO_HOURS = ONE_HOUR * 2;
	private static final long TWO_HOURS_1_MINUTE = TWO_HOURS + ONE_MINUTE;

	private WheeelMainActivity activity = new WheeelMainActivity();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity.setmPrice(0);
	}

	public void testPriceCounting20Minutes() {
		activity.countPrice(TWENTY_MINUTES);
		assertEquals(0, activity.getmPrice());
	}

	public void testPriceCounting21Minutes() {
		activity.countPrice(TWENTYONE_MINUTES);
		assertEquals(2, activity.getmPrice());
	}

	public void testPriceCounting1Hour() {
		activity.countPrice(ONE_HOUR);
		assertEquals(2, activity.getmPrice());
	}

	public void testPriceCounting1Hour1Minute() {
		activity.countPrice(ONE_HOUR_1_MINUTE);
		assertEquals(6, activity.getmPrice());
	}

	public void testPriceCounting1Hour59Minute() {
		activity.countPrice(ONE_HOUR_59_MINUTES);
		assertEquals(6, activity.getmPrice());
	}

	public void testPriceCounting2Hours() {
		activity.countPrice(TWO_HOURS);
		assertEquals(6, activity.getmPrice());
	}

	public void testPriceCounting2Hours1Minute() {
		activity.countPrice(TWO_HOURS_1_MINUTE);
		assertEquals(10, activity.getmPrice());
	}

}
