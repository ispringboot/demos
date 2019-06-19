package net.ijiangtao.tech.demo.http.web.util;

import net.ijiangtao.tech.demo.http.constant.APIConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;

/**
 * response
 *
 * @author ijiangtao
 * @create 2019-06-05 22:52
 **/
public class ResponseJsonWriter {

    public static void write(HttpServletResponse response, Object json) {
        write(response, json, APIConstant.UTF_8);
    }

    public static void write(HttpServletResponse response, Object json, String characterEncoding) {
        String contentType = "application/json;charset=" + characterEncoding;
        write(response, json, contentType, characterEncoding);
    }

    public static void write(HttpServletResponse response, Object content, String contentType, String characterEncoding) {
        if (null != contentType && contentType.length() > 0) {
            response.setContentType(contentType);
        }
        if (null != characterEncoding && characterEncoding.length() > 0) {
            response.setCharacterEncoding(characterEncoding);
        }

        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(content);
            printWriter.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
