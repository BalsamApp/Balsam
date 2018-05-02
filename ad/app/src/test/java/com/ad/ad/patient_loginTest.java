package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class patient_loginTest {

    @Test
    public void testPatientLogin_username() {

        int username=1313;
        patient_login patient_login=new patient_login();
        assertEquals(1313,patient_login.testPatientLogin_username(username));

    }

    @Test
    public void testPatientLogin_passwoed() {

        int password=1234;
        patient_login patient_login=new patient_login();
        assertEquals(true,patient_login.testPatientLogin_passwoed(password));
    }


}