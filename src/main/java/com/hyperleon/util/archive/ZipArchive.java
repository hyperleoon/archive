package com.hyperleon.util.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip 归档
 * @author leon
 * @date 2021-03-05 11:44
 **/
public class ZipArchive extends AbstractGenericArchive {

    private String baseDir;

    @Override
    public String tmpDirName() {
        return "tmp";
    }

    @Override
    public FileNode transfer(String json) throws RuntimeException {
        return null;
    }

    @Override
    public Boolean checkFile(FileNode fileNode) throws RuntimeException {
        return true;
    }

    @Override
    public File creatTempDir(FileNode fileNode) throws RuntimeException {
        LinkedList<String> tmpDir = new LinkedList<>();

        if (fileNode == null) {
            return new File(baseDir);
        }

        if (fileNode.isFile()) {
            createFile(baseDir + "/"+ fileNode.getFileName(),fileNode.getContent(),false);
            return new File(baseDir);
        }

        Stack<FileNode> dirStack = new Stack<>();
        dirStack.push(fileNode);
        while (!dirStack.isEmpty()) {
            FileNode pop = dirStack.pop();
            if (pop.isFile() || pop.getSubFile() == null || pop.getSubFile().size() ==0) {
                StringBuilder tmpDirStr = new StringBuilder("");
                tmpDirStr = new StringBuilder();

                tmpDir.forEach(tmpDirStr::append);
                tmpDir.addLast(pop.getFileName());
                if (pop.isFile()) {
                    createFile(baseDir  + "/" + tmpDirStr + "/" + pop.getFileName(),pop.getContent(),false);
                } else {
                    createFile(baseDir  + "/" + tmpDirStr + "/" + pop.getFileName(),null,true);
                }
                tmpDir.removeLast();
            } else if (!pop.isFile() && pop.getSubFile() != null) {
                tmpDir.addLast(pop.getFileName() + "/");
                for (FileNode sub : pop.getSubFile()) {
                    dirStack.push(sub);
                }
            }
        }
        return new File(baseDir);
    }

    @Override
    public void archive(String zipFileName, File inputFile) throws RuntimeException {
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(out, inputFile, "");
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clean() throws RuntimeException {
        File file = new File(baseDir);
        if (!file.exists()) {
            return;
        } else {
            if (file.isFile()) {
                deleteFile(baseDir);
            } else {
                deleteDirectory(baseDir);
            }
        }
    }


    public void deleteFile(String path) {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public void deleteDirectory(String path) {
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File dirFile = new File(path);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return;
        }
        File[] files = dirFile.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                deleteFile(file.getAbsolutePath());
            } else {
                deleteDirectory(file.getAbsolutePath());
            }
        }
        dirFile.delete();
    }



    private static void createFile(String path, String content, Boolean isDir)  {
        if (path != null && !"".equals(path)) {
            File file = new File(path);
            if (file.getParentFile() == null || !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                if (isDir) {
                    file.mkdir();
                } else {
                    file.createNewFile();
                    Writer out =new FileWriter(file);
                    out.write(content);
                    out.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void zip(ZipOutputStream out, File f, String base)
            throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (File file : fl) {
                zip(out, file, base + file.getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

}
