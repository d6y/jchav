/**
 * Copyright 2006-2009 Paul Goulbourn, Richard Dallaway, Gareth Floodgate
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

/** Concrete implementation of the measurement class.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class MeasurementImpl implements Measurement,Comparable<Measurement>
{
    /** The builds identifier. */
    private BuildId buildId;
    
    /** The minimum request time in milliseconds. */
    private long minimumTime;
    
    /** The maximum time in milliseconds. */
    private long maximumTime;
    
    /** The average time in milliseconds. */
    private long averageTime;



    /** Flag showing if page processed OK. */
    private boolean pageOK;

    /**
     * Helper constructor.
     * @param buildId Id of build.
     * @param averageTime average time.
     * @param minimumTime minimum time.
     * @param maximumtime maximum time.
     */
    public MeasurementImpl(BuildId buildId, long averageTime, long minimumTime, long maximumtime)
    {
        this.buildId=buildId;
        this.averageTime=averageTime;
        this.minimumTime=minimumTime;
        this.maximumTime=maximumtime;
    }
    
    /** {@inheritDoc}
     */
    public final long getAverageTime()
    {
        return averageTime;
    }

    /** {@inheritDoc}
     */
    public final void setAverageTime(long averageTime)
    {
        this.averageTime = averageTime;
    }

    /**
     *   {@inheritDoc}
     */
    public final BuildId getBuildId()
    {
        return buildId;
    }

    /** {@inheritDoc} */
    public final void setBuildId(BuildId buildId)
    {
        this.buildId = buildId;
    }

    /**{@inheritDoc} */
    public final long getMaximumTime()
    {
        return maximumTime;
    }

    /**{@inheritDoc}*/
    public final void setMaximumTime(long maximumTime)
    {
        this.maximumTime = maximumTime;
    }

    /** {@inheritDoc}*/
    public final long getMinimumTime()
    {
        return minimumTime;
    }

    /** {@inheritDoc}*/
    public final void setMinimumTime(long minimumTime)
    {
        this.minimumTime = minimumTime;
    }

    /**{@inheritDoc}*/
    public void setPageOK(boolean pageOK)
    {
        this.pageOK = pageOK;
    }

    /**{@inheritDoc}*/
    public int compareTo(Measurement o)
    {
        return buildId.getBuildOrder()-o.getBuildId().getBuildOrder();
    }
    
    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof MeasurementImpl))
        {
            return false;
        }
        
        MeasurementImpl that = (MeasurementImpl)obj;
        
        /*
         * We don't have a strong position on what qualifies as two
         * MeasurementImpl classes being equal (ditto for hashcode). 
         * However, for the sake of clarity we define equals here as being consistent
         * with the comapreTo definition above.  This is in line
         * with Effective Java, 2nd Ed, Item 12 (p. 63, 4th bullet point).
         */
        
        return this.buildId.equals(that.buildId);
        
    }

    @Override
    public int hashCode()
    {
        return buildId.hashCode();
    }

    /**{@inheritDoc}*/
    public boolean isPageOK()
    {
        return pageOK;
    }

   
 
    
    
}
