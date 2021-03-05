package com.hyperleon.util.archive;

import java.io.File;

/**
 * 归档接口
 * @author leon
 * @date 2021-03-05 11:38
 **/
public interface Archive {


    /**
     * 校验文件内容
     * @param fileNode 文件抽象结构
     * @return 文件结构是否正确
     * @throws RuntimeException 异常
     */
    Boolean checkFile(FileNode fileNode) throws RuntimeException;


    /**
     * 执行加载
     * @param fileName 归档文件名
     * @param inputFile 文件
     * @throws RuntimeException 异常
     */
    void archive(String fileName, File inputFile) throws RuntimeException;

}
