package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorLoginTest {

    @Test
    public void testDoctorLogin_username() {
        int username=1414;
        DoctorLogin DoctorLogin=new DoctorLogin();
        assertEquals(1414,DoctorLogin.testDoctorLogin_username(username));
    }

    @Test
    public void testDoctorLogin_passwoed() {
        int password=1234;
        DoctorLogin DoctorLogin=new DoctorLogin();
        assertEquals(true,DoctorLogin.testDoctorLogin_passwoed(password));
    }
}