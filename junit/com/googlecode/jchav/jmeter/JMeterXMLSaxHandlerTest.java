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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests on the handler.
 *
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class JMeterXMLSaxHandlerTest
{

    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite()
    {
        return new junit.framework.JUnit4TestAdapter(JMeterXMLSaxHandlerTest.class);
    }



	/**
     * Test we can encode IDs into fix-length hashes.
	 */
    @Test public void testTruncation()
    {

		// Example values from http://en.wikipedia.org/wiki/MD5

		String labelName="http://localhost/action;jessionid=12345";
        String newLabel=JMeterXMLSaxHandler.simplifyLabelName(labelName);
        assertEquals("http://localhost/action",newLabel);

    }



}
