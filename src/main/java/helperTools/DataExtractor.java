package helperTools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataExtractor {
    private static final Logger logger = LogManager.getLogger(DataExtractor.class);
    /**
     * @param string with the times inside
     * @param separator the chars the separate time ints.
     * @return List order is Hours, Minutes, Second and Nano-Seconds
     * @throws IllegalArgumentException thrown if separator is not found
     * @throws NumberFormatException thrown is NaN or number not in correct range
     * @apiNote if second or Nano-Seconds are not found their values will default to 0
     */
    public static List<Integer> getTimeListFromString(String string, String separator){
        if(!string.contains(separator)){
            throw new IllegalArgumentException("Could not find separator in string!");
        }
        List<Integer> timeParts = Stream.of(string.split(separator)).map(Integer::valueOf)
                    .collect(Collectors.toList());

        if(timeParts.size() < 2){
            throw new IllegalArgumentException("Could not find valid time!");
        }

        //Checking if hours are in range of 0 - 23
        if(timeParts.get(0) < 0 || timeParts.get(0) > 23){
            throw new NumberFormatException("Hours range is 0 - 23");
        }

        //Checking if minutes are in range of 0 - 59
        if(timeParts.get(1) < 0 || timeParts.get(1) > 59){
            throw new NumberFormatException("Minutes range is 0 - 59");
        }



        //If Second or Nano-Seconds are not in list we just add 0s
        if(timeParts.size() < 3){
            timeParts.add(0);//Adding Seconds
            timeParts.add(0);//Adding Milliseconds
        }else if(timeParts.size() < 4){
            timeParts.add(0);//Adding Milliseconds
        }

        //Checking if seconds are in range of 0 - 59
        if(timeParts.get(2) < 0 || timeParts.get(2) > 59){
            throw new NumberFormatException("Seconds range is 0 - 59");
        }

        //Checking if nano-seconds are in range of 0 to 999,999,999
        if(timeParts.get(3) < 0 || timeParts.get(3) > 999_999_999){
            throw new NumberFormatException("Nano-Seconds range is 0 - 999'999'999");
        }

        return timeParts;
    }
}
