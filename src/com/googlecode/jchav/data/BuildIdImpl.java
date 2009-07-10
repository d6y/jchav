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
 * Concrete immutable implementation of the build id class.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
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
    public BuildIdImpl(final String buildName, final int buildOrder)
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buildName == null) ? 0 : buildName.hashCode());
        result = prime * result + buildOrder;
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof BuildIdImpl))
        {
            return false;
        }
        
        BuildIdImpl that = (BuildIdImpl)obj;
        return that != null && this.buildName.equals(that.buildName) && this.buildOrder == that.buildOrder;
       
    }
    
    
    
}
