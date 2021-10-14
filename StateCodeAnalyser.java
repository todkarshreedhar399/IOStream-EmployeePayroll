import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
public class StateCodeAnalyser {
    private static String[] headerName = {"SrNo", " StateName", " TIN", " StateCode"};
    private static String delimiterCharacter = ",";

    private static boolean checkFileType(String filePath, String extension) throws CensusAnalyserException {
        int index = filePath.lastIndexOf('.');
        if (!filePath.startsWith(extension, index)) {
            throw new CensusAnalyserException("Incorrect file type(extension)", CensusAnalyserException.ExceptionType.FILE_TYPE_INCORRECT);
        }
        return true;
    }

    private static boolean checkHeader(String[] header) throws CensusAnalyserException {
        if (header.length != headerName.length) {
            throw new CensusAnalyserException("Incorrect Header", CensusAnalyserException.ExceptionType.INCORRECT_HEADER);
        } else {
            for (int i = 0; i < header.length; i++) {
                if (!header[i].equals(headerName[i])) {
                    throw new CensusAnalyserException("Incorrect Header", CensusAnalyserException.ExceptionType.INCORRECT_HEADER);
                }
            }
        }
        return true;
    }

    private static boolean checkDelimiter(String delimiter) throws CensusAnalyserException {
        if(!delimiter.equals(delimiterCharacter)){
            throw new CensusAnalyserException("Incorrect Delimiter", CensusAnalyserException.ExceptionType.FILE_DELIMITER_INCORRECT);
        }
        return true;
    }

    public int loadIndianStateCode(String filePath) throws CensusAnalyserException {
        int count = 0;
        try {
            if (checkFileType(filePath, ".csv")) {
                FileReader filereader = new FileReader(filePath);
                CSVParser parser;
                if (checkDelimiter(",")) {
                    parser = new CSVParserBuilder().withSeparator(',').build();
                    CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).build();
                    List<String[]> allData = csvReader.readAll();
                    if (checkHeader(allData.get(0))) {
                        for (String[] row : allData) {
                            count++;
                            for (String cell : row) {
                                System.out.print(cell);
                            }
                            System.out.println();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException e) {
            new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return count;
    }
}
