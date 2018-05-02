package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class patient_signUpTest {

    @Test
    public void patient_sinup() {
        patient_signUp patient_signUp=new patient_signUp();
        assertEquals("Done Successfully",patient_signUp.patient_sinup(1310, "1234", "Maha",
                                                  0555444333,"Female","02-07-2002", "Single",
                                                  "Jeddah",160, 50,"+A"));
    }

    @Test
    public void dep_patient_sinup() {
        patient_signUp patient_signUp=new patient_signUp();
        assertEquals("Done Successfully",patient_signUp.dep_patient_sinup(1310, 1311, "1234",
                "Ali",0555444333,"Male","01-05-2016", "Single",
                "Jeddah",60, 13,"+A"));
    }
}