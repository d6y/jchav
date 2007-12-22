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

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of a page processor.
 *
 * @author $LastChangedBy: paul.goulbourn $
 * @version $LastChangedDate: 2006-09-30 14:14:12 +0100 (Sat, 30 Sep 2006) $ $LastChangedRevision: 45 $
 */
public class PageIdProcessorImpl
{
    /** For debugging. */
    private static Logger log = Logger.getLogger(PageIdProcessorImpl.class);


	/**
	 * Convert the page ID into something that can be shown to users.
	 *
	 * @param initialPageId the initial page name. this may be a URI etc.
     * @return Processed URI into a string presentable to users.
	 */
	public static String humanReadableTitle(final String initialPageId)
	{
		return initialPageId;
	}

   /**
    * Process a pageid into something that can be stored on the file system.
	*
    * @param initialPageId the initial page name. this may be a URI etc.
    * @return Processed URI into a string presentable to the file system.
    */
    public static String processPageId(final String initialPageId)
    {

        try
        {
            return encode(initialPageId);
        }
		catch (NoSuchAlgorithmException nsax)
		{
			log.error("Unable to encode page", nsax);
			return initialPageId;
		}
		catch (UnsupportedEncodingException e)
        {
            log.error("Unable to encode page name", e);
            return initialPageId;
        }


    }


	/**
	 * Encode a page ID (e.g., URL) into a file-system friendly name.
	 *
	 * This particular implementation is using an MD5 hash.
	 *
	 * @param input the URL to encode.
	 * @return the encoded url.
	 * @throws UnsupportedEncodingException if there was a problem encoding the id.
	 * @throws NoSuchAlgorithmException if there was a problem encoding the id.
	 */
	private static String encode(final String input) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(input.getBytes("UTF-8"));
		return toHexString(md.digest());

	}

	/**
	 * Convert a byte array into a string of hex digits representing those bytes.
	 *
	 * From http://forum.java.sun.com/thread.jspa?threadID=429739&messageID=1921162
	 *
	 * @param bytes bytes to encode.
	 * @return String representing the hex characters for the inoput byte array.
	 */
	  private static String toHexString(final byte[] bytes)
	  {
		int n = bytes.length;
		StringBuilder sb = new StringBuilder(n * 2);
        for (int i = 0; i < n; i++)
		{
             int b = bytes[i] & 0xFF;
             sb.append(HEX_DIGITS.charAt(b >>> 4))
               .append(HEX_DIGITS.charAt(b & 0xF));
        }
        return sb.toString();
    }

	/** The digits used in base-16, required by toHexString() */
	private static final String HEX_DIGITS = "0123456789abcdef";


}
