package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class alarm_activtyTest {

    @Test
    public void modifyAlerts() {
        alarm_activty alarm_activty=new alarm_activty();
        assertEquals("Alerts modified successfully",alarm_activty.modifyAlerts(1310,30, "Fefadol",
                                                                           7, 2, 12, "11:00"));
    }

    @Test
    public void dep_modifyAlerts() {
        alarm_activty alarm_activty=new alarm_activty();
        assertEquals("Alerts modified successfully",alarm_activty.dep_modifyAlerts(1310, 1311,40,
                                                         "Fefadol", 3, 2, 12, "10:00"));
    }
}