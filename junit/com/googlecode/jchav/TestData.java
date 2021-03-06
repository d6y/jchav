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
package com.googlecode.jchav;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Helper for locating test data in the project.
 *
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-10-03 19:21:47 +0100 (Tue, 03 Oct 2006) $ $LastChangedRevision: 72 $
 */
public class TestData
{
    private static Logger log = Logger.getLogger(TestData.class);

    /**
     * Locate the <code>testdata</code> directory.
     * 
     * @return the directory containing the test data for this project.
     */
    public static File getTestDirRoot()
    {
        final File result;
        
        String explicitDir = System.getProperty("jchav.test.data.dir");
        if (explicitDir == null)
        {
            // Try to guess the location:
            result = new File("./testdata");
            log.warn("Guessing testdata location as: "+result +" - if that's" +
                    " wrong see docs/testing.txt and then set -Djchav.test.data.dir");
        }
        else
        {
            result = new File(explicitDir);
        }
        
        if (result.exists() == false)
        {
            log.error("Unable to locate test data, expected directory does not exist: "+result.getAbsolutePath());
        }  
        
        return result;
    }
    
    /**
     * Helper to build up a <code>File</code> from a series of file parts.
     * 
     * @param parts the directory and file names to turn into a <code>File</code>.
     * @return a <code>File</code> in the test data directory.
     */
    public static File getTestDataFile(final String... parts)
    {
        File result = getTestDirRoot();
        
        for(String part : parts)
        {
            result = new File(result, part);
        }
        
        return result;
    }
    
}
