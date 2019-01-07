package com.globallogic.dashboard.email;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CheckDateUtilTest {

    private CheckDateUtil checkDateUtil = new CheckDateUtil();
    @Test
    public void checkDateValidation() {
        Date current = new Date();
        Date exp = new Date(current.getTime() + TimeUnit.DAYS.toMillis(1));
        Date exp2 = new Date(current.getTime() + TimeUnit.DAYS.toMillis(-1));
        Assert.assertEquals(true,checkDateUtil.checkDateValidation(exp));
        Assert.assertNotEquals(true,checkDateUtil.checkDateValidation(exp2));
        Assert.assertEquals(false,checkDateUtil.checkDateValidation(current));

    }
}