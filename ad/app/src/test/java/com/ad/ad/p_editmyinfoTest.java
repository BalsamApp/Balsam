package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class p_editmyinfoTest {

    @Test
    public void editinformation_patient() {
        p_editmyinfo p_editmyinfo=new p_editmyinfo();
        assertEquals("Updated Successfully",p_editmyinfo.editinformation_patient(1310, "Maha",0555444333,
                                       "Female","02-07-2002", "Single",
                                       "Jeddah",60,50));
    }
}