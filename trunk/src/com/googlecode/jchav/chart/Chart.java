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

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * Base class for things charts generally want to do, such as
 * being written to disk.
 *
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate$ $LastChangedRevision: 17 $
 */
public abstract class Chart
{
    /** Default image width. */
    private int width = 600;
   
    /** Default image height. */
    private int height = 400;
    
    /** Default image thumbnail ratio. */
    private double thumbnailScale = 0.5d;
 
    /** The chart itself. */
    protected JFreeChart chart;
    
    /**
     * @param height the height of the full-size chart.
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @param width the width of the full-size chart.
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @param thumbnailScale the scale to use when creating thumbnails via <code>writeThumbnail</code>.
     *  A scale of 0.5d would be half the size of the full-size (<code>width</code>x<code>height</code)
     *  chart.
     */
    public void setThumbnailScale(double thumbnailScale)
    {
        this.thumbnailScale = thumbnailScale;
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
    
    /**
     * Creates a PNG graphic for the given data as a thumbnail image.
     *
     * @param out the stream to write the PNG to.  The caller is responsible
     *  for closing the stream.
     * 
     * @throws IOException if there was a problem creating the chart.
     */
    public void writeThumbnail(final OutputStream out) 
        throws IOException
    {
        
        // Set up the transfomration:
        final AffineTransform xform = new AffineTransform();
        xform.scale(thumbnailScale, thumbnailScale);
        
        // Thanks to the almanac for this one:
        // http://javaalmanac.com/egs/java.awt.image/CreateTxImage.html?l=rel
        final AffineTransformOp op = new AffineTransformOp(xform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        final BufferedImage fullsize = chart.createBufferedImage(width, height);
        final BufferedImage thumbnail = op.filter(fullsize, null /*null means create the image for us*/);

        ChartUtilities.writeBufferedImageAsPNG(out, thumbnail);
        
    }

    
    
    
}
