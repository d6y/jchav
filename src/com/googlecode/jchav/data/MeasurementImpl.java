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

/** Concrete implementation of the measurement class.
 * 
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public class MeasurementImpl implements Measurement
{
    /** The builds identifier. */
    private String buildId;
    
    /** The minimum request time in milliseconds. */
    private long minimumTime;
    
    /** The maximum time in milliseconds. */
    private long maximumTime;
    
    /** The average time in milliseconds. */
    private long averageTime;

    /**
     * Helper constructor.
     * @param buildId Id of build.
     * @param averageTime average time.
     * @param minimumTime minimum time.
     * @param maximumtime maximum time.
     */
    public MeasurementImpl(String buildId, long averageTime, long minimumTime, long maximumtime)
    {
        this.buildId=buildId;
        this.averageTime=averageTime;
        this.minimumTime=minimumTime;
        this.maximumTime=maximumtime;
    }
    
    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#getAverageTime()
     */
    public final long getAverageTime()
    {
        return averageTime;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#setAverageTime(long)
     */
    public final void setAverageTime(long averageTime)
    {
        this.averageTime = averageTime;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#getBuildId()
     */
    public final String getBuildId()
    {
        return buildId;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#setBuildId(java.lang.String)
     */
    public final void setBuildId(String buildId)
    {
        this.buildId = buildId;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#getMaximumTime()
     */
    public final long getMaximumTime()
    {
        return maximumTime;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#setMaximumTime(long)
     */
    public final void setMaximumTime(long maximumTime)
    {
        this.maximumTime = maximumTime;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#getMinimumTime()
     */
    public final long getMinimumTime()
    {
        return minimumTime;
    }

    /** (non-Javadoc)
     * @see com.googlecode.jchav.data.Measurement#setMinimumTime(long)
     */
    public final void setMinimumTime(long minimumTime)
    {
        this.minimumTime = minimumTime;
    }
    
}
