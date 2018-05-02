package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class pharmacy_LoginTest {

    @Test
    public void testPharmacyLogin_username() {
        int username=1812;
        pharmacy_Login pharmacy_Login=new pharmacy_Login();
        assertEquals(1812,pharmacy_Login.testPharmacyLogin_username(username));
    }

    @Test
    public void testPharmacyLogin_passwoed() {
        int password=1234;
        pharmacy_Login pharmacy_Login=new pharmacy_Login();
        assertEquals(true,pharmacy_Login.testPharmacyLogin_passwoed(password));
    }
}