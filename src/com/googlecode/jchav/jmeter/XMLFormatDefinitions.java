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


/**
 * Interface defining the names of tags in the JMeter files.
 * These tag names have changed between XML files v2.0 and v2.1.
 * 
 * @author $Author: pgoulbou $
 * @version $Revision: 1.2 $ $Date: 2006/05/05 14:20:33 $
 */
public interface XMLFormatDefinitions
{

    /** Get the name for the sample tag. */
    String getSampleTagName();
    
    /** Get the name for the elapsed time attribute. */
    String getElapsedTimeAttributeName();
    
    /** Get the name for the label attribute. */
    String getLabelAttributeName();
    
    
}
