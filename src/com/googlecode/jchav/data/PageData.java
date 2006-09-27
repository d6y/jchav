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

import java.util.Iterator;
import java.util.List;

/**
 * Interface to the data source provided from JMeter detailing the performance characteristics of a given request.
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public interface PageData
{

    /** Get the ordered list of measurements for a given pageId.
     * 
     * @param pageId The unique page name from the getPages iterator.
     * @return Ordered list of measurements. Ordered in X axis order (left to right).
     */
    List <Measurement> getMeasurements(String pageId);
    
    
        
    /** Get an iterator over the ordered set of page ids.
     * @return Iterator over the ordered set of page ids.
     */
    Iterator getPageIds();
    
}
