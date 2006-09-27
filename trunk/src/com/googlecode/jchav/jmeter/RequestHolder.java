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
package com.googlecode.jchav.jmeter;

/**
 * Data holder for a single request.
 * 
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public class RequestHolder
{
    /** Page Id. */
    private String pageId;
    
    /** Number of requests. */
    private int count=0;
    
    /** Total time of all requests. */
    private long sum=0;
    
    /** Minimum value. */
    private long minimum=Long.MAX_VALUE;
    
    /** Maximum value. */
    private long maximum=Long.MIN_VALUE;
    
    /** Add a given result to the total. */
    public void addResult(long time)
    {
        count++;
        sum=sum+time;
        if(time<minimum)
        {
            minimum=time;
        }
        else if(time>maximum)
        {
            maximum=time;
        }
            
    }
    
    /** Get the average value. 
     * If no results present then return 0;
     * @return average value, or 0 if no results set.
     */
    public long getAverage()
    {
        if(count==0)
        {
            return 0;
        }
        else
        {
            return sum/count;
        }
    }
    
    /** Get the smallest request.
     * 
     * @return smallest value.
     */
    public long getMinimum()
    {
        return minimum;
    }
    
    /** Get the maximum value.
     * 
     * @return maximum value.
     */
    public long getMaximum()
    {
        return maximum;
    }

    /**
     * @return Returns the pageId.
     */
    public final String getPageId()
    {
        return pageId;
    }

    /**
     * @param pageId The pageId to set.
     */
    public final void setPageId(String pageId)
    {
        this.pageId = pageId;
    }
    
}
