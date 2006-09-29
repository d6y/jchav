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

import java.awt.Color;
import java.util.SortedSet;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.MinMaxCategoryRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.googlecode.jchav.data.Measurement;


/**
 * Provides a way to create a single chart (graph) from
 * a set of data.  
 * 
 * The chart is a <a href="http://en.wikipedia.org/wiki/Candlestick_chart">candlestick chart</a>, 
 * showing the min ("low") and max ("high") of the response
 * time for each build for a page as the "wick" of the chart.  
 * The "body" is simply the mean RT for the build.  
 * Colour does not have any interpretation in this chart.
 * 
 * A good alternative we might consider is the <a href="http://en.wikipedia.org/wiki/Box_plot">box plot</a>.
 * 
 * Note that this is not a timeseries chart, because we do not
 * know the interval between builds.
 * 
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-09-28 00:55:09 +0100 (Thu, 28 Sep 2006) $ $LastChangedRevision: 17 $
 */
public class MinMeanMaxChart extends Chart
{


    /**
     * Construct a new chart to display the given data.
     * 
     * @param pageId The page id, used for the title of the chart.
     * @param data the data to plot.
     */
    public MinMeanMaxChart(final String pageId, final SortedSet<Measurement> data)
    {
        // The renderer that does all the real work here:
        final MinMaxCategoryRenderer minMaxRenderer = new MinMaxCategoryRenderer();
        minMaxRenderer.setObjectIcon( new FilledCircle());
         
        // Munge the data into form JFreeChart can use:
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Measurement m : data)
        {
            dataset.addValue(m.getMinimumTime(), "min", m.getBuildId().getBuildName());
            dataset.addValue(m.getMaximumTime(), "max", m.getBuildId().getBuildName());
            dataset.addValue(m.getAverageTime(), "mean", m.getBuildId().getBuildName());
        }
        
        // Create the plot area:
        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset);
        plot.setRenderer(minMaxRenderer);
        plot.setDomainAxis(new CategoryAxis("Build")); // TO DO: i18n
        plot.setRangeAxis(new NumberAxis("RT"));

        // Build labels running diagonally under the bars of the chart.
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(false);

        
        // Render the chart:  the legend here would be the "min", "max", "mean"
        // strings used when created int category data set.
        chart = new JFreeChart(pageId, JFreeChart.DEFAULT_TITLE_FONT, plot, /*show legend=*/false);
        chart.setTitle(pageId);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);
         
    }



    
    
    
    
}
