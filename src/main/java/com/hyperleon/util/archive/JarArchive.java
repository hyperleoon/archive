package com.hyperleon.util.archive;

import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;

import java.io.File;

/**
 * Jar 归档
 * @author leon
 * @date 2021-03-05 11:44
 **/
public class JarArchive implements Archive {

    @Override
    public Boolean checkFile(FileNode fileNode) throws RuntimeException {
        throw new ActionNotSupportedException("not support jar file this version");
    }

    @Override
    public void archive(String fileName, File inputFile) throws RuntimeException {
        throw new ActionNotSupportedException("not support jar file this version");
    }
}
