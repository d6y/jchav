package com.googlecode.jchav.data;

/**
 * Apache lic here.
 * 
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
