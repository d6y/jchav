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
package com.googlecode.jchav.ant;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


/**
 * Test the controller call.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */

public class ControllerTest
{

    /**
     * Test we can deal with a directory full of files.    
     * @throws IOException on unexpected failure.
     */
    @Test public void testFullDirectoryController() throws IOException
    {
        Controller controller=new Controller(); 
        LaunchParams params=new LaunchParams(System.getProperty("jchav.test.data.dir"),System.getProperty("jchav.test.data.dir")+File.separator+"results");
        controller.go(params);       
    }
    
    
    /**
     * Test tsa data dir.
     * @throws IOException on unexpected failure.
     */
    @Test public void testTSAData() throws IOException
    {
        Controller controller=new Controller();
        LaunchParams params=new LaunchParams(System.getProperty("jchav.test.data.dir")+File.separator+"tsaea",System.getProperty("jchav.test.data.dir")+File.separator+"tsaea"+File.separator+"results","TSAEA report");
        controller.go(params);       
    }
    
    
    /**
     * Test digg data dir.
     * @throws IOException on unexpected failure.
     */
    @Test public void testDiggData() throws IOException
    {
        Controller controller=new Controller();
        LaunchParams params=new LaunchParams(System.getProperty("jchav.test.data.dir")+File.separator+"digwalk",
                        System.getProperty("jchav.test.data.dir")+File.separator+"digwalk"+File.separator+"results","Dig walk");
        controller.go(params);                
    }
    
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(ControllerTest.class);
    }
    
    
}
