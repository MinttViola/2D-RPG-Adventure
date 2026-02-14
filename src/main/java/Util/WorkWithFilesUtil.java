package Util;

import java.net.URL;

public class WorkWithFilesUtil {

	public WorkWithFilesUtil() {
	}
	public URL get(String path) {
        URL url = getClass()
                .getClassLoader()
                .getResource(path);

        if (url == null) {
            throw new IllegalStateException("File not found: " + path);
        }
        return url;
    }
}
