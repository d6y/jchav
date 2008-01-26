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
package com.googlecode.jchav.jmeter;

import java.util.List;


/**
 * Interface defining the names of tags in the JMeter files.
 * These tag names have changed between XML files v2.0 and v2.1.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public interface XMLFormatDefinitions
{

    /** Get the names for the sample tag.
     * 
     * @return the root tag names samples.
     */
    List<String> getSampleTagNames();
    
    /** Get the name for the elapsed time attribute. 
     * 
     * @return the elapsed time attribute name.
     */
    String getElapsedTimeAttributeName();
    
    /** Get the name for the label attribute. 
     * 
     * @return the pageid/test name attribute.
     */
    String getLabelAttributeName();


    /** Get the name for the return code value.
     *
     * @return the attribute name for the return code flag
     */
     String getReturnCodeName();

}
