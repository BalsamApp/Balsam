package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class reset_passwordPHTest {

    @Test
    public void reset_Passwoerd_PH() {
        reset_passwordPH reset_passwordPH=new reset_passwordPH();
        assertEquals("Password Updated Successfully",reset_passwordPH.reset_Passwoerd_PH(1712,0555111777,
                                                                                                   "1992-09-09"));

    }
}