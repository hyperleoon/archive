package com.hyperleon.util.archive;

import java.io.File;

/**
 * 一般的归档接口
 * @author leon
 * @date 2021-03-05 11:53
 **/
public interface GenericArchive extends Archive {

    /**
     * json转换成FileNode
     * @param json json信息
     * @return FileNode fileNode
     * @throws RuntimeException 异常
     */
    FileNode transfer(String json) throws RuntimeException;

    /**
     * 将内存中fileNode创建临时文件夹
     * @param fileNode 文件抽象结构
     * @return file 文件
     * @throws RuntimeException 异常
     */
    File creatTempDir(FileNode fileNode) throws RuntimeException;

    /**
     * 清理临时文件
     * @throws RuntimeException 异常
     */
    void clean() throws RuntimeException;

    /**
     * 获取临时目录名称
     * @return 临时目录名称
     */
    String tmpDirName();
}
