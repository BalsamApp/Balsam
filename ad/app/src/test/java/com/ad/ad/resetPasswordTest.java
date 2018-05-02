package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class resetPasswordTest {

    @Test
    public void reset_Passwoerd_D() {
        resetPassword resetPassword=new resetPassword();
        assertEquals("Password Updated Successfully",resetPassword.reset_Passwoerd_D(1412, 0555111000,
                                                                                              "1990-01-01"));

    }
}