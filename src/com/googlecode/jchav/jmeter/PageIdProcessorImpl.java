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
package com.googlecode.jchav.jmeter;

import java.net.URLEncoder;

/**
 * Implementation of a page processor.
 * 
 * @author $LastChangedBy: paul.goulbourn $
 * @version $LastChangedDate: 2006-09-30 14:14:12 +0100 (Sat, 30 Sep 2006) $ $LastChangedRevision: 45 $
 */
public class PageIdProcessorImpl 
{
   /** 
    * Process a pageid.
    * @param initialPageId the initial page name. this may be a URI etc.
    * @return Processed URI into a string presentable to the file system.
    */ 
    public static String processPageId(String initialPageId)
    {

        // the xml file format moves params to be ;, so we cut on that value
        int argStart=initialPageId.indexOf(";");
        if(argStart>0)
        {
            return URLEncoder.encode(initialPageId.substring(0,argStart));
        }
        else
        {
            return URLEncoder.encode(initialPageId);
        }
        
        // deal with any parameters passed.
        
        
        // process the strings into something readable and safe for the disk
        
    }
}
