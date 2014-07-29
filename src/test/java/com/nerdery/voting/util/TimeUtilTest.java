package com.nerdery.voting.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtilTest {
    @Test
    public void makeSureNumberOfSecondsIsEqualToOneDay() throws Exception {
        LocalDateTime start = LocalDate.now().plusDays(1).atStartOfDay();
        LocalDateTime end = LocalDate.now().atStartOfDay();
        Assert.assertEquals(24 * 60 * 60, TimeUtil.getDiffInSeconds(start, end));
    }
}
