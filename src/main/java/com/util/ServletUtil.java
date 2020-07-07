package com.util;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author shkstart
 * @create 2020-06-01 20:02
 */
public abstract class ServletUtil {

    public static String getJSON(BufferedReader reader) throws IOException {
        char[] buf = new char[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = reader.read(buf)) != -1)
        {
            sb.append(new String(buf, 0, len));
        }

        return sb.toString();
    }

    // 把文件流包装成对象
    public static List getListByStream(InputStream in){
        List list = null;

        try (
            // 把字节流转换成字符流，然后转换成缓冲流
             BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
             ){

            // 读取表头
            String headStr = bfr.readLine();
            String[] heads = headStr.split(",");

            // 装map对象
            list = new ArrayList<>();

            String info = null;
            while ((info= bfr.readLine()) != null)
            {
                // 切分
                String[] split = info.split(",");

                HashMap<String, String> map = new HashMap<>(split.length);

                for (int i = 0; i < split.length; i++) {
                    // 编码并放入
                    map.put(heads[i], split[i]);
                }

                // 加入集合
                list.add(map);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
