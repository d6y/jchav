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
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.jchav.TestData;
import com.googlecode.jchav.data.Measurement;

/**
 * Test of creating a chart. 
 * 
 * This test uses a byte[] comparison to ensure that created 
 * charts match expected graph data.  Of course, if we change
 * the layout of a chart, these tests will break, so regenerate
 * the data by running the <code>main()</code> of this class.
 * 
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
         chart.setHeight(400);
         chart.setWidth(600);
         chart.setThumbnailScale(0.5d);
    }
    
    /**
     * Helper to comapre the contents of a test data file and a
     *  byte array, failing if they do not exactly match.
     *  
     * @param filename the chart test data file to compare against.
     * @param actual the actualy bytes created.
     * 
     * @throws IOException if there was a problem reading the data.
     */
    private void assertEquals(final String filename, final ByteArrayOutputStream actual)
        throws IOException
    {
        File file = TestData.getTestDataFile("chartoutput", filename);
        assertTrue("Missing file: "+filename, file.exists());
        
        // Read the file:
        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(file);
        int bufsize = 1024;
        byte[] buffer = new byte[bufsize];
        int n = 0;
        while ( (n=in.read(buffer)) != -1)
        {
            expected.write(buffer,0,n);
        }
        in.close();
        expected.close();
       
        // Compare the bytes:
        byte[] actualBytes = actual.toByteArray();
        byte[] expectedBytes = expected.toByteArray();
        
        assertEquals(expectedBytes.length, actualBytes.length);
        
        for(int i=0; i<expectedBytes.length; i++)
        {
            assertEquals("Bytes differ at position "+i, expectedBytes[i], actualBytes[i]);
        }
        
    }
    
    /**
     * Test that we can create a PNG graph from the Dummy data.
     * 
     * @throws IOException if the test fails unexpectedly.
     */
    @Test public void canCreateAChart() throws IOException
    {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        chart.write(out);
        out.close();
       
        assertEquals("summary.png", out);  
    }
    

    /**
     * Test of creating a thumbnail.
     * 
     * @throws IOException if the test fails unexpectedly.
     */
    @Test public void canCreateThumbnail() throws IOException
    {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        chart.writeThumbnail(out);
        out.close();
       
        assertEquals("summary_thumb.png", out);
    }
    
    /**
     * Create an example thumbnail and full-size chart to
     * use as input to these unit tests.
     * 
     * @param args ignored.
     * 
     * @throws IOException if it was not possible to create the charts.
     */
    public static void main(final String[] args) throws IOException
    {
       
        MinMeanMaxChartTest test = new MinMeanMaxChartTest();
        test.setUp();
        
        File outDir = TestData.getTestDataFile("chartoutput");
       
        final FileOutputStream fullsize = new FileOutputStream(new File(outDir, "summary.png"));
        test.chart.write(fullsize);
        fullsize.close();
        
        final FileOutputStream thumb = new FileOutputStream(new File(outDir, "summary_thumb.png"));
        test.chart.writeThumbnail(thumb);
        thumb.close();
        
        System.out.println("Test data created in: "+outDir.getAbsolutePath());
        
    }
    
    
}
