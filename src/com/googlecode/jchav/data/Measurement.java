package com.googlecode.jchav.data;


/**
 * Apache lic here.
 * 
 * 
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public interface Measurement
{

    /**
     * @return Returns the averageTime.
     */
    long getAverageTime();

    /**
     * @param averageTime The averageTime to set.
     */
    void setAverageTime(long averageTime);

    /**
     * @return Returns the buildId.
     */
    String getBuildId();

    /**
     * @param buildId The buildId to set.
     */
    void setBuildId(String buildId);

    /**
     * @return Returns the maximumTime.
     */
    long getMaximumTime();

    /**
     * @param maximumTime The maximumTime to set.
     */
    void setMaximumTime(long maximumTime);

    /**
     * @return Returns the minimumTime.
     */
    long getMinimumTime();

    /**
     * @param minimumTime The minimumTime to set.
     */
    void setMinimumTime(long minimumTime);

}