package cloud.autotests.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public String readStringFromFile(String filePath) {
        return new String(readBytesFromFile(filePath));
    }

    public void saveFile(String content, String filePath)  {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private String resourcePath(String file) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
//        assertNotNull(resource, "Resource not found in classpath: " + file);
        return resource.getFile();
    }

//    public static void getFileFromContainer(String exportFileName) {
//
//        String session = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//        String path = SELENOID_URL + "download/" + session + "/" + exportFileName;
//
//        int retryCount = 1;
//        while (retryCount <= 10) {
//            try {
//                copyURLToFile(new URL(path), new File("build/downloads/" + exportFileName));
//                break;
//            } catch (IOException e) {
//                logger.error("Файл не найден. Попытка " + retryCount + " из 10");
//                sleep(1000);
//                retryCount++;
//            }
//        }
//    }
}
