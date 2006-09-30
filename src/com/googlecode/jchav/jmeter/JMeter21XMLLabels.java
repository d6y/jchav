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

/**
 * Definition of the XML names for Jmeter 2.1.
 * Reference is http://jakarta.apache.org/jmeter/usermanual/listeners.html 14.6 XML Log format 2.1.
 * 
 * Tags look like this 
 * &lt;httpSample t="1392" lt="351" ts="1144371014619" s="true" lb="HTTP Request" 
 * rc="200" rm="OK" tn="Listen 1-1" dt="text" de="iso-8859-1" by="12407"&gt;
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class JMeter21XMLLabels implements XMLFormatDefinitions
{

    /** Sample tag name. */
    private static  final String SAMPLE_TAG_NAME="httpSample";
    
    /** Elapsed time attribute name. */
    private static final String ELAPSED_TIME_ATTRIBUTE_NAME="t";
    
    /** Label attribute name. */
    private static final String LABEL_ATTRIBUTE_NAME="lb";

    /** {@inheritDoc}*/
    public String getElapsedTimeAttributeName()
    {
       return ELAPSED_TIME_ATTRIBUTE_NAME;
    }

    /** {@inheritDoc} */
    public String getLabelAttributeName()
    {
        return LABEL_ATTRIBUTE_NAME;
    }

    /** {@inheritDoc}*/
    public String getSampleTagName()
    {
        return SAMPLE_TAG_NAME;
    }

}
