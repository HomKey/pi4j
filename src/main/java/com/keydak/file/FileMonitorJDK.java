package com.keydak.file;

import com.keydak.file.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileMonitorJDK {
    public static void test(){
        final Path path = Paths.get("/sys/class/gpio/gpio3");
        System.out.println(path.getFileName());
        File[] files = path.toFile().listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            System.out.println("try");
            //给path路径加上文件观察服务
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.OVERFLOW);
            while (true) {
                System.out.println("while start");
                final WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    final WatchEvent.Kind<?> kind = watchEvent.kind();
                    System.out.println(kind.name());
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    //创建事件
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("[新建]");
                    }
                    //修改事件
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("修改]");
                    }
                    //删除事件
                    if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("[删除]");
                    }
                    // get the filename for the event
                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                    final Path filename = watchEventPath.context();
                    // print it out
                    System.out.println(kind + " -> " + filename);
                    String content = FileUtils.readFileContent(filename.toFile());
                    System.out.println(content);
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex);
        }
    }
}
