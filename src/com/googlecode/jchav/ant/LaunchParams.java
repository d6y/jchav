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
package com.googlecode.jchav.ant;

import java.io.File;
import java.util.Set;


/**
 * Holder for the JChav arguments passed to the controller.
 *
 * @author $LastChangedBy$ 
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class LaunchParams 
{
    /** Source files to process. */
    private Set<File> srcFiles;
    
    /** Target directory for resulting html/images. */
    private String destdir;
  
    /** Target directory for resulting html/images. */
    private String reportTitle;
    
    /** Should the y-axis be the same for all charts? */
    private boolean uniformYAxis = true;    
    
    /** Simple constuctor.
     */
    public LaunchParams()
    {
    }
    
    /** 
     * Simple constructor for directories.
     * 
     * @param srcFiles The set of source files.
     * @param destdir destination location.
     */
    public LaunchParams(Set<File> srcFiles, String destdir)
    {
        this.srcFiles = srcFiles;
        this.destdir = destdir;
    }
    
    /** Simple constuctor for directories and title.
     * 
     * @param srcFiles The set of source files.
     * @param destdir destination location.
     * @param reportTitle title for the report.
     */
    public LaunchParams(Set<File> srcFiles, String destdir, String reportTitle)
    {
        this.srcFiles = srcFiles;
        this.destdir = destdir;
        this.reportTitle = reportTitle;
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
     * @return Returns the set of source files.
     */
    public final Set<File> getSrcFiles()
    {
        return srcFiles;
    }

    /**
     * @param srcFiles The set of source files.
     */
    public final void setSrcFiles(Set<File> srcFiles)
    {
        this.srcFiles = srcFiles;
    }

    /**
     * @return true if all charts should have the same y-axis.
     */
    public boolean isUniformYAxis()
    {
        return uniformYAxis;
    }

    /**
     * @param uniformYAxis true if the charts should all have the same y axis.
     */
    public void setUniformYAxis(boolean uniformYAxis)
    {
        this.uniformYAxis = uniformYAxis;
    }
     
}