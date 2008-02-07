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

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;

import com.googlecode.jchav.ant.FileChooser;
import com.googlecode.jchav.data.BuildIdImpl;
import com.googlecode.jchav.data.Measurement;
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
        testDataDir = System.getProperty("jchav.test.data.dir");
        if (testDataDir == null)
        {
            testDataDir = "testdata";
        }
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
        final Set<File> files = FileChooser.corraleSrcFiles(testDataDir);
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(files);
    }
    
    /**
     * Test passing a file instead of a dir is ignored.
     */
    @Test public void testProcessFileAsDirectory() 
    {
        final Set<File> files = new HashSet<File>();
        files.add(new File(testDataDir + File.separator + "Good21Data.xml"));
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(files);
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
    
    
    /**
     * Test we can handle a valid JMeter2.3.1 file.    
     * @throws IOException on unexpected failure.
     */
    @Test public void testGoodJMeter231Format() throws IOException
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        
        InputSource source=new InputSource(new FileReader(testDataDir+File.separator+"Good231Data.xml"));
        jmeterExpander.processXMLFile(new BuildIdImpl("TestBuild",0), source);    
    }
    
    
     /**
     * Test we can handle mixes of sample and httpSample tags.    
     * @throws IOException on unexpected failure.
     */
    @Test public void testMixedSampleFormat() throws IOException
    {
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        
        InputSource source=new InputSource(new FileReader(testDataDir+File.separator+"MixedSampleData.xml"));
        jmeterExpander.processXMLFile(new BuildIdImpl("TestBuild",0), source); 
        
        // confirm that the defined test "Test Dummy JUnit Request" appears
        boolean found=false;
        for(String pageId: jmeterExpander.getPageData().getPageIds())
        {
            System.out.println("Title "+jmeterExpander.getPageData().getPageTitle(pageId));
            
            if("Test Dumy JUnit Request".equals(jmeterExpander.getPageData().getPageTitle(pageId)))
            {
                found=true;
                break;
            }
        }
        if(!found)
        {
            fail("JUnit page not found.");
        }
        
        // check the digg request
        found=false;
        for(String pageId: jmeterExpander.getPageData().getPageIds())
        {
            System.out.println("Title "+jmeterExpander.getPageData().getPageTitle(pageId));
            
            if("Digg root page".equals(jmeterExpander.getPageData().getPageTitle(pageId)))
            {
                found=true;
                break;
            }
        }
        if(!found)
        {
            fail("Digg page not found.");
        }
                 
    }
    
    /** Test that when a directory full of files is generated that the builds are in order.
     */
    @Test public void testBuildOrdering() 
    {
        final Set<File> files = FileChooser.corraleSrcFiles(testDataDir);
    
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        jmeterExpander.processAllfiles(files);
        
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
    

    /** We had a bug where a series of ascending or descending times caused max to never move from -MIN_NOS.
      * @throws IOException on unexpected failure.
     */
    @Test public void testDescendingData()  throws IOException
    {
        ExpandJMeterXML jmeterExpander=new ExpandJMeterXML();
        
        InputSource source=new InputSource(new FileReader(testDataDir+File.separator+"IncData.xml"));
        jmeterExpander.processXMLFile(new BuildIdImpl("TestBuild",0), source);    

        PageData data=jmeterExpander.getPageData();
        
        SortedSet<Measurement> measurements=data.getMeasurements("DTC+Observed+CSV+Generated");
        
        for(Measurement measurement : measurements)
        {

            if(measurement.getMaximumTime()==Long.MIN_VALUE)
            {
                fail("Max value not updated.");
            }
            
        }
        
        
        
        
    }
    
}
