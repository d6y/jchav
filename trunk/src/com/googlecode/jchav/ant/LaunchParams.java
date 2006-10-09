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
package com.googlecode.jchav.ant;


/**
 * Holder for the JChav arguments passed to the controller.
 *
 * @author $LastChangedBy$ 
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class LaunchParams 
{
    /** Source dir for files to process. */
    private String srcdir;
    
    /** Target directory for resulting html/images. */
    private String destdir;
  
    /** Target directory for resulting html/images. */
    private String reportTitle;

    /** Simple constuctor.
     */
    public LaunchParams()
    {
    }
    
    /** Simple constuctor for directories.
     * 
     * @param srcdir src location.
     * @param destdir destination location.
     */
    public LaunchParams(String srcdir, String destdir)
    {
        this.srcdir=srcdir;
        this.destdir=destdir;
    }
    
    /** Simple constuctor for directories and title.
     * 
     * @param srcdir src location.
     * @param destdir destination location.
     * @param reportTitle title for the report.
     */
    public LaunchParams(String srcdir, String destdir,String reportTitle)
    {
        this.srcdir=srcdir;
        this.destdir=destdir;
        this.reportTitle=reportTitle;
    }
    
    /**
     * @return Returns the destdir.
     */
    public final String getDestdir()
    {
        return destdir;
    }

    /**
     * @param destdir The destdir to set.
     */
    public final void setDestdir(String destdir)
    {
        this.destdir = destdir;
    }

    /**
     * @return Returns the reportTitle.
     */
    public final String getReportTitle()
    {
        return reportTitle;
    }

    /**
     * @param reportTitle The reportTitle to set.
     */
    public final void setReportTitle(String reportTitle)
    {
        this.reportTitle = reportTitle;
    }

    /**
     * @return Returns the srcdir.
     */
    public final String getSrcdir()
    {
        return srcdir;
    }

    /**
     * @param srcdir The srcdir to set.
     */
    public final void setSrcdir(String srcdir)
    {
        this.srcdir = srcdir;
    }
     
 
   

}