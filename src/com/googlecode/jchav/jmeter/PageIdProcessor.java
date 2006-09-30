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

/**
 * Interface to the pageId processor classes.
 * This class turns the pageId names into a file system safe string.
 * 
 * @author $LastChangedBy: paul.goulbourn $
 * @version $LastChangedDate: 2006-09-30 14:14:12 +0100 (Sat, 30 Sep 2006) $ $LastChangedRevision: 45 $
 */
public interface PageIdProcessor 
{
   /** 
    * Process a pageid.
    * @param initialPageId the initial page name. this may be a URI etc.
    * @return Processed URI into a string presentable to the file system.
    */ 
    String processPageId(String initialPageId);
}
