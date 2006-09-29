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
package com.googlecode.jchav.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.googlecode.jchav.chart.Chart;
import com.googlecode.jchav.chart.MinMeanMaxChart;
import com.googlecode.jchav.data.PageData;
import com.googlecode.jchav.jmeter.ExpandJMeterXML;


/**
 * Provides the control and co-ordination of the JChav process to
 * produce a set of charts for a given set of data.
 *
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class Controller
{

    private int width = 640;
    private int height = 480;
    private double thumbnailScale = 0.5d;
    
   
    
    /**
     * 
     * @param xmlDir the source of the reports.
     * @param outDir the directory to write to.
     * 
     * @throws IOException if there was a problem creating the charts.
     */
    public void go(final File xmlDir, final File outDir) 
        throws IOException
    {
        
        if (outDir.exists() == false)
        {
            if (false == outDir.mkdirs())
            {
                throw new IOException("Failed to create directory: "+outDir.getAbsolutePath());
            }
        }
        
        
        // Read the data:
        ExpandJMeterXML expander = new ExpandJMeterXML();
        expander.processAllfiles(xmlDir);
       
        final PageData data = expander.getPageData();

        
        // Foreach page...
        for(String id: data.getPageIds()) 
        {
            // Create the chart:
            Chart chart = new MinMeanMaxChart(id, data.getMeasurements(id));
            chart.setWidth(width);
            chart.setHeight(height);
            chart.setThumbnailScale(thumbnailScale);
            
            writeChart(id, chart, outDir);
            
        }
        
        // writeSummary(); ??
        
    }

    /**
     * Writes the chart both full-sized and thumbnail size to
     * the output directory.
     * 
     * @param chart the chart to write.
     * @param outDir the directory to write into.
     * 
     * @throws IOException if there was a problem writing the charts.
     * 
     */
    private void writeChart(final String pageId, final Chart chart, final File outDir) throws IOException
    {
        // TODO: pageId is maybe not a good candidate for a file name as
        //       (a) it might contain chars the OS doesn't like; and
        //       (b) it might not be unique (although that's nuts).
        
        final File fullFile = new File(outDir, pageId + ".png");
        final File thumbFile = new File(outDir, pageId +"_thumb.png");
        
        FileOutputStream fullOut = new FileOutputStream(fullFile);
        try
        {
            chart.write(fullOut);
        }
        finally
        {
            fullOut.close();
            fullOut = null;
        }
        
        FileOutputStream thumbOut = new FileOutputStream(thumbFile);
        try
        {
            chart.writeThumbnail(thumbOut);
        }
        finally
        {
            thumbOut.close();
            thumbOut = null;
        }
        
        
    }
    
}
