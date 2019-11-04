package com.pengjunlee.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pengjunlee
 * @create 2019-09-23 14:58
 */
public class TmallUtil {

    private static Set<String> eliminateComments = new HashSet<>();

    public static boolean isValidComment(String rateContent) {
        return !eliminateComments.contains(rateContent);
    }

    public static void loadAllEliminateComments() {
        try {
            ClassPathResource resource = new ClassPathResource("eliminate_comment.txt");
            InputStream inputStream = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                eliminateComments.add(lineTxt.trim());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getOffsetFromParams(Map<String, Object> map) {
        int page = map.get("page") == null ? 1 : Integer.valueOf(map.get("page").toString());
        int limit = map.get("limit") == null ? 10 : Integer.valueOf(map.get("limit").toString());
        Integer offset = (page - 1) * limit;
        return offset;
    }

}
