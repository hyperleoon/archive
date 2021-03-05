package com.hyperleon.util.archive;

import java.io.File;

/**
 * @author leon
 * @date 2021-03-05 11:58
 **/
public abstract class AbstractGenericArchive implements GenericArchive {
    public final void doArchive(FileNode fileNode,String archiveFileName) {
        if (!checkFile(fileNode)) {
            throw new RuntimeException("check fail");
        }
        File inputFile = creatTempDir(fileNode);
        archive(archiveFileName,inputFile);
        clean();
    }
}
