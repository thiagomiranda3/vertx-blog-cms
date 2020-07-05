package br.com.tommiranda.blog.util;

import com.google.gson.Gson;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionPrinter {

    private static final Gson gson = new Gson();

    public static String print(Exception e) {
        return "ExceptionPrinter --> " + gson.toJson(ExceptionUtils.getStackTrace(e));
    }
}
