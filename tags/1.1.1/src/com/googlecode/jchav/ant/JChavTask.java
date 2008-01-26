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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Provides the control and co-ordination of the JChav process to
 * produce a set of charts for a given set of data.
 *
 * @author $LastChangedBy$ 
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class JChavTask extends Task
{
    /** Source dir for files to process. 
     * NB all lowercase because of ANT conventions. */
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
        LaunchParams taskParams=new LaunchParams();
        
        if(getSrcdir()==null)
        {
            throw new BuildException("srcdir not set.");
        }
        else
        {
            taskParams.setSrcdir(getSrcdir());
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



}