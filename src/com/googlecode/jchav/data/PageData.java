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

import java.util.SortedSet;

/**
 * Interface to the data source provided from JMeter detailing the performance characteristics of a given request.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public interface PageData
{
    

    /** The identifier for the summary page id. */
    public static final String SUMMARY_PAGE_ID="summarypage";

    /** Get the ordered list of measurements for a given pageId.
     * 
     * @param pageId The unique page name from the getPages iterator.
     * @return Ordered list of measurements. Ordered in X axis order (left to right).
     */
    SortedSet <Measurement> getMeasurements(String pageId);
    
    
        
    /** Get an iterator over the ordered set of page ids.
     * @return Iterator over the ordered set of page ids.
     */
    Iterable<String> getPageIds();
    
    /** Add a measurement to the structure.
     * 
     * @param pageId the pageId to add for,
     * @param measurement The measurement.
     */
    void addMeasurement(String pageId,Measurement measurement);
    
}
