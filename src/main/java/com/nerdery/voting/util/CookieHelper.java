package com.nerdery.voting.util;

import com.nerdery.voting.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CookieHelper {

    /**
     * TODO
     * @param request
     * @return
     */
    public boolean hasVotedToday(HttpServletRequest request) {
        return Arrays.asList(request.getCookies()).stream()
                .filter(c -> "lastVote".equals(c.getName()) && LocalDate.now().equals(LocalDate.parse(c.getValue())))
                .collect(Collectors.toList()).size() > 0;
    }

    /**
     * TODO
     * @param request
     * @param results
     */
    public void hasVotedToday(HttpServletRequest request, BindingResult results) {
        if (hasVotedToday(request)) {
            results.addError(new ObjectError("votedToday", "You have already voted today."));
        }
    }

    /**
     * TODO
     * @param key
     * @param value
     * @return
     */
    public Cookie createCookieForToday(String key, String value) {
        Cookie c = new Cookie(key, value);
        c.setMaxAge(TimeUtil.secondsUntilMidnight());
        return c;
    }

    /**
     * TODO
     * @param results
     */
    public void validateWeekday(BindingResult results) {
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY
                || LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            results.addError(new ObjectError("weekend", "Can't perform this action on Saturday or Sunday."));
        }
    }
}
