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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Draws an indication of the magnitude of the change in response time on a chart.
 *
 * The current implementation is a circle in the top-right corner in various
 * shades of red or green.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class ChangeAlertDecorator implements Decorator
{
    
    /** The size of the change as a ratio. */
    private final double change;
    

    /**
     * Construct a change dectorator for the given change.
     * @param firstValue the first value.
     * @param secondValue the second value.
     */
    public ChangeAlertDecorator(final long firstValue, final long secondValue)
    {
     
        // Compute the change ratio:
        if (firstValue < secondValue)
        {
            change = (double)(secondValue-firstValue) / (double)firstValue;
        }
        else
        {
            change = - (double)(firstValue-secondValue) / (double)secondValue;
            
        }
        
    }

    /**
     * {@inheritDoc}
     */
    public void decorate(Graphics2D g, Chart chart)
    {
        
        int size=30; // how big to make the circle
        int stokeSize=2; // how thick is the mark?
        int margin=10; // space from edge of chart
        
        // place circle on the top right:
        int markX = chart.getWidth() - (size + margin);
        int markY = margin;
        
        // Draw it:
        g.setColor(toColor());
        g.setStroke(new BasicStroke(stokeSize));
        g.fillOval(markX, markY, size, size);
        
        // Outline:
        g.setColor(Color.black);
        g.drawOval(markX, markY, size+stokeSize, size+stokeSize);
        
    }

    /**
     * @return returns the change.
     */
    public double getChange()
    {
        return change;
    }
    
    /**
     * Convert the change into a colour.
     * 
     * @return the colour that represents the change magnitude.
     */
    public Color toColor()
    {
        
        // Map the change value to the 0-255 range.
        int alpha = (int)Math.round( 255d * (Math.atan(change) / (Math.PI/2d)) );
      
       
        if (change > 0d)
        {
            // response time has gone up = :-(
            // TODO: i18n?  red isn't bad (and green isn't good) in all cultures?
            return new Color(255,0,0, alpha);
        }
        else
        {
            // response time has not gone up = :-)
            return new Color(0,255,0, -alpha);
        }
    }
    
}
