# WebContent

WebContent is a Java library for retrieving content from a URL and downloading files.

## Installation

Download the [jar](https://github.com/sanee4ka/webcontent/releases) file and add it to your library list.

## Usage

```java
public class Example {

    public static void main(String[] args) throws IOException {
        // Example getData
        InputStream inputStream = WebContent.getData("http://example.com/post.php", Map.of("%header-key%", "%header-value%"), Map.of("%post-key%", "%post-value%"));
        // Example downloadFile
        WebContent.downloadFile("http://example.com/test.png", "%save-dir%", "%save-name%"); // Or use other downloadFile methods
    }
}
```

## Contributing

The project is archived