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
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.googlecode.jchav.chart.Chart;
import com.googlecode.jchav.chart.ChartNameUtil;
import com.googlecode.jchav.chart.MinMeanMaxChart;
import com.googlecode.jchav.data.PageData;
import com.googlecode.jchav.jmeter.ExpandJMeterXML;


/**
 * Provides the control and co-ordination of the JChav process to
 * produce a set of charts for a given set of data.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class Controller
{

    /** Default image width. */
    private int width = 640;
    /** Default image height. */
    private int height = 480;
    /** Default image thumbnail ratio. */
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
        
        writeSummary(data, outDir); //??
        
    }

    /**
     * Writes the chart both full-sized and thumbnail size to
     * the output directory.
     * 
     * @param pageId id of current page.
     * @param chart the chart to write.
     * @param outDir the directory to write into.
     * 
     * @throws IOException if there was a problem writing the charts.
     */
    private void writeChart(final String pageId, final Chart chart, final File outDir) throws IOException
    {
        
    	final File fullFile = ChartNameUtil.buildChartImagePath(pageId, outDir);
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
        
        final File thumbFile = ChartNameUtil.buildChartThumbnailPath(pageId, outDir);
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
    
    
    
    
    /**
     * Write the summary index page.
     * 
     * 
     * @param pageData The page dta.
     * @param outDir The output directory.
     */
    private void writeSummary(final PageData pageData, final File outDir) throws IOException
    {
    	final File indexFile = new File(outDir, "index.html");

    	Writer output = null;
    	try
    	{
    		output = new OutputStreamWriter(new FileOutputStream(indexFile), "UTF-8");
    		
    		
    		output.write("<html>\n");
    		output.write("<head>\n");
    		output.write("</head>\n");
    		output.write("<body>\n");
    		
	        for(String id: pageData.getPageIds()) 
	        {
	        	// String thiumb img = "";
	        	
	        	output.write("<div class=\"summary\">\n" +
								        			
							"<h2>\n" +
							id +
							"</h2>\n" +
							"<a href=\"" +
							 ChartNameUtil.buildChartImagePath(id, outDir).getName() +
							"\" />\n" +
							"<img src=\"" +
							 ChartNameUtil.buildChartThumbnailPath(id, outDir).getName() +
							"\" />\n" +
							"</a>" +
							"</div>\n"

	        			);
	        	
	        	
	        }
	        
	        
	        
    		output.write("</body>\n");
    		output.write("</html>\n");
	        
		
    	}
    	finally
    	{
    		output.close();
    		output = null;
    	}
    	
    	
    	

    }
    
}
