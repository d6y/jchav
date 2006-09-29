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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests on the handler
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class JMeterXMLSaxHandlerTest
{
    /**
     * Test we can deal with a directory full of files.
     */
    @Test public void testTruncation() 
    {
        String labelName="http://secretserver/home/welcome.do;jsessionid=73D43D90900F22BF8A0D3DFB4E17E978.thtp21";
        String newLabel=JMeterXMLSaxHandler.simplifyLabelName(labelName);
        assertEquals(newLabel,"http://secretserver/home/welcome.do");

    }
}
