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
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.googlecode.jchav.data.BuildId;
import com.googlecode.jchav.data.BuildIdImpl;
import com.googlecode.jchav.data.Measurement;
import com.googlecode.jchav.data.MeasurementImpl;
import com.googlecode.jchav.data.PageData;
import com.googlecode.jchav.data.PageDataImpl;

/**
 * Process an XML file to generate the set of averages for a given request file.
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class ExpandJMeterXML
{
    /** Logger. */
    private static final Logger logger = Logger.getLogger(ExpandJMeterXML.class);

    /** This stores all the averages per page per build. */
    private PageData pageData = new PageDataImpl();

    /**
     * Read each resulting xml file in turn.
     * For each file call processXMLFile to calculate its averages and add them to the build.
     * @param dir Directory used to store all the jmeter files.
     */
    public void processAllfiles(File dir)
    {
        // for each file 
        if (!dir.isDirectory())
        {
            logger.error("Passed file is not a directory.....ignoring.");
            return;
        }

        File[] files = getOrderedFiles(dir);

        for (int l = 0; l < files.length; l++)
        {
            File toProcess = files[l];
            if (toProcess.isFile())
            {
                //process the file
                try
                {
                    InputSource source = new InputSource(new FileReader(toProcess));
                    processXMLFile(new BuildIdImpl(toProcess.getName(), l), source);
                }
                catch (FileNotFoundException e)
                {
                    logger.error("File " + toProcess.getName() + " could not be processed " + e.getMessage());
                }

            }
        }

    }

    /** Get all the files (not dirs) in a directory.
     * 
     * @param dir dir to list.
     * @return array of files in modification date order.
     */
    private File[] getOrderedFiles(File dir)
    {

        //Build a simple comparitor as inner class
        Comparator<File> byModificationDate = new Comparator<File>()
        {
            public int compare(File f1, File f2)
            {
                long diff = f1.lastModified() - f2.lastModified();
                int returnValue;
                if (diff < 0L)
                {
                    returnValue = -1;
                }
                else if (diff > 0L)
                {
                    returnValue = +1;
                }
                else
                {
                    assert diff == 0L;
                    returnValue = 0;
                }
                return returnValue;
            }
        };

        // file filter to reject dirs
        FileFilter noDirFilter = new FileFilter()
        {
            public boolean accept(File f)
            {
                if (f.isDirectory())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        };

        // Filter out the dirs.
        File[] nonDirs = dir.listFiles(noDirFilter);

        //return them sorted
        Arrays.sort(nonDirs, byModificationDate);
        return nonDirs;
    }

    /** Process a single xml file. 
     * Operation as follows :
     * a) Use the JMeterXMLSaxHandler to get all the average values per unique page.
     * b) Add them to the core data structure by the buildid (filename).
     * 
     * @param buildId Unique id for the build.
     * @param source The input source to process.
     */
    public void processXMLFile(BuildId buildId, InputSource source)
    {
        // future expansion point
        // detect xml version here and pass alternate labels instance depending on version
        JMeterXMLSaxHandler contentHandler = new JMeterXMLSaxHandler(new JMeter21XMLLabels());

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser saxParser = factory.newSAXParser();

            XMLReader parser = saxParser.getXMLReader();

            // Register the content handler
            parser.setContentHandler(contentHandler);

            parser.parse(source);

            Iterator<String> iter = contentHandler.getLabels();

            //for each page in this build
            while (iter.hasNext())
            {
                RequestHolder requestAverages = contentHandler.getRequestHolder(iter.next());
                Measurement measurement = new MeasurementImpl(buildId, requestAverages.getAverage(), requestAverages.getMinimum(), requestAverages.getMaximum());

                if (logger.isDebugEnabled())
                {
                    logger.debug("Adding averages values as follows pageId=" + requestAverages.getPageId() + " buildId=" + buildId + " av=" + requestAverages.getAverage() + " min=" + requestAverages.getMinimum() + " max="
                                    + requestAverages.getMaximum());
                }

                // add the average for this page and build to the data set
                pageData.addMeasurement(requestAverages.getPageId(), measurement);
            }

            // add the overall averages as well
            Measurement averageMeasurement = new MeasurementImpl(buildId, contentHandler.getSummaryRequestHolder().getAverage(), contentHandler.getSummaryRequestHolder().getMinimum(), contentHandler.getSummaryRequestHolder().getMaximum());
            pageData.addMeasurement(PageData.SUMMARY_PAGE_ID, averageMeasurement);

        }
        catch (SAXException er)
        {
            logger.error("Unable parse log file " + er.getMessage());
        }
        catch (ParserConfigurationException er)
        {
            logger.error("Unable to configure sax parser " + er.getMessage());
        }
        catch (IOException e)
        {
            logger.error("Unable to read log input source." + e.getMessage());
        }
    }

    /** Allow calling classes to access the page data produced.
     * 
     * @return the page data structure.
     */
    public PageData getPageData()
    {
        return pageData;
    }
}
