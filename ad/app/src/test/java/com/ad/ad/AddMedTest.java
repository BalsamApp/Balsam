package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddMedTest {

    @Test
    public void add_med() {
        AddMed addMed=new AddMed();
        assertEquals("Done Successfully",addMed.Add_med(1313, 1412, 30,"Fefadol",
                                                     7,2,12,"Take the medicine after eating"));
    }
}