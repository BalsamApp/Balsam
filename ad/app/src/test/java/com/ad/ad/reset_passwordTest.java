package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class reset_passwordTest {

    @Test
    public void reset_Passwoerd_P() {
        reset_password reset_password=new reset_password();
        assertEquals("Password Updated Successfully",reset_password.reset_Passwoerd_P(1313, 0555111222,
                                                                                               "1999-05-05"));

    }
}