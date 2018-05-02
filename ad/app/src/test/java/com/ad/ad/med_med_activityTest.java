package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class med_med_activityTest {

    @Test
    public void actived_med() {
        med_med_activity med_med_activity=new med_med_activity();
        assertEquals("active",med_med_activity.actived_med(1313, 1412,30, "Fefadol",
                7,2,12,"Take the medicine after eating","active"));
    }
}