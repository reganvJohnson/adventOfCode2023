package advent.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Util {

public static List<String> readFile(final String filename) throws Exception {

	   	List<String> result = new ArrayList<>();
        String inLine;
	    BufferedReader bufReader = new BufferedReader(new FileReader(filename));
        while ((inLine = bufReader.readLine()) != null) {
        	result.add(inLine);
        }
        return result;
    }
}
