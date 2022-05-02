package de.tanukihardwarestore.ProductService.data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import de.tanukihardwarestore.ProductService.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVDataReader {

    private ResourceLoader resourceLoader;

    CSVDataReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Reads data from file
     * @param path path to file
     * @return Array representating the Data for inport into db
     * @throws IOException when file was not found
     */
    public List<String[]> readData(String path){
        List<String[]> list = new ArrayList<>();

        try {
            //FileReader fileReader = new FileReader(new ClassPathResource(path).getInputStream().toString());

            Resource resource = resourceLoader.getResource(path);
            InputStream inputStream = resource.getInputStream();


            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            list = csvReader.readAll();
            inputStream.close();
            //fileReader.close();
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * returns a List of Objects representating the csv file entries
     * @param path the path to the file
     * @return the Lsit ob objects read by csv
     */
    public List<Product> getObjects(String path) {
        List<String[]> list = readData(path);
        List<Product> objects = new ArrayList<>();


        for (String[] comp :
                list) {
            System.out.println(Arrays.toString(comp));
            objects.add(new Product(Long.parseLong(comp[0]), Long.parseLong(comp[1]), Long.parseLong(comp[2]), Long.parseLong(comp[3]), Long.parseLong(comp[4]), Long.parseLong(comp[5]), Long.parseLong(comp[6]), comp[7]));
        }

        return objects;
    }
}
