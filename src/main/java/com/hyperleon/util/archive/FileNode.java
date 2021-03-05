package com.hyperleon.util.archive;

import java.util.List;

/**
 * @author leon
 * @date 2021-03-05 11:38
 **/
public class FileNode {
    /**
     * 是否是文件
     */
    private Boolean isFile;

    /**
     * 文件内容
     */
    private String content;

    /**
     * 文件名称/目录名称
     */
    private String fileName;


    /**
     * 子目录或者文件
     */
    private List<FileNode> subFile;

    public Boolean isFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<FileNode> getSubFile() {
        return subFile;
    }

    public void setSubFile(List<FileNode> subFile) {
        this.subFile = subFile;
    }
}
