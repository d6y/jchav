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
 * Concrete implementation of the build id class.
 * @author $LastChangedBy$
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public class BuildIdImpl implements BuildId
{
    /** The name. */
    private String buildName;
    
    
    /** The order. */
    private int buildOrder;

    
    /** Simple Constructor. 
     * 
     * @param buildName name.
     * @param buildOrder order.
     */
    public BuildIdImpl(String buildName, int buildOrder)
    {
        this.buildName=buildName;
        this.buildOrder=buildOrder;
    }
    
    /** 
     * Get the builds name.
     * @return The builds name.
     */
    public String getBuildName()
    {
        return buildName;
    }
    
    /** Get the build order.
     * 
     * @return The build order in numeric order.
     */
    public int getBuildOrder()
    {
        return buildOrder;
    }
}
