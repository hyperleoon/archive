package com.hyperleon.util.archive;

import java.util.Arrays;

/**
 * @author leon
 * @date 2021-03-05 13:33
 **/
public class Demo {
    public static void main(String[] args)  {
        AbstractGenericArchive archive = new ZipArchive();
        archive.doArchive(genFileNode(),"demo.zip");
    }

    /**
     * /src/hello.py
     * /src/resource
     * /requirement.txt
     * /empty
     * @return FileNodo
     */
    public static FileNode genFileNode() {

        FileNode zip = new FileNode();
        FileNode src = new FileNode();
        FileNode requirement = new FileNode();
        FileNode empty = new FileNode();
        FileNode code = new FileNode();
        FileNode resource = new FileNode();

        resource.setFile(false);
        resource.setFileName("resource");

        code.setContent("import this");
        code.setFileName("hello.py");
        code.setFile(true);

        src.setFileName("src");
        src.setFile(false);
        src.setSubFile(Arrays.asList(code,resource));

        requirement.setFile(true);
        requirement.setContent("requirement");
        requirement.setFileName("requirement.txt");

        empty.setFile(false);
        empty.setFileName("empty");

        zip.setFileName("demo");
        zip.setFile(false);
        zip.setSubFile(Arrays.asList(src,requirement,empty));

        return zip;
    }

}
