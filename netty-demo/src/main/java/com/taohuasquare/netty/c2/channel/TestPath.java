package com.taohuasquare.netty.c2.channel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author happy
 * @since 2022/1/17
 */
public class TestPath {
    public static void main(String[] args) {
        Path source1 = Paths.get("1.txt"); // 相对路径 使用 user.dir 环境变量来定位 1.txt

        Path source2 = Paths.get("d:\\1.txt"); // 绝对路径 代表了  d:\1.txt

        Path source3 = Paths.get("d:/1.txt"); // 绝对路径 同样代表了  d:\1.txt

        Path projects = Paths.get("d:\\data", "projects"); // 代表了

        System.out.println(Files.exists(source1));
        try {
            Files.createDirectory(source1);
            Files.createDirectories(source1);
            Files.copy(source1, source2);
            Files.copy(source1, source2, StandardCopyOption.REPLACE_EXISTING);
            Files.move(source1, source2, StandardCopyOption.ATOMIC_MOVE);
            Files.delete(source1);
            Files.delete(source1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
