package com.mezjh.blog.play.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 快速删除XML注释
 * @author ZJH
 * @date 2020/11/11 16:19
 */
public class DeleteXml {
    public static void main(String[] args) {
        String xml="";
        String regexPattern = "<!-[\\s\\S]*?-->";
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(xml);

        xml = matcher.replaceAll("");

        System.out.println(xml);
    }

    public void test(){
    }
}
