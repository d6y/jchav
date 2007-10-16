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
package com.googlecode.jchav.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * A helper class for dealing with files.
 * 
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-10-03 19:21:47 +0100 (Tue, 03 Oct 2006) $ $LastChangedRevision: 72 $
 */
public class FileUtil
{

	
	/**
	 * Copy an input stream to an output file.
	 * 
	 * @param inputStream a non-null input stream.
	 * @param outputFile the file to write the input to.
	 * 
	 * @throws IOException If there is a problem with the copy.
	 */
	public static void copy(final InputStream inputStream, final File outputFile) throws IOException
	{
		assert inputStream != null;
        assert outputFile != null;
        
        OutputStream output = null;
		try
		{
			output = new FileOutputStream(outputFile);
			
			final byte[] buffer = new byte[8192];
			int count;
			while ((count = inputStream.read(buffer)) > -1)
			{
				output.write(buffer, 0, count);
			}
			
			output.flush();
		}
		finally
		{
			if(output!=null)
            {
                output.close();
            }
		}
	}
	
}
