/**
 * Copyright 2006 Paul Goulbourn, Richard Dallaway, Gareth Floodgate
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 * 
 */
package com.googlecode.jchav.chart;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

/**
 * Test of how colours are computed.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class ChangeAlertDecoratorTest
{


    /**
     * Make sure we can draw red for a bad test result.
     *
     */
    @Test public void testCanConvertIncreaseToRed()
    {
        // A doubling of resonse time:
        ChangeAlertDecorator change = new ChangeAlertDecorator(1L,2L);
        
        Color color = change.toColor();
        
        // it's bad, so it's red. 
        assertEquals(255 , color.getRed());
        assertEquals(128 , color.getAlpha());

       
    }
    
    /**
     * Make sure we can draw green for a good result.
     *
     */
    @Test public void testCanConvertDecreaseToGreen()
    {
        // A doubling of resonse time:
        ChangeAlertDecorator change = new ChangeAlertDecorator(2L,1L);
        
        Color color = change.toColor();
        
        // it's a good change, so we show green.         
        assertEquals(255, color.getGreen());
        
        // Not 128, because -ve are rounded towards zero.
        assertEquals(127, color.getAlpha());
        
    }
    
    
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(ChangeAlertDecoratorTest.class);
    }
    
    
}
