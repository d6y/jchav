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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.jfree.chart.ChartUtilities;
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
 * @author $LastChangedBy$
 * @version $LastChangedDate$ $LastChangedRevision$
 */
public class ChartCreator
{
    
    /**
     * All methods a static helpers, so no need to
     * construct an instance.
     */
    private ChartCreator()
    {
    }

    /**
     * Creates a PNG graphic for the given data.
     * 
     * @param pageId The page id, used for the title of the chart.
     * @param data the data to plot.
     * @param out the steam to write the PNG to.
     * 
     * @throws IOException if there was a problem creating the chart.
     */
    public static void write(final String pageId, final List<Measurement> data, final OutputStream out) 
        throws IOException
    {
       
        final MinMaxCategoryRenderer minMaxRenderer = new MinMaxCategoryRenderer();
        
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(Measurement m : data)
        {
            dataset.addValue(m.getMinimumTime(), "min", m.getBuildId());
            dataset.addValue(m.getMaximumTime(), "max", m.getBuildId());
            dataset.addValue(m.getAverageTime(), "mean", m.getBuildId());
            
        }
        
        
        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset);
        plot.setRenderer(minMaxRenderer);
        plot.setDomainAxis(new CategoryAxis("Build"));
        plot.setRangeAxis(new NumberAxis("RT"));

           
        // Build labels running diagonally under the bars of the chart.
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(false);

        
        // Render the chart:
        
        final JFreeChart chart = new JFreeChart(pageId, JFreeChart.DEFAULT_TITLE_FONT, plot, /*show legend=*/false);
       
        chart.setTitle(pageId);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);
        
        ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
        
     
    }
    
}
