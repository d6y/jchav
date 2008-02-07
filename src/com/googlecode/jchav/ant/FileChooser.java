package com.googlecode.jchav.ant;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

/**
 * A helper class that does the hard work associated with obtaining
 * the set of applicable files that need to be processed by jchav.
 * 
 * @author $LastChangedBy: dallaway $ 
 * @version $LastChangedDate: 2006-10-19 08:25:54 +0100 (Thu, 19 Oct 2006) $ $LastChangedRevision: 122 $
 */
public final class FileChooser
{
    
    /**
     * A filter that ensures that folders are not included in any lists.O
     */
    private static final FileFilter noDirFilter = new FileFilter()
    {
        public boolean accept(File f)
        {
            return !f.isDirectory();
        }
    };

    
    /**
     * A comparator instance that can be used for comparing file equivalence based on
     * the modification date of the file.
     */
    private static final Comparator<File> modifiedOnComparator = new Comparator<File> ()
    {
        public int compare(File f1, File f2)
        {
            long diff = f1.lastModified() - f2.lastModified();
            int returnValue;
            if (diff < 0L)
            {
                returnValue = -1;
            }
            else if (diff > 0L)
            {
                returnValue = +1;
            }
            else
            {
                assert diff == 0L;
                returnValue = 0;
            }
            return returnValue;
        }
    };  
    
    
    /**
     * Extract the set of applicable File objects for the given src folder.
     * An applicable file is currently defined as: any file.
     * 
     * @param srcDir The src folder to scan for applicable files.
     * @return A set of File objects sorted by modification date.
     */
    public static Set<File> corraleSrcFiles(final String srcDir)
    {
        if (srcDir == null || "".equals(srcDir.trim()))
        {
            // If the folder path is blank, return an empty set.
            return Collections.emptySet();
        }
        
        return corraleSrcFiles(new File(srcDir));
    }

    
    /**
     * Extract the set of applicable File objects for the given src folder.
     * An applicable file is currently defined as: any file.
     * 
     * @param srcDir The src folder <code>File</code> object to scan for applicable files.
     * @return A set of File objects sorted by modification date.
     */
    public static Set<File> corraleSrcFiles(final File srcDir)
    {
        final Set<File> srcFiles = new TreeSet<File>(modifiedOnComparator);
        
        if (srcDir != null)
        {
            // Filter out the dirs.
            final File[] nonDirs = srcDir.listFiles(noDirFilter);
            if (nonDirs != null)
            {
                // Only do this is there are valid files, otherwise you'll get an NPE.
                Arrays.sort(nonDirs, modifiedOnComparator);
                
                final List<File> fileList = Arrays.asList(nonDirs);
                srcFiles.addAll(fileList);
            }
        }
        
        return srcFiles;
    }
    
    
    /**
     * Extract the set of applicable File objects for the given src folder.
     * An applicable file is currently defined as: any file.
     * 
     * @param fileSets The ant fileset collection to extract files from.
     * @param project The ant project instance to use (for fileset iteration).
     * @return A set of File objects sorted by modification date.
     */
    public static Set<File> corraleSrcFiles(final ArrayList<FileSet> fileSets, final Project project)
    {
        final Set<File> srcFiles = new TreeSet<File>(modifiedOnComparator);
        
        for (final FileSet fileSet : fileSets)
        {
            final DirectoryScanner scanner = fileSet.getDirectoryScanner(project);
            final File baseDir = scanner.getBasedir();
            
            final String[] files = scanner.getIncludedFiles();
            for (final String file : files)
            {
                srcFiles.add(new File(baseDir, file));
            }        
        }        
        
        return srcFiles;
    }

}
