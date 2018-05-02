package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNewPrescTest {

    @Test
    public void createPrescription() {
        CreateNewPresc createNewPresc=new CreateNewPresc();
        assertEquals("Prescription Created Successfully",createNewPresc.createPrescription(1313, 1412,
                                                                           "2018-05-05", 7));
    }
}