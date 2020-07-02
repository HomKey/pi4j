package com.keydak.file.listener;

import com.keydak.file.util.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileListener extends FileAlterationListenerAdaptor {
    /**
     * 文件创建执行
     */
    public void onFileCreate(File file) {
        System.out.println("[新建]:" + file.getAbsolutePath());
    }
    /**
     * 文件创建修改
     */
    public void onFileChange(File file) {
        String s = FileUtils.readFileContent(file);
        System.out.println("[修改]:");
        System.out.println(file.getAbsolutePath());
        System.out.println(s);
    }
    /**
     * 文件删除
     */
    public void onFileDelete(File file) {
        System.out.println("[删除]:" + file.getAbsolutePath());
    }
    /**
     * 目录创建
     */
    public void onDirectoryCreate(File directory) {
        System.out.println("[新建]:" + directory.getAbsolutePath());
    }
    /**
     * 目录修改
     */
    public void onDirectoryChange(File directory) {
        System.out.println("[修改]:" + directory.getAbsolutePath());
    }
    /**
     * 目录删除
     */
    public void onDirectoryDelete(File directory) {
        System.out.println("[删除]:" + directory.getAbsolutePath());
    }
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }
}
