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
package com.googlecode.jchav.data;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.googlecode.jchav.chart.DummyPageData;

/**
 * Test of the min and max finding code. 
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class MinMaxTest
{

    /**
     * Check we can find the smallest and largest value in the data set.
     *
     */
    @Test public void testFindsMinAndMax()
    {
        MinMax mm = MinMax.from(new DummyPageData());
        
        assertEquals(5L, mm.getMin());
        assertEquals(20000L, mm.getMax());   
    }
    
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(MinMaxTest.class);
    }

}
