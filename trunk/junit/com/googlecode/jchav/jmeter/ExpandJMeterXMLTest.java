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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import com.googlecode.jchav.data.BuildIdImpl;
import com.googlecode.jchav.data.PageData;



/**
 * Tests to check the XML parsing capabilites.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class ExpandJMeterXMLTest
{
    /** test dir location. */
    private String testDataDir;
    
    /**
     * Set up paths etc.
     */
    @Before public void setUp()
    {
        testDataDir=System.getProperty("jchav.test.data.dir");
    }
    
    
    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite() 
    {
        return new junit.framework.JUnit4TestAdapter(ExpandJMeterXMLTest.class);
    }
    
    /**
     * Test we can deal with a directory full of files.
     */
    @Test public void testProcessFullDirectory() 
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(new File(testDataDir));
    }
    
    /**
     * Test passing a file instead of a dir is ignored.
     */
    @Test public void testProcessFileAsDirectory() 
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(new File(testDataDir+File.separator+"Good21Data.xml"));
    }
    
    /**
     * Test we can handle a valid XML2.1 file.    
     * @throws IOException on unexpected failure.
     */
    @Test public void testGoodXML21Format() throws IOException
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        
        InputSource source=new InputSource(new FileReader(testDataDir+File.separator+"Good21Data.xml"));
        jmeterExpander.processXMLFile(new BuildIdImpl("TestBuild",0), source);    
    }
    
    /**
     * Test we can handle an invalid XML2.1 file.
     * Test issues error and carries on - I guess this is what we need.
     * @throws IOException on unexpected failure.
     * 
     */
    @Test public void testNoParseXML21Format() throws IOException
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        
        InputSource source=new InputSource(new FileReader(testDataDir+File.separator+"BadParse21Data.xml"));
        jmeterExpander.processXMLFile(new BuildIdImpl("TestBuild",0), source);    
    }
    
    /** Test that when a directory full of files is generated that the builds are in order.
     */
    @Test public void testBuildOrdering() 
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(new File(testDataDir));
        
        PageData pageData=jmeterExpander.getPageData();
        
        Iterable<String> pageIds=pageData.getPageIds();
        
        String oldKey=null;
        for(String page : pageIds)
        {
            if(oldKey==null)
            {
                oldKey=page;
            }
            else
            {
                String newKey=page;
                System.out.println("pageId "+page);
                oldKey=newKey;
            }
        }
        
    }
    
}
