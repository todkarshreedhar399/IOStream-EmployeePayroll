public class CensusAnalyserException  extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, FILE_TYPE_INCORRECT, FILE_DELIMITER_INCORRECT, INCORRECT_HEADER;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
