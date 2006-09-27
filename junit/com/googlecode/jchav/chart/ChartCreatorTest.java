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
import java.util.List;

import org.junit.Test;

import com.googlecode.jchav.data.Measurement;

/**
 * Test of creating a graph. 
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class ChartCreatorTest
{

    /**
     * Test that creates a PNG graph from
     * the Dummy data.
     * 
     * @throws IOException if the test fails unexpectedly.
     */
    @Test public void canCreateAChart() throws IOException
    {
        // A page name we know exists in the dummy data.
        final String pageId = "Summary"; 
        
        final DummyPageData pageData = new DummyPageData();
        final List<Measurement> data = pageData.getMeasurements(pageId);
        
        // Sanity checks on the test data:
        assertNotNull(data);
        assertEquals(4, data.size());
        
        final File file = new File("jchav-sumary.png");
        final FileOutputStream out = new FileOutputStream(file);
        //final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartCreator.write(pageId, data, out);
        
        System.out.println(file.getAbsolutePath());
        
    }
    
}
