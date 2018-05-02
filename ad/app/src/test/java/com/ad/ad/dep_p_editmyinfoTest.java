package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class dep_p_editmyinfoTest {

    @Test
    public void dep_editinformation_patient() {
        dep_p_editmyinfo dep_p_editmyinfo=new dep_p_editmyinfo();
            assertEquals("Updated Successfully",dep_p_editmyinfo.dep_editinformation_patient(1312, 1310, "Maha",
                    0555444333,"Female","02-07-2002", "Single",
                    "Jeddah",60,50));

    }
}