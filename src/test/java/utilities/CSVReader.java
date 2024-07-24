package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Object [][] readData(String filePath) {

        List<String[]> list;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            list = new ArrayList<>();
            while ((line=br.readLine()) != null){
                String[] row = line.split(",");
                list.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Object[][] data = new Object[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
         }

        return data;
    }


}
