package com.nerdery.voting.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class CookieHelper {

    /**
     * Determines if there is a cookie that has a key lastVote with a value of today's date.
     *
     * @param request The {@link javax.servlet.http.HttpServletRequest} to check all cookies for last vote date.
     * @return If last vote date is today then true otherwise false.
     */
    public boolean hasVotedToday(HttpServletRequest request) {
        for (Cookie c : request.getCookies()) {
            if ("lastVote".equals(c.getName()) && LocalDate.now().toString().equals(c.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if there is a cookie that has a key lastVote with a value of today's date.
     *
     * @param request The {@link javax.servlet.http.HttpServletRequest} to get the cookies from.
     * @param results Add {@link org.springframework.validation.ObjectError} to {@code results} if the {@code request}
     *                has a cookie with the last vote date as today.
     */
    public void hasVotedToday(HttpServletRequest request, BindingResult results) {
        if (hasVotedToday(request)) {
            results.addError(new ObjectError("votedToday", "You have already voted today."));
        }
    }

    /**
     * This will create a cookie with key, {@code key}, and value, {@code value}. It will then
     * set the max age to the number of seconds until midnight.
     *
     * @param key The key of the cookie
     * @param value The value of the cookie
     * @return The {@link Cookie} object based on {@code key}, {@code value}, and the number of
     * seconds until midnight.
     */
    public Cookie createCookieForToday(String key, String value) {
        Cookie c = new Cookie(key, value);
        c.setMaxAge(TimeUtil.secondsUntilMidnight());
        return c;
    }

    /**
     * Will add an error to {@code results} if the current day of the week is Saturday of Sunday.
     *
     * @param results if Monday through Friday nothing, otherwise add {@link org.springframework.validation.ObjectError}
     *                to {@code results}.
     */
    public void validateWeekday(BindingResult results) {
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY
                || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            results.addError(new ObjectError("weekend", "Can't perform this action on Saturday or Sunday."));
        }
    }
}
