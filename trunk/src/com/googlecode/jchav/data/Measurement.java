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
 * Interface describing behaviour of a measurement object.
 * This object is used to store the actual values from a measured object.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
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
    BuildId getBuildId();

    /**
     * @param buildId The buildId to set.
     */
    void setBuildId(BuildId buildId);

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