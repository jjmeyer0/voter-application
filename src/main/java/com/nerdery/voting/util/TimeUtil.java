package com.nerdery.voting.util;

import java.util.Calendar;

public class TimeUtil {
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
