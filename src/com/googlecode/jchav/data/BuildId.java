/**
 * Copyright 2006-2009 Paul Goulbourn, Richard Dallaway, Gareth Floodgate
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
 * Interface describing behaviour of the build Id object.
 * This object store the build id, and ordering information.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
  */
public interface BuildId
{
    
    /** 
     * Get the builds name.
     * @return The builds name.
     */
    String getBuildName();
    
    /** Get the build order.
     * 
     * @return The build order in numeric order.
     */
    int getBuildOrder();
    
    

}