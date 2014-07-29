package com.nerdery.voting.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {
    /**
     * This will get the current time in milliseconds and subtract the current day's midnight time in
     * milliseconds, and then it will finally divide my one thousand to get seconds.
     * @return the number of seconds until midnight.
     */
    public static int secondsUntilMidnight() {
        return getDiffInSeconds(LocalDateTime.now(), LocalDate.now().plusDays(1).atStartOfDay());
    }

    /**
     * This returns the absolute value between the two dates in seconds.
     *
     * @param start The start date.
     * @param end the end date
     * @return The absolute value of start - end. It will be in seconds.
     */
    public static int getDiffInSeconds(LocalDateTime start, LocalDateTime end) {
        // shouldn't cast...
        return (int) Duration.between(end, start).getSeconds();
    }
}
