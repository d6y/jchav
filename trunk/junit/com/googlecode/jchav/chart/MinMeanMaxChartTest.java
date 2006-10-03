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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.SortedSet;

import com.googlecode.jchav.TestData;
import com.googlecode.jchav.data.Measurement;

/**
 * Test of creating a chart.
 * 
 * This is not a unit test, but instead is a main that allows you to produce an
 * example graph for eye-balling.
 * 
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$
 *          $LastChangedRevision$
 */
public class MinMeanMaxChartTest
{

    /** The object under test. */
    private MinMeanMaxChart chart;

    /**
     * Set up common test data.
     */
    public void setUp()
    {
        // A page name we know exists in the dummy data.
        final String pageId = "Summary";

        // Data for the page:
        final DummyPageData pageData = new DummyPageData();
        final SortedSet<Measurement> data = pageData.getMeasurements(pageId);

        // Sanity checks on the test data:
        // assertNotNull(data);
        // assertEquals(4, data.size());

        chart = new MinMeanMaxChart(pageId, data);
        chart.setHeight(400);
        chart.setWidth(600);
        chart.setThumbnailScale(0.5d);
    }

    /**
     * Create an example thumbnail and full-size chart.
     * 
     * @param args
     *            ignored.
     * 
     * @throws IOException
     *             if it was not possible to create the charts.
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

        System.out.println("Test data created in: " + outDir.getAbsolutePath());

    }

}
