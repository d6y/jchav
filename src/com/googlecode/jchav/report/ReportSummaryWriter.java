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
package com.googlecode.jchav.report;

import com.googlecode.jchav.chart.ChartNameUtil;
import com.googlecode.jchav.data.PageDataImpl;
import com.googlecode.jchav.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;

/**
 * Writes the summary (front page) of the JChav report.
 *
 * @author $LastChangedBy: dallaway $
 * @version $LastChangedDate: 2006-10-03 19:21:47 +0100 (Tue, 03 Oct 2006) $ $LastChangedRevision: 72 $
 */
public class ReportSummaryWriter
{

    /** The writer to write to. */
    private Writer writer;

    /** The root folder files are written to. */
    private File rootDir;

    /** Timestamp for the report. */
    private final String timestamp;

    /**
     * Create a page summary writer.
     *
     * @param writer The writer to write output to.
     * @param rootDir The root folder files are written to.
     * @param reportTitle The title to apply to the report.
     *
     * @throws IOException If there is a problem writing output.
     */
    public ReportSummaryWriter(final Writer writer, final File rootDir, final String reportTitle) throws IOException
    {
        this.writer = writer;
        this.rootDir = rootDir;
        this.timestamp = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG).format(new Date());

        // Copy over some files ...
        FileUtil.copy(this.getClass().getClassLoader().getResourceAsStream("com/googlecode/jchav/report/resources/style.css"), new File(rootDir, "style.css"));
        FileUtil.copy(this.getClass().getClassLoader().getResourceAsStream("com/googlecode/jchav/report/resources/badge104x47.jpg"), new File(rootDir, "badge104x47.jpg"));

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        writer.write("<head>\n");
        writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        writer.write("<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\" media=\"all\" />");
        if (reportTitle != null && !reportTitle.equals(""))
        {
            writer.write("<title>");
            writer.write(reportTitle);
            writer.write("</title>");
        }
        else
        {
            writer.write("<title>Jchav summary report</title>");
        }
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("<h2>Report generated at: " + timestamp + "</h2>\n");
    }

    /**
     * Write out an entry.
     *
     * @param pageId The page id.
	 * @param title the title for the page.
     *
     * @throws IOException If there is a problem writing the output.
     */
    public void writeEntry(final String pageId, final String title) throws IOException
    {
        if (PageDataImpl.SUMMARY_PAGE_ID.equals(pageId))
        {
            writer.write("<div class=\"summary\">\n" +

            "<h2>" + "Overall Summary" + "</h2>\n" + "<a href=\"" +

            URLEncoder.encode(pageId, "UTF-8") + ".html" +

            "\">\n" + "<img class=\"centred\" src=\"" + URLEncoder.encode(ChartNameUtil.buildChartImagePath(pageId, rootDir).getName(), "UTF-8") + "\" alt=\"Detailed View of " + pageId + "\" />\n" + "</a>\n" + "</div>\n"

            );

            return;
        }

        writer.write("<div class=\"page\">\n" +

        "<h2>" + title + "</h2>\n" + "<a href=\"" +

        URLEncoder.encode(pageId, "UTF-8") + ".html" +

        "\" >\n" + "<img class=\"centred\" src=\"" + URLEncoder.encode(ChartNameUtil.buildChartThumbnailPath(pageId, rootDir).getName(), "UTF-8") + "\" alt=\"Detailed View of " + title + "\" />\n" + "</a>\n" + "</div>\n"

        );
    }

    /**
     * Finish the file.
     *
     * @throws IOException If there is a problem writing the output.
     */
    public void finish() throws IOException
    {
        writer.write("<p id=\"GeneratedBy\">Report generated at: " + timestamp + "</p>");
        writer.write("<a href=\"http://jchav.blogspot.com/\"><img width=\"104\" height=\"47\" src=\"badge104x47.jpg\" alt=\"Pimped by jChav\" /></a>");
        writer.write("</body>\n");
        writer.write("</html>\n");
    }

}
