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
package com.googlecode.jchav.data;

/**
 * Container for the largest and smallest values in the dataset. 
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class MinMax
{
    /** The smallest value seen. */
    private final long min;
    
    /** The largest value. */
    private final long max;
    
    /**
     * @param min the smallest value.
     * @param max the largest value.
     */
    public MinMax(long min, long max)
    {
        super();
        
        assert min <= max : "Min larger than max.  Check parameter order in constructor";
        
        this.min = min;
        this.max = max;
    }



    /**
     * Utility for finding the largest and smallest value across
     * the whole dataset.
     * 
     * @param data the data to process.
     * 
     * @return the min and max values found in the <code>data</code>.
     */
    public static MinMax from(final PageData data)
    {
    
        // TODO: does the summary page always give us the largest max and the 
        // smalest min?  If so... we can just look there...
        
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(String pageId : data.getPageIds())
        {
            for(Measurement m : data.getMeasurements(pageId))
            {
                if (m.getMaximumTime() > max)
                {
                    max = m.getMaximumTime();
                }
                
                if (m.getMinimumTime() < min)
                {
                    min = m.getMinimumTime();
                }
                
            }
            
        }
        
        return new MinMax(min,max);
        
    }



    /**
     * @return returns the max.
     */
    public long getMax()
    {
        return max;
    }



    /**
     * @return returns the min.
     */
    public long getMin()
    {
        return min;
    }
}
