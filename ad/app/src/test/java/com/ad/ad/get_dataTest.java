package com.ad.ad;

import org.junit.Test;

import static org.junit.Assert.*;

public class get_dataTest {

    @Test
    public void updateDiseases() {
        get_data get_data=new get_data();
        assertEquals("Diseases Updated Successfully",get_data.updateDiseases(1313, "Stomach ulcer"));
    }

    @Test
    public void updateAllergy() {
        get_data get_data=new get_data();
        assertEquals("Allergy Updated Successfully",get_data.updateAllergy(1313, " milk allergy"));
    }
}