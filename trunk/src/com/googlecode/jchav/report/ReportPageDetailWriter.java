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
package com.googlecode.jchav.report;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.SortedSet;

import com.googlecode.jchav.chart.ChartNameUtil;
import com.googlecode.jchav.data.Measurement;


/**
 * TODO
 * 
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-10-03 19:21:47 +0100 (Tue, 03 Oct 2006) $ $LastChangedRevision: 72 $
 */
public class ReportPageDetailWriter
{

	/** The writer to write to. */
	private Writer writer;
	
	
	/** The root folder files are written to. */
	private File rootDir;
	
	
	/**
	 * Create a page detail writer.
	 * 
	 * @param writer The writer to write output to.
	 * @param rootDir The root folder files are written to.
	 * 
	 * @throws IOException If there is a problem writing output.
	 */
	public ReportPageDetailWriter(final Writer writer, final File rootDir) throws IOException
	{
		this.writer = writer;
		this.rootDir = rootDir;
		
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
		writer.write("<head>\n");
		writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		writer.write("<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" media=\"all\" />");
		writer.write("<title>Insert title here</title>");
		writer.write("</head>\n");
		writer.write("<body>\n");		
	}
	
	
	/**
	 * Write the body.
	 * 
	 * @param pageId The page id.
	 * @param measurements The measurements to write.
	 * @throws IOException If there is a problem writing the output.
	 */
	public void write(final String pageId, final SortedSet<Measurement> measurements) throws IOException
	{
    	writer.write("<div class=\"summary\">\n" +
    			
				"<h2>" +
				URLDecoder.decode(pageId, "UTF-8") +
				"</h2>\n" +
				 
				"<img id=\"DetailImage\" src=\"" +
				URLEncoder.encode(ChartNameUtil.buildChartImagePath(pageId, rootDir).getName(), "UTF-8") +
				"\" />\n" +
				"</a>\n" +
				"</div>\n"

			);		

    	
    	writer.write("<table class=\"centre detail\">");
    	
		writer.write("<tr>");
		writer.write("<td>Id</td>");
		writer.write("<td>min time (ms)</td>");
		writer.write("<td>max time (ms)</td>");
		writer.write("<td>Average time (ms)</td>");
		
		writer.write("</tr>");

    	for (Measurement measure : measurements)
    	{
    		writer.write("<tr>");
        		
    		writer.write("<td>");
    		writer.write(measure.getBuildId().getBuildName());
        	writer.write("</td>");
        	
    		writer.write("<td>");
    		writer.write(String.valueOf(measure.getMinimumTime()));
        	writer.write("</td>");
        	
    		writer.write("<td>");
    		writer.write(String.valueOf(measure.getMaximumTime()));
        	writer.write("</td>");
        	
    		writer.write("<td>");
    		writer.write(String.valueOf(measure.getAverageTime()));
        	writer.write("</td>");
        	
    		writer.write("</tr>");
        }
    		
    	writer.write("</table>");
	}
	
	
	/**
	 * Finish the file.
	 * 
	 * @throws IOException If there is a problem writing the output.
	 */
	public void finish() throws IOException
	{
		writer.write("</body>\n");
		writer.write("</html>\n");		
	}
	
}
