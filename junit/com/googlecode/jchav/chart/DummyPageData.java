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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.googlecode.jchav.data.BuildIdImpl;
import com.googlecode.jchav.data.Measurement;
import com.googlecode.jchav.data.PageData;

/**
 * Sample page data for use in testing.
 * 
 * Not designed for thread safety.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class DummyPageData implements PageData
{

    /** Page list. */
    private final List<String> pageIdList;
    /** Page data. */
    private final HashMap<String, SortedSet<Measurement>> data;
    
    /**
     * Construct a fixed set of sample data. 
     */
    public DummyPageData()
    {
    
        // A set of pages...
        pageIdList = new ArrayList<String>();
        pageIdList.add("Summary");
        pageIdList.add("Home");
        pageIdList.add("Login");
        pageIdList.add("View Stuff");
        pageIdList.add("Do Things");
        pageIdList.add("Logout");
       
        // ...measurements for each of the pages...
        
        data = new HashMap<String, SortedSet<Measurement>>();
        
        data.put("Summary",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.0",0), 50L, 140L, 150L)
                    .add(new BuildIdImpl("build 1.1",1), 25L, 32L, 40L)
                    .add(new BuildIdImpl("build 1.2",2), 27L, 38L, 50L)
                    .add(new BuildIdImpl("build 1.3",3), 26L, 45L, 60L)
                    .toList() );
        
        data.put("Home",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.0",0), 5L, 100L, 230L)
                    .add(new BuildIdImpl("build 1.1",1), 6L, 80L, 200L)
                    .add(new BuildIdImpl("build 1.2",2), 10L, 30L, 40L)
                    .add(new BuildIdImpl("build 1.3",3), 9L, 28L, 32L)
                    .toList() );
        
        data.put("Login",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.1",0), 5000L, 6000L, 20000L)
                    .add(new BuildIdImpl("build 1.2",1), 5000L, 6000L, 20000L)
                    .add(new BuildIdImpl("build 1.3",2), 400L,  500L, 600L)
                    .toList() );
        
        data.put("View Stuff",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.2",0), 50L, 60L, 62L)
                    .add(new BuildIdImpl("build 1.3",1), 51L, 59L, 61L)
                    .toList() );
        
        data.put("Do Things",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.3",0), 42L, 142L, 242L)
                    .toList() );
    
        data.put("Logout",  
                new MeasurementListBuilder()
                    .add(new BuildIdImpl("build 1.3",0),90L, 300L, 1000L)
                    .toList() );
    
        
    }
    
    /**
     * {@inheritDoc}
     */
    public SortedSet<Measurement> getMeasurements(final String pageId)
    {
        return this.data.get(pageId);
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<String> getPageIds()
    {
        return pageIdList;
    }

    /**
     * {@inheritDoc}
     */
    public void addMeasurement(String pageId, Measurement measurement)
    {
       throw new NotImplementedException();
        
    }

}
