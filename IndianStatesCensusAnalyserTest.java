import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IndianStatesCensusAnalyser {
    public static boolean checkHeader(String filePath, String[] stringName) throws IOException, CensusAnalyserException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        String[] header = line.split(",");
        for (int i = 0; i < header.length && i < stringName.length; i++) {
            if (!stringName[i].equals(header[i])) {
                throw new CensusAnalyserException("Incorrect header!", CensusAnalyserException.ExceptionType.INCORRECT_HEADER);
            }
        }
        return true;
    }

    public static boolean checkDelimiter(String filePath, String delimitingCharacter) throws IOException, CensusAnalyserException {
        try {
            Scanner input = new Scanner(Paths.get(filePath));
            input.useDelimiter(",");
            Pattern result = input.delimiter();
            if (result.pattern().equals(delimitingCharacter)) {
                return true;
            } else {
                throw new Exception("Incorrect Delimiter");
            }
        } catch (Exception e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.FILE_DELIMITER_INCORRECT);
        }
    }

    public static int loadIndianCensusData(String filePath) throws CensusAnalyserException {
        int count = 0;
        try {
            int index = filePath.lastIndexOf('.');
            if (!filePath.startsWith(".csv", index)) {
                throw new CensusAnalyserException("Incorrect type", CensusAnalyserException.ExceptionType.FILE_TYPE_INCORRECT);
            }
            FileReader filereader = new FileReader(filePath);
            CSVParser parser;
            if (checkDelimiter(filePath, ",")) {
                parser = new CSVParserBuilder().withSeparator(',').build();
                CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
                List<String[]> allData = null;
                try {
                    allData = csvReader.readAll();
                } catch (CsvException e) {
                    e.printStackTrace();
                }
                String[] header = {"State", "Population", "AreaInSqKm", "DensityPerSqKm"};
                if (checkHeader(filePath, header)) {
                    for (String[] row : allData) {
                        count++;
                        for (String cell : row) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Indian State Census Analyser!");
    }
}
