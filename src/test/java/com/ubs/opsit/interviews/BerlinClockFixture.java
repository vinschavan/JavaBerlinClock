package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing
 * stories. You should not need to edit this class to complete the exercise,
 * this is your definition of done.
 */
public class BerlinClockFixture {

	private TimeConverter berlinClock;
	private String theTime;

	private static final String MIDNIGHT_CLOCK = "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO";
	private static final String MIDNIGHT_CLOCK_24 = "Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO";
	private static final String JUST_BEFORE_MIDNIGHT_CLOCK = "Y\nRROO\nRROO\nOOOOOOOOOOO\nOOOO";
	private static final String MIDDLE_OF_AFTERNOON_CLOCK = "Y\nRROO\nRROO\nYYROOOOOOOO\nYYOO";
	private static final String MIDNIGHT = "00:00:00";
	private static final String MIDNIGHT_24 = "24:00:00";
	private static final String JUST_BEFORE_MIDNIGHT = "23:59:59";
	private static final String MIDDLE_OF_AFTERNOON = "13:17:01";

	@Test
	public void berlinClockAcceptanceTests() throws Exception {
		aBehaviouralTestRunner().usingStepsFrom(this)
				.withStory("berlin-clock.story").run();
	}

	@When("the time is $time")
	public void whenTheTimeIs(String time) {
		theTime = time;
	}

	@Then("the clock should look like $")
	public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
		assertThat(berlinClock.convertTime(theTime)).isEqualTo(
				theExpectedBerlinClockOutput);
	}

	/**
	 * Initialize resources
	 */
	@Before
	public void init() {
		berlinClock = new BerlinClockImp();
	}

	/**
	 * Cleaning all the resources
	 */
	@After
	public void cleanup() {
		berlinClock = null;
	}

	/**
	 * test for Midnight with value 00:00:00
	 * 
	 * @throws Exception
	 */
	@Test
	public void berlinMidnightClockTest() throws Exception {
		String berlinMidnight = berlinClock.convertTime(MIDNIGHT);
		assertEquals(MIDNIGHT_CLOCK, berlinMidnight);
	}

	/**
	 * test for Midnight with value 24:00:00
	 * 
	 * @throws Exception
	 */
	@Test
	public void berlinMidnight24ClockTest() throws Exception {
		String berlinMidnight24 = berlinClock.convertTime(MIDNIGHT_24);
		assertEquals(MIDNIGHT_CLOCK_24, berlinMidnight24);
	}

	/**
	 * test time which is just before Midnight with value 23:59:59
	 * 
	 * @throws Exception
	 */
	@Test
	public void berlinBeforeMidnightClockTest() throws Exception {
		String berlinBeforeMidnight = berlinClock
				.convertTime(JUST_BEFORE_MIDNIGHT);
		assertEquals(JUST_BEFORE_MIDNIGHT_CLOCK, berlinBeforeMidnight);
	}

	/**
	 * test afternoon time with value 13:17:01
	 * 
	 * @throws Exception
	 */
	@Test
	public void berlinAfternoonClockTest() throws Exception {
		String berlinAfternoon = berlinClock.convertTime(MIDDLE_OF_AFTERNOON);
		assertEquals(MIDDLE_OF_AFTERNOON_CLOCK, berlinAfternoon);
	}
}
