/**
 * Copyright 2006-2007 Paul Goulbourn, Richard Dallaway, Gareth Floodgate
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

import com.googlecode.jchav.data.PageData;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Sax processor to read the JMeter XML v2.1 files as specified in http://jakarta.apache.org/jmeter/usermanual/listeners.html.
 * See 14.6 XML Log Format 2.1
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class JMeterXMLSaxHandler extends DefaultHandler
{

    /** Logger. */
    private static Logger logger = Logger.getLogger(JMeterXMLSaxHandler.class);

    /** Local format definition.
     * Defaults to log format v2.1, but can be overridden by the constructor.
     */
    private XMLFormatDefinitions formatDefinitions = new JMeter21XMLLabels();

    /** Current request being processed. */
    private RequestHolder currentRequest;

    /** Map of request objects and values. */
    private Map<String, RequestHolder> requestMap = new HashMap<String, RequestHolder>();

    /** Holds the oveall request averages etc. */
    private RequestHolder summaryRequestHolder;

    /** Create a handler with a defined xml format.
     *
     * @param formats The XML format definition.
     */
    public JMeterXMLSaxHandler(XMLFormatDefinitions formats)
    {
        this.formatDefinitions = formats;
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

    /**
     * {@inheritDoc}
     */
    public void startDocument() throws SAXException
    {
        summaryRequestHolder = new RequestHolder();
        summaryRequestHolder.setPageId(PageData.SUMMARY_PAGE_ID);
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(String namespaceUri, String localName, String qualifiedName, Attributes attributes) throws SAXException
    {

        // look for our tag
        //if (qualifiedName.equalsIgnoreCase(formatDefinitions.getSampleTagNames()))
        if (formatDefinitions.getSampleTagNames().contains(qualifiedName))
        {
            String labelName = attributes.getValue(formatDefinitions.getLabelAttributeName());

			String simpleLabel = simplifyLabelName(labelName);

			labelName = PageIdProcessorImpl.processPageId(simpleLabel);
			String pageTitle = PageIdProcessorImpl.humanReadableTitle(simpleLabel);

			// check if we already have a request for this stuff
            currentRequest = requestMap.get(labelName);
            if (currentRequest == null)
            {
                currentRequest = new RequestHolder();
                currentRequest.setPageId(labelName);
				currentRequest.setPageTitle(pageTitle);
				requestMap.put(labelName, currentRequest);
            }

            // add return code/is Good
            String returnCode=attributes.getValue(formatDefinitions.getReturnCodeName());
            try
            {
                if(returnCode!=null)
                {
                    int retVal=Integer.parseInt(returnCode);
                    currentRequest.setReturnCode(retVal);
                }
                else
                {
                    logger.error("Error on reading return code flag "+returnCode);
                }
            }
            catch(NumberFormatException ne)
            {
                logger .error("Read non numeric as return code for a page >"+returnCode+"<");
            }

            // add time spent
            String timeSpent = attributes.getValue(formatDefinitions.getElapsedTimeAttributeName());

            // try conversion
            try
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Adding raw performance for " + labelName + " val " + timeSpent);
                }
                // add to current page
                long requestTime = Long.parseLong(timeSpent);
                currentRequest.addResult(requestTime);

                // add to overall averages
                summaryRequestHolder.addResult(requestTime);
            }
            catch (NumberFormatException nfe)
            {
                logger.error("Invalid time in xml " + nfe);
            }
        }
    }

    /** Process the label name down to something simple.
     * i.e. remove any params passed to the request.
     * This is a future expansion point to be specific about what we are to remove.
     * eg just JSessionId etc etc.
     *
     * @param labelName pageId that potentially includes Get parameters.
     *
     * @return truncated version of label name with no params.
     */
    public static String simplifyLabelName(String labelName)
    {

            int argStart=labelName.indexOf(";");
            if(argStart>0)
            {
                return labelName.substring(0,argStart);
            }
            else
            {
                return labelName;
            }



        // deal with any parameters passed.


        // process the strings into something readable and safe for the disk
	}


	/** Get the overall page average values.
     * @return Returns the summaryRequestHolder.
     */
    public final RequestHolder getSummaryRequestHolder()
    {
        return summaryRequestHolder;
    }
}
