package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.opsit.interviews.exception.InvalidTimeProvidedException;
import com.ubs.opsit.interviews.exception.TimeNotProvidedException;

public class BerlinClockImp implements TimeConverter {

	private static final Logger LOG = LoggerFactory
			.getLogger(BerlinClockImp.class);
	private static final CharSequence NEW_LINE_OPERATOR = "\n";
	private int hours, minutes, seconds;

	private static final String TIME_NOT_PROVIDED_ERROR = "Time not provided";
	private static final String INVALID_TIME_PROVIDED_ERROR = "Invalid time provided.";
	private static final String NUMERIC_TIME_ERROR = "Time values must be numeric.";

	@Override
	public String convertTime(String aTime) {
		LOG.info("convertTime() Begin..");
		String berlinTime = null;
		try {
			hours = 0;
			minutes = 0;
			seconds = 0;
			validateTime(aTime);
			LOG.info("Validation of time success");
			berlinTime = processGivenTime();
			LOG.info("Process to convert into Berlin time gets completed.");
			LOG.debug("Time - " + aTime + " convert into Berling time - \n"
					+ berlinTime);
		} catch (TimeNotProvidedException | InvalidTimeProvidedException e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.info("convertTime() Ends..");
		return berlinTime;
	}

	/**
	 * Validate the time along with the format. If provided time has an issues
	 * then throwing the respective exception with proper error message.
	 * 
	 * @param aTime
	 * @throws TimeNotProvidedException
	 * @throws InvalidTimeProvidedException
	 */
	private void validateTime(String aTime) throws TimeNotProvidedException,
			InvalidTimeProvidedException {
		LOG.info("validateTime() Begin..");
		if (aTime == null)
			throw new TimeNotProvidedException(TIME_NOT_PROVIDED_ERROR);

		String[] times = aTime.split(":", 3);

		if (times.length != 3)
			throw new InvalidTimeProvidedException(INVALID_TIME_PROVIDED_ERROR);

		try {
			hours = Integer.parseInt(times[0]);
			minutes = Integer.parseInt(times[1]);
			seconds = Integer.parseInt(times[2]);
		} catch (NumberFormatException e) {
			throw new InvalidTimeProvidedException(NUMERIC_TIME_ERROR);
		}

		if (hours == 24 && (seconds > 0 | minutes > 0))
			throw new InvalidTimeProvidedException("Time greater than 24 hours");
		if (hours < 0 || hours > 24)
			throw new InvalidTimeProvidedException("Hours out of bounds.");
		if (minutes < 0 || minutes > 59)
			throw new InvalidTimeProvidedException("Minutes out of bounds.");
		if (seconds < 0 || seconds > 59)
			throw new InvalidTimeProvidedException("Seconds out of bounds.");
		LOG.info("validateTime() Ends..");
	}

	/**
	 * Convert individual hours, minutes and seconds into a Berlin Time.
	 * 
	 * @return BerlinTime
	 */
	private String processGivenTime() {
		LOG.info("processGivenTime() Begin..");
		String l1 = (seconds % 2 == 0) ? "Y" : "0";
		String l2 = generateRow(hours / 5, 4, "R");
		String l3 = generateRow(hours % 5, 4, "R");
		String l4 = generateRow(minutes / 5, 11, "Y").replaceAll("YYY", "YYR");
		String l5 = generateRow(minutes % 5, 4, "Y");
		LOG.info("processGivenTime() Ends..");
		return String
				.join(NEW_LINE_OPERATOR, Arrays.asList(l1, l2, l3, l4, l5));

	}

	/**
	 * Creates a string for each row of the Berlin clock.
	 * 
	 * @param lightLights
	 * @param lightsInRow
	 * @param lampType
	 * @return A string representing a single row of the clock.
	 */
	private String generateRow(int lightLights, int lightsInRow, String lampType) {
		LOG.info("generateRow() Begin..");
		int unlightLights = lightsInRow - lightLights;
		String light = String.join("",
				Collections.nCopies(lightLights, lampType));
		String unlight = String.join("",
				Collections.nCopies(unlightLights, "0"));
		LOG.info("generateRow() Ends..");
		return light + unlight;
	}
}
