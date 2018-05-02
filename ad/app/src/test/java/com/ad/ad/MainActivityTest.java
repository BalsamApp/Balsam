package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void reset_Passwoerd_Patient() {
        MainActivity MainActivity=new MainActivity();
        assertEquals("Password Updated Successfully",MainActivity.reset_Passwoerd_Patient(1313));

    }
}