package com.googlecode.jchav.jmeter;
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

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test of processing pade IDs into file system friendly IDs.
 *
 * @author $LastChangedBy: paul.goulbourn $
 * @version $LastChangedDate: 2006-10-19 09:52:12 +0100 (Thu, 19 Oct 2006) $ $LastChangedRevision: 124 $
 */
public class PageIdProcessorImplTest
{


	/**
     * Test we can encode IDs into fix-length hashes.
	 */
    @Test
	public void testEncoding()
    {

		// Example values from http://en.wikipedia.org/wiki/MD5

		String labelName="The quick brown fox jumps over the lazy dog";
        String newLabel=PageIdProcessorImpl.processPageId(labelName);
        assertEquals("9e107d9d372bb6826bd81d3542a419d6",newLabel);

    }



    /**
     * For v4 junit tests to run through ant we currently need the adapter.
     * @return suite of tests.
     */
    public static junit.framework.Test suite()
    {
        return new junit.framework.JUnit4TestAdapter(PageIdProcessorImplTest.class);
    }

}
