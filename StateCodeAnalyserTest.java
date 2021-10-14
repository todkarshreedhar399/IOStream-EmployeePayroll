import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class StateCodeAnalyserTest {
    private static final String INDIA_STATE_CODE_FILE_PATH = "src/main/resources/IndianStateCode.csv";
    private static final String INDIA_STATE_CODE_WRONG_FILE_PATH = "main/resources/IndianStateCode.csv";
    private static final String INDIA_STATE_CODE_WRONG_FILE_TYPE = "src/resources/IndianStateCode.txt";

    @Test
    public void givenStateCodeCSV_ShouldReturnExactCount() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            int numOfRecords = stateCodeAnalyser.loadIndianStateCode(INDIA_STATE_CODE_FILE_PATH);
            Assert.assertEquals(38, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenStateCodeCSV_ShouldThrowException_WrongPathPassed(){
        try{
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCode(INDIA_STATE_CODE_WRONG_FILE_PATH);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenStateCodeCSV_ShouldThrowException_IncorrectTypePassed(){
        try{
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCode(INDIA_STATE_CODE_WRONG_FILE_TYPE);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_INCORRECT,e.type);
        }
    }
    @Test
    public void givenStateCodeCSV_ShouldThrowException_IncorrectDelimiterPassed(){
        try{
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCode(INDIA_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_DELIMITER_INCORRECT,e.type);
        }
    }
    @Test
    public void givenStateCodeCSV_ShouldThrowException_IncorrectHeaderPassed(){
        try{
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            stateCodeAnalyser.loadIndianStateCode(INDIA_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_HEADER,e.type);
        }
    }
}
