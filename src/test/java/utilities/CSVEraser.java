package utilities;

import java.io.File;

public class CSVEraser {



    public static void deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
