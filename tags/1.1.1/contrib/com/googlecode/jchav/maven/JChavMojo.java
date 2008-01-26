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
package com.googlecode.jchav.maven;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.googlecode.jchav.ant.Controller;
import com.googlecode.jchav.ant.LaunchParams;

/**
 * Maven plugin to run JChav.
 * 
 * Thanks for Tim McCune for this.
 * 
 * <br />
 * See also:
 * <ul>
 * <li> <a href="http://wiki.apache.org/jakarta-jmeter/JMeterMavenPlugin">JMeter Maven Plugin</a> </li>
 * <li> <a href="http://maven.apache.org/guides/plugin/guide-java-plugin-development.html">Maven Java Plugin Guide</a></li>.
 * </ul>
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 * 
 * @goal jchav
 */
public class JChavMojo extends AbstractMojo
{

    /**
     * @parameter expression="jmeter-reports"
     */
    private File srcDir;

    /**
     * @parameter expression="${project.reporting.outputDirectory}/jchav"
     */
    private File destDir;

    /**
     * @parameter expression="${reportTitle}" default-value="JChav Report"
     */
    private String reportTitle;

    /**
     * @parameter expression="${uniformYAxis}" default-value="true"
     */
    private boolean uniformYAxis = true;

    /**
     * {@inheritDoc}
     */
    public void execute() throws MojoExecutionException
    {
        LaunchParams params = new LaunchParams();
        params.setSrcdir(srcDir.toString());
        params.setDestdir(destDir.toString());
        params.setReportTitle(reportTitle);
        params.setUniformYAxis(uniformYAxis);
        Controller controller = new Controller();
        try
        {
            controller.go(params);
        }
        catch (IOException e)
        {
            throw new MojoExecutionException("Couldn't run JChav", e);
        }
    }
}