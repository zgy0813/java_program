package com.lambda;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Y
 */
public class FunctionalInterface {
    /**
     * 列出当前目录下的所有扩展名为.txt的文件
     */
    File f = new File(".");
    File[] files = f.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            if (name.endsWith(".txt")) {
                return true;
            }
            return false;
        }
    });

    /**
     * lambda语法
     * lambda表达式由 -> 分隔为两部分，前面是方法参数，后面 {} 内是方法的代码
     */
    File f2 = new File(".");
    File[] files2 = f2.listFiles((File dir, String name) -> {
            return name.endsWith(".txt");
        }
    );

    /**
     * lambda语法2
     * 当主体代码只有一条语句时，括号和return语句也可以省略，结尾不能加分号
     * 当方法参数只有一个时，参数部分的括号可以省
     * 当没有参数的时候，参数部分写为 ()
     */
    File f3 = new File(".");
    File[] files3 = f3.listFiles((File dir, String name) ->  name.endsWith(".txt"));



}
