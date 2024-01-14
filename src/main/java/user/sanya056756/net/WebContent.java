package user.sanya056756.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

/**
 * <strong>
 *     Simple library for retrieving content from a URL and downloading files.
 *     <p>
 *     It offers easy-to-use functions for making HTTP requests and processing the received data.
 *     <p>
 *     This lightweight library provides convenient methods for fetching content from a URL in various formats including:
 * </strong>
 *
 * <li>Simple text</li>
 * <li>JSON</li>
 * <li>XML</li>
 *
 * @author _Sanya056756_
 * @version 1.0
 * @since 7
 *
 */

public class WebContent {

    public static InputStream getData(String url, Map<String, String> headers, Map<String, String> post)
            throws IOException, WebContentException {
        if (headers == null && post == null)
            return getWebInputStream(url);

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod(post != null ? "POST" : "GET");

        con.setRequestProperty("User-Agent", "WebContent/1.0"); // Default value

        if (headers != null)
            for (Map.Entry<String, String> entry : headers.entrySet())
                con.setRequestProperty(entry.getKey(), entry.getValue());

        if (post != null) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                os.write(getStringPost(post).getBytes(StandardCharsets.UTF_8));
            }
        }

        int responseCode = con.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK)
            throw new WebContentException("The site showed an error: " + responseCode);

        try (InputStream is = con.getInputStream()){
            return is;
        } finally {
            con.disconnect();
        }
    }

    public static InputStream getData(String url) throws IOException {
        return getWebInputStream(url);
    }

    public static InputStream getDataHeader(String url, Map<String, String> headers)
            throws WebContentException, IOException {
        return getData(url, headers, null);
    }

    public static InputStream getDataPost(String url, Map<String, String> post)
            throws WebContentException, IOException {
        return getData(url, null, post);
    }

    /**
     * <li>Private special methods</li>
     */

    private static InputStream getDataType(String url, Map<String, String> headers, Map<String, String> post, String dataType)
            throws WebContentException, IOException {
        if (headers == null)
            headers = new HashMap<>();

        headers.put("Accept", "application/" + dataType);

        if (post != null)
            headers.put("Content-Type", "application/x-www-form-urlencoded");

        return getData(url, headers, post);
    }

    private static String getStringPost(Map<String, String> post) {
        StringBuilder builder = new StringBuilder();
        int size = post.size();
        int i = 0;

        for (Map.Entry<String, String> entry : post.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            i++;
            if (i == size)
                return builder.toString();
            builder.append("&");
        }

        return builder.toString();
    }

    /**
     * <li>JSON</li>
     */

    public static InputStream getJsonData(String url, Map<String, String> headers, Map<String, String> post)
            throws WebContentException, IOException {
        return getDataType(url, headers, post, "json");
    }

    public static InputStream getJsonData(String url) throws WebContentException, IOException {
        return getJsonData(url, null, null);
    }

    public static InputStream getJsonDataHeader(String url, Map<String, String> headers)
            throws WebContentException, IOException {
        return getJsonData(url, headers, null);
    }

    public static InputStream getJsonDataPost(String url, Map<String, String> post)
            throws WebContentException, IOException {
        return getJsonData(url, null, post);
    }

    /**
     * <li>XML</li>
     */

    public static InputStream getXmlData(String url, Map<String, String> headers, Map<String, String> post)
            throws WebContentException, IOException {
        return getDataType(url, headers, post, "xml");
    }

    public static InputStream getXmlData(String url) throws WebContentException, IOException {
        return getXmlData(url, null, null);
    }

    public static InputStream getXmlDataHeader(String url, Map<String, String> headers)
            throws WebContentException, IOException {
        return getXmlData(url, headers, null);
    }

    public static InputStream getXmlDataPost(String url, Map<String, String> post)
            throws WebContentException, IOException {
        return getXmlData(url, null, post);
    }

    /**
     * <strong>
     *     Without post and headers args.
     *     <p>
     *     Faster than getData(String, Map, Map).
     * </strong>
     */

    public static InputStream getWebInputStream(String url) throws IOException {
        return new URL(url).openStream();
    }

    /**
     * <li>Downloading files</li>
     */

    public static void downloadFile(String url, String saveDirectory) throws IOException {
        downloadFile(url, saveDirectory, url.substring(url.lastIndexOf("/")+1));
    }

    public static void downloadFile(String url, String saveDirectory, String saveName) throws IOException {
        downloadFile(url, Paths.get(saveDirectory, saveName));
    }

    public static void downloadFile(String url, Path path) throws IOException {
        Files.copy(getWebInputStream(url), path, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * <li>Checks internet availability by trying to open a data stream to the specified host.</li>
     */

    public static boolean isInternetAvailable() {
        return getAvailableHost("https://google.com");
    }

    public static boolean getAvailableHost(String host) {
        try (InputStream ignored = getWebInputStream(host)) {
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * <li>Exception <strong>WenContentException</strong></li>
     */

    public static class WebContentException extends Exception {

        WebContentException(String message) {
            super(message);
        }
    }
}