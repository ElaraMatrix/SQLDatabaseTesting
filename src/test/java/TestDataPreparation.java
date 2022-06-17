import logger.Log;
import models.Test;
import org.testng.annotations.DataProvider;
import utils.ConfigManager;
import utils.SystemDataUtility;
import utils.database.TableAuthor;
import utils.database.TableTest;

import java.util.ArrayList;

public class TestDataPreparation {

    @DataProvider(name = "getTestRecordsForTest")
    public static Object[][] getTestRecordsForTest() {
        Log.warn("GET TEST RECORDS FOR TEST");
        ArrayList<Test> prepared = getPreparedTestRecords();
        Test[][] tests = new Test[prepared.size()][1];
        for (int i = 0; i < tests.length; i++) {
            tests[i][0] = TableTest.copy(prepared.get(i));
        }
        return tests;
    }

    static ArrayList<Test> getPreparedTestRecords() {
        Log.warn("PREPARATION TEST RECORDS FOR TEST");
        ArrayList<Integer> IDs = getDoubleDigitIDList(ConfigManager.getTestsNumber());
        ArrayList<Test> tests = new ArrayList<>();
        for (int i = 0; i < IDs.size(); i++) {
            tests.add(TableTest.get(IDs.get(i)));
            tests.get(i).setEnv(SystemDataUtility.getThisEnvName());
            tests.get(i).setAuthor_id(TableAuthor.getThisAuthor().getId());
        }
        return tests;
    }

    static ArrayList<Integer> getDoubleDigitIDList(int size) {
        Log.info("Get " + size + " double digit numbers");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 10; i < 1000; i++) {
            char[] number = String.valueOf(i).toCharArray();
            char previous = number[0];
            for (int j = 1; j < number.length; j++) {
                if (number[j] == previous && result.size() < size) result.add(i);
                previous = number[j];
            }
        }
        return result;
    }
}