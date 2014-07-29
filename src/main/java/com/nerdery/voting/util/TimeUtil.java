package com.nerdery.voting.util;

import java.util.Calendar;

public class TimeUtil {
    /**
     * This will get the current time in milliseconds and subtract the current day's midnight time in
     * milliseconds, and then it will finally divide my one thousand to get seconds.
     * @return the number of seconds until midnight.
     */
    public static int secondsUntilMidnight() {
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return (int) ((c.getTimeInMillis() - now) / 1000L);
    }
}
