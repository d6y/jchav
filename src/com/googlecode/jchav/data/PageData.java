/**
 * Apache lic here
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
