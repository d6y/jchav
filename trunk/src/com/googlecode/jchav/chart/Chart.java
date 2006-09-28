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

import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * Base class for things charts generally want to do, such as
 * being written to disk.
 *
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-09-28 00:55:09 +0100 (Thu, 28 Sep 2006) $ $LastChangedRevision: 17 $
 */
public abstract class Chart
{
    private int width = 600;
    private int height = 400;
 
    protected JFreeChart chart;
    
    /**
     * @param height the height to set.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @param width the width to set.
     */
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    /**
     * Creates a PNG graphic for the given data.
     *
     * @param out the stream to write the PNG to.  The caller is responsible
     *  for closing the stream.
     * 
     * @throws IOException if there was a problem creating the chart.
     */
    public void write(final OutputStream out) 
        throws IOException
    {
        ChartUtilities.writeChartAsPNG(out, chart, width, height);
    }
    
    
}
