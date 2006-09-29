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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Sax processor to read the JMeter XML v2.1 files as specified in http://jakarta.apache.org/jmeter/usermanual/listeners.html.
 * See 14.6 XML Log Format 2.1
 * 
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public class JMeterXMLSaxHandler extends DefaultHandler
{
    
    /** Logger. */
    private static Logger logger = Logger.getLogger(JMeterXMLSaxHandler.class);

    /** Local format definition.
     * Defaults to log format v2.1, but can be overridden by the constructor.
     */
    private XMLFormatDefinitions formatDefinitions=new JMeter21XMLLabels();
    
    
    /** Current request being processed. */
    private RequestHolder currentRequest;
    
    /** Map of request objects and values. */
    private Map<String, RequestHolder> requestMap=new HashMap<String, RequestHolder>();
    
    /** Holds the oveall request averages etc. */
    private RequestHolder summaryRequestHolder;
    
    /** The identifier for the summary page id. */
    public final static String SUMMARY_PAGE_ID="sumarypage";

    
    /** Create a handler with a defined xml format.
     * 
     * @param formats The XML format definition.
     */
    public JMeterXMLSaxHandler(XMLFormatDefinitions formats)
    {
        this.formatDefinitions=formats;
    }
    
    /** Get an iterator through the labels. 
     * 
     * @return iterator through the labels.
     */
    public Iterator<String> getLabels()
    {
        return requestMap.keySet().iterator();
        
    }
    
    /** Get the request holder for a given label.
     * 
     * @param name label of result.
     * @return The request holder for this.
     */
    public RequestHolder getRequestHolder(String name)
    {
        return requestMap.get(name);
    }
    
    /** (non-Javadoc)
     * @see DefaultHandler#startDocument()
     */
    public void startDocument() throws SAXException
    {
        summaryRequestHolder=new RequestHolder();
        summaryRequestHolder.setPageId(SUMMARY_PAGE_ID);
    }
  
    
    /** (non-Javadoc)
     * @see DefaultHandler#startElement()
     */
    public void startElement(String namespaceUri,
                    String localName,
                    String qualifiedName,
                    Attributes attributes)
            throws SAXException 
            {    
            
        
                // look for our tag
                if(qualifiedName.equalsIgnoreCase(formatDefinitions.getSampleTagName()))
                {
                    String labelName=attributes.getValue(formatDefinitions.getLabelAttributeName());
                    // check if we already have a request for this stuff
                    currentRequest=requestMap.get(labelName);
                    if(currentRequest==null)
                    {
                        currentRequest=new RequestHolder();
                        currentRequest.setPageId(labelName);
                        requestMap.put(labelName, currentRequest);
                    }
                    
                    // add time spent
                    String timeSpent=attributes.getValue(formatDefinitions.getElapsedTimeAttributeName());
                    
                    // try conversion
                    try
                    {
                        if(logger.isDebugEnabled())
                        {
                            logger.debug("Adding raw performance for "+labelName+" val "+timeSpent);
                        }
                        // add to current page
                        long requestTime=Long.parseLong(timeSpent);
                        currentRequest.addResult(requestTime);
                        
                        // add to overall averages
                        summaryRequestHolder.addResult(requestTime);
                    }
                    catch(NumberFormatException nfe)
                    {
                      logger.error("Invalid time in xml "+nfe);
                    }
                }
            }

    /** Get the overall page average values.
     * @return Returns the summaryRequestHolder.
     */
    public final RequestHolder getSummaryRequestHolder()
    {
        return summaryRequestHolder;
    }
}
