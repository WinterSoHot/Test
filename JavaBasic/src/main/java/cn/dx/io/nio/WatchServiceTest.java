package cn.dx.io.nio;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author dongxian
 * @version 1.0
 * @className WatchServiceTest
 * @description WatchService 监听文件变化
 * @date 20-5-15 下午1:54
 **/
public class WatchServiceTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //为当前路径下注册监听
        Paths.get(".").register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true){
            //获取下一个文件变化
            WatchKey key = watchService.take();
            key.pollEvents().forEach(watchEvent -> {
                System.out.println(watchEvent.context() + "文件发生了" + watchEvent.kind() + "变化");
            });
            // 重置WatchKey
            boolean valid = key.reset();
            if (!valid){
                //重置失败退出
                break;
            }
        }
    }
}
