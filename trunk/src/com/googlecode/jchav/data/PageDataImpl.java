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

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Concrete implementation of the PageData structure.
 * 
 * The structure is not designed to be thread safe, and almost certainly isnt.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class PageDataImpl implements PageData
{
    /** Structure for all the read measurements. */
    private SortedMap<String, SortedSet<Measurement>> allMeasurements=new TreeMap<String, SortedSet<Measurement>>();

    /** Get the ordered list of measurements for a given pageId.
     * 
     * @param pageId The unique page name from the getPages iterator.
     * @return Ordered list of measurements. Ordered in X axis order (left to right).
     */
    public SortedSet <Measurement> getMeasurements(String pageId)
    {
        SortedSet<Measurement> measurements=allMeasurements.get(pageId);
        if(measurements==null) //ensure we do return a list, even if its empty.
        {
            return new TreeSet<Measurement>();
        }
        else
        {
            return measurements;
        }
    }
    
    
        
    /** Get an iterator over the ordered set of page ids.
     * @return Iterator over the ordered set of page ids.
     */
    public Iterable<String> getPageIds()
    {
        return allMeasurements.keySet();
    }
    
    /** Add a measurement to the structure.
     * 
     * @param pageId the pageId to add for,
     * @param measurement The measurement.
     */
    public void addMeasurement(String pageId,Measurement measurement)
    {
        SortedSet<Measurement> measurements=allMeasurements.get(pageId);
        if(measurements==null)
        {
            measurements=new TreeSet<Measurement>();
            measurements.add(measurement);
            allMeasurements.put(pageId,measurements);
        }
        else
        {
            measurements.add(measurement);
        }
    }
    
}
