import logger.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.database.SQLUtility;
import utils.database.TableTest;

import java.util.Date;

@Test(testName = "Add new record")
public class TestCase1 {

    @Test
    public void testSimulation() {
        Log.warn("TEST SIMULATION IS LAUNCHED");
        Assert.assertEquals((int) Math.pow(2, 6), 64, "Test failed");
    }

    @AfterMethod
    public void addNewTestRecord(ITestResult result) {
        Log.warn("ADD NEW TEST RECORD");
        SQLUtility.openConnection();
        Assert.assertTrue(TableTest.add(new models.Test(result.getTestName(), result.getStatus(), result.getInstanceName(),
                        new Date(result.getStartMillis()), new Date(result.getEndMillis()))), "Test record hasn't been added");
        SQLUtility.closeConnection();
    }
}