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

import java.io.IOException;
import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/**
 * Provides the control and co-ordination of the JChav process to
 * produce a set of charts for a given set of data.
 *
 * @author $LastChangedBy$ 
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class JChavTask extends Task
{
	
	/**   */
	private final ArrayList<FileSet> fileSets = new ArrayList<FileSet>();
	
	
    /** Source dir for files to process. 
     * NB all lowercase because of ANT conventions. 
     * @deprecated Will be removed in the next version */
    private String srcdir;
    
    /** Target directory for resulting html/images. 
     *  NB all lowercase because of ANT conventions. */
    private String destdir;
  
    /** Target directory for resulting html/images. 
     *  NB all lowercase because of ANT conventions. */
    private String reporttitle;
     
    /** Do we want all the charts to have the same y-axis (computed
     * from the min and max we observe)? 
     *  NB all lowercase because of ANT conventions. */
    private String uniformyaxis;
 
    /** 
     * Perform the task through the Controller object.
     */
    public void execute() 
    {
        LaunchParams taskParams = new LaunchParams();
        
        if(getSrcdir() != null)
        {
            // If the source location is a directory, expand that to get files.
            taskParams.setSrcFiles(FileChooser.corraleSrcFiles(getSrcdir()));
            
            log("Use of srcdir is DEPRECATED, please change to using ant filesets.", Project.MSG_WARN);
        }
        else if (fileSets.size() > 0)
        {
            // If the source location is specified by filesets, then, expand those.
            taskParams.setSrcFiles(FileChooser.corraleSrcFiles(fileSets, getProject()));
        }
        else
        {
            // If there is no source location, do not continue.
            throw new BuildException("Source location not specified, unable to continue.");
        }
        
        
        
        if(getDestdir()==null)
        {
            throw new BuildException("desdir not set.");
        }
        else
        {
            taskParams.setDestdir(getDestdir());
        }
        
        if(getReporttitle()!=null)
        {
            taskParams.setReportTitle(getReporttitle());
        }
        
        if ( getUniformyaxis() != null )
        {
            taskParams.setUniformYAxis("true".equalsIgnoreCase(getUniformyaxis()));
        }
        
            
        Controller controller=new Controller();
        try
        {
            controller.go(taskParams);
        }
        catch (IOException e)
        {
            throw new BuildException("JChav faield "+e.getMessage());
        }
        
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


    /**
     * @return Returns the reporttitle.
     */
    public final String getReporttitle()
    {
        return reporttitle;
    }


    /**
     * @param reporttitle The reporttitle to set.
     */
    public final void setReporttitle(String reporttitle)
    {
        this.reporttitle = reporttitle;
    }


    /**
     * @return true if the charts should all have the same y axis.
     */
    public String getUniformyaxis()
    {
        return uniformyaxis;
    }


    /**
     * @param uniformyaxis true if the charts should all have the same y axis.
     */
    public void setUniformyaxis(String uniformyaxis)
    {
        this.uniformyaxis = uniformyaxis;
    }

    
    /**
     * Add an An <code>FileSet</code> for consideration.
     * This is done during ant task expansion.
     * 
     * @param fileSet The (already expanded) <code>FileSet</code> instance.
     */
    public void addFileSet(final FileSet fileSet)
    {
    	fileSets.add(fileSet);
    }
  
}