package user.sanee4ka.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * @author sanee4ka
 * @version 1.3
 * @since 7
 */

public class WebHandler {

    public static String getData(String url, Map<String, String> headers, Map<String, String> post) throws IOException {
        return toString(WebContent.getData(url, headers, post));
    }

    public static String getData(String url) throws IOException {
        return toString(WebContent.getData(url));
    }

    public static String getDataHeader(String url, Map<String, String> headers) throws IOException {
        return toString(WebContent.getDataHeader(url, headers));
    }

    public static String getDataPost(String url, Map<String, String> post) throws IOException {
        return toString(WebContent.getDataPost(url, post));
    }

    public static String getJsonData(String url, Map<String, String> headers, Map<String, String> post) throws IOException {
        return toString(WebContent.getJsonData(url, headers, post));
    }

    public static String getJsonData(String url) throws IOException {
        return toString(WebContent.getJsonData(url));
    }

    public static String getJsonDataHeader(String url, Map<String, String> headers) throws IOException {
        return toString(WebContent.getJsonDataHeader(url, headers));
    }

    public static String getJsonDataPost(String url, Map<String, String> post) throws IOException {
        return toString(WebContent.getJsonDataPost(url, post));
    }

    public static String getXmlData(String url, Map<String, String> headers, Map<String, String> post) throws IOException {
        return toString(WebContent.getXmlData(url, headers, post));
    }

    public static String getXmlData(String url) throws IOException {
        return toString(WebContent.getXmlData(url));
    }

    public static String getXmlDataHeader(String url, Map<String, String> headers) throws IOException {
        return toString(WebContent.getXmlDataHeader(url, headers));
    }

    public static String getXmlDataPost(String url, Map<String, String> post) throws IOException {
        return toString(WebContent.getXmlDataPost(url, post));
    }

    private static String toString(InputStream is) {
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "ERROR: can not convert InputStream to String";
    }
}
