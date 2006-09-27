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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.googlecode.jchav.data.Measurement;
import com.googlecode.jchav.data.MeasurementImpl;
import com.googlecode.jchav.data.PageData;


/**
 * Process an XML file to generate the set of averages for a given request file.
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public class ExpandJMeterXML extends DefaultHandler
{
    /** Logger. */
    private static Logger logger = Logger.getLogger(ExpandJMeterXML.class);

    PageData pageData; // do this stuff.....
    
    
    
    public void processAllfiles(String dir)
    {
        // for each file 
        
        // process the file
        
        // get the set of averages for that run 
    }
    
    public void processXMLFile(String buildId, InputSource source)
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
            
            Iterator iter=contentHandler.getLabels();
            
            while(iter.hasNext())
            {
                RequestHolder requestAverages=contentHandler.getRequestHolder((String)iter.next());
                Measurement measurement=new MeasurementImpl(buildId,requestAverages.getAverage(),requestAverages.getMinimum(),requestAverages.getMaximum());
            
                if(logger.isDebugEnabled())
                {
                    logger.debug("Adding averages values as follows buildId="+buildId+" av="+requestAverages.getAverage()+" min="+requestAverages.getMinimum()+" max="+requestAverages.getMaximum());
                }
                
                pageData.addMeasurement(requestAverages.getPageId(), measurement);
            }
            
        }
        catch (SAXException er)
        {
            logger.error("Unable parse log file "+er.getMessage());
        }
        catch (ParserConfigurationException er)
        {
            logger.error("Unable to configure sax parser "+er.getMessage());
        }
        catch (IOException e)
        {
            logger.error("Unable to read log input source."+e.getMessage());
        }
    }
}
