package com.nerdery.voting.util;

import com.nerdery.init.PersistenceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.Cookie;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfiguration.class })
public class CookieHelperTest {
    @Autowired
    CookieHelper cookieHelper;

    @Test
    public void makeSureCreateCookieWorksProperly() throws Exception {
        Cookie c = new Cookie("test", "test");
        Cookie cookieForToday = cookieHelper.createCookieForToday("test", "test");
        Assert.assertEquals(c.getValue(), cookieForToday.getValue());
        Assert.assertEquals(TimeUtil.secondsUntilMidnight(), cookieForToday.getMaxAge());
    }

    // TODO should add many more tests.
}
