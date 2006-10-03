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

/**
 * Test of the PageData implementation
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class PageDataImplTest
{
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(PageDataImplTest.class);
    }
    
    /**
     * Ensure that the first page is always the summary page.
     *
     */
    @Test public void testSummaryIsTheFirstPage()
    {
        BuildIdImpl id = new BuildIdImpl("beta",1);
        
        PageDataImpl data = new PageDataImpl();
        data.addMeasurement("Z Page", new MeasurementImpl(id, 1L,2L,3L));   
        data.addMeasurement(PageData.SUMMARY_PAGE_ID, new MeasurementImpl(id, 1L,2L,3L));
        data.addMeasurement("A Page", new MeasurementImpl(id, 1L,2L,3L));
        
        // Ensure the first item is always summary:
        for(String page : data.getPageIds())
        {
            assertEquals(PageData.SUMMARY_PAGE_ID, page);
            break;
        }
        
 
    }
    
}
