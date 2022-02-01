package helperTools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataExtractorTest {

    @Test
    void badSeparatorTest() {
        String timeStrWithBadSeparator = "23-11";
        assertThrows(IllegalArgumentException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithBadSeparator, ":");
        });
    }
    @Test
    void NaNTestInString(){
        String timeStrWithNaN = "11b:11";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithNaN, ":");
        });
    }
    @Test
    void NaNTestAtStart(){
        String timeStrWithNaN = "BB:11";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithNaN, ":");
        });
    }@Test
    void NaNTestAtEnd(){
        String timeStrWithNaN = "11:11b";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithNaN, ":");
        });
    }
    @Test
    void HoursIntTooHigh(){
        String timeStrWithHoursOutOfRange = "24:11";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithHoursOutOfRange, ":");
        });
    }
    @Test
    void HoursIntNegative(){
        String timeStrWithHoursOutOfRange = "-1:11";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithHoursOutOfRange, ":");
        });
    }
    @Test
    void MinuteIntTooHigh(){
        String timeStrWithMinutesOutOfRange = "23:60";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithMinutesOutOfRange, ":");
        });
    }
    @Test
    void MinuteIntNegative(){
        String timeStrWithMinutesOutOfRange = "05:-1";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithMinutesOutOfRange, ":");
        });
    }
    @Test
    void SecondIntTooHigh(){
        String timeStrWithSecondsOutOfRange = "23:50:60";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithSecondsOutOfRange, ":");
        });
    }
    @Test
    void SecondIntNegative(){
        String timeStrWithSecondsOutOfRange = "05:50:-1";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithSecondsOutOfRange, ":");
        });
    }
    @Test
    void NanoSecondIntTooHigh(){
        String timeStrWithNanoSecondsOutOfRange = "23:50:10:1_111_111_111";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithNanoSecondsOutOfRange, ":");
        });
    }
    @Test
    void NanoSecondIntNegative(){
        String timeStrWithNanoSecondsOutOfRange = "05:50:1:-1";
        assertThrows(NumberFormatException.class, ()->{
            DataExtractor.getTimeListFromString(timeStrWithNanoSecondsOutOfRange, ":");
        });
    }
    @Test
    void EmptyStringShouldReturnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            DataExtractor.getTimeListFromString("", ":");
        });
    }
    @Test
    void StringWithJustSeparatorsShouldReturnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            DataExtractor.getTimeListFromString("::::", ":");
        });
    }

}