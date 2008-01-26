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

import java.util.SortedSet;
import java.util.TreeSet;

import com.googlecode.jchav.data.BuildId;
import com.googlecode.jchav.data.Measurement;
import com.googlecode.jchav.data.MeasurementImpl;

/**
 * Helper for building up data for testing. Follows
 * the builder pattern.
 *
 * Not thread safe.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class MeasurementListBuilder
{
    
    /** Test set of data. */
    private final SortedSet<Measurement> data = new TreeSet<Measurement>();

    /**
     * Append data to the list.
     * 
     * @param buildId the build id.
     * @param min the minimum measurement.
     * @param mean the average measurement.
     * @param max the maximum mesasurement.
     * 
     * @return this.
     */
    public MeasurementListBuilder add(final BuildId buildId, final long min, final long mean, final long max)
    {
        final MeasurementImpl m = new MeasurementImpl(buildId, mean, min, max);
        
        data.add(m);
        
        return this;
    }

    /**
     * @return this object as a list of <code>Measurement</code>
     *  objects.
     */
    public SortedSet<Measurement> toList()
    {
        return data;
    }
    
}
