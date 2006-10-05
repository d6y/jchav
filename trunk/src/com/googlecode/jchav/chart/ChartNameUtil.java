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
package com.googlecode.jchav.chart;

import java.io.File;


/**
 * A utility class to consistently generate chart image names.
 *
 * @author $LastChangedBy: gareth.floodgate $
 * @version $LastChangedDate: 2006-10-01 23:03:11 +0100 (Sun, 01 Oct 2006) $ $LastChangedRevision: 56 $
 */
public final class ChartNameUtil
{
	
	/**
	 * Build the path for an image.
	 * 
	 * @param page The page name.
	 * @param root The file root.
	 * @return The File object representing the image path.
	 */
	public static File buildChartImagePath(final String page, final File root)
	{
        return new File(root, page + ".png");
    }

	
	/**
	 * Build the path for a thumbnail image.
	 * 
	 * @param page The page name.
	 * @param root The file root.
	 * @return The File object representing the image path.
	 */
	public static File buildChartThumbnailPath(final String page, final File root)
	{
        return new File(root, page + "_thumb.png");
    }
	
}
