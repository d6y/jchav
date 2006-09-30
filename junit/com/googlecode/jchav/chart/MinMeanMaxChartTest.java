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
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.jchav.data.Measurement;

/**
 * Test of creating a chart. 
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class MinMeanMaxChartTest
{

    /** The object under test. */
    private MinMeanMaxChart chart;
    
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(MinMeanMaxChartTest.class);
    }
    
    /**
     * Set up common test data.
     */
    @Before public void setUp()
    {
        // A page name we know exists in the dummy data.
        final String pageId = "Summary"; 
        
        // Data for the page:
        final DummyPageData pageData = new DummyPageData();
        final SortedSet<Measurement> data = pageData.getMeasurements(pageId);
        
        // Sanity checks on the test data:
        assertNotNull(data);
        assertEquals(4, data.size());

         chart = new MinMeanMaxChart(pageId, data);
    }
    
    /**
     * Test that we can create a PNG graph from the Dummy data.
     * 
     * @throws IOException if the test fails unexpectedly.
     */
    @Test public void canCreateAChart() throws IOException
    {
        
        final File file = new File("jchav-summary.png");
        final FileOutputStream out = new FileOutputStream(file);
       
        //final ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        chart.setHeight(400);
        chart.setWidth(600);
        chart.write(out);
        
        out.close();
        
        System.out.println(file.getAbsolutePath());
        
    }
    

    /**
     * Test of creating a thumbnail.
     * 
     * @throws IOException if the test fails unexpectedly.
     */
    @Test public void canCreateThumbnail() throws IOException
    {

        final File file = new File("jchav-summary-thumb.png");
        final FileOutputStream out = new FileOutputStream(file);
       
        //final ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        chart.setHeight(400);
        chart.setWidth(600);
        chart.setThumbnailScale(0.5d);
        chart.writeThumbnail(out);
        
        out.close();
        
        System.out.println(file.getAbsolutePath());
        
    }
    
    
}
