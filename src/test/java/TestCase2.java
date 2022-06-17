import logger.Log;
import org.testng.*;
import org.testng.annotations.*;
import utils.database.SQLUtility;
import utils.database.TableSession;
import utils.database.TableTest;

import java.util.ArrayList;
import java.util.Date;

@Test(testName = "Work with test data")
public class TestCase2 {

    private final ArrayList<models.Test> tests = new ArrayList<>();

    @BeforeTest
    public void openDatabaseConnection() {
        Log.warn("OPEN DATABASE CONNECTION");
        SQLUtility.openConnection();
    }

    @Test(dataProvider = "getTestRecordsForTest", dataProviderClass = TestDataPreparation.class)
    public void testSimulation(models.Test test) {
        Log.warn("TEST: " + test.getName() + " IS LAUNCHED");
        tests.add(test);
        int statusID = (int) (Math.random() * 3) == (int) (Math.random() * 3) ? 1 : 2;
        Assert.assertEquals(1, statusID, "Test failed");
    }

    @AfterMethod
    public void updateTestRecord(ITestResult result) {
        Log.warn("UPDATE TEST RECORD");
        models.Test completedTest = tests.get(tests.size() - 1);
        completedTest.setSession_id(TableSession.get(new Date(result.getStartMillis())).getId());
        completedTest.setStart_time(new Date(result.getStartMillis()));
        completedTest.setEnd_time(new Date(result.getEndMillis()));
        completedTest.setStatus_id(result.getStatus());
        Assert.assertTrue(TableTest.update(completedTest.getId(), completedTest), "Record hasn't been updated");
    }

    @AfterTest
    public void restoreDatabase() {
        Log.warn("CLOSE DATABASE CONNECTION AND RESTORE DATABASE");
        for (models.Test test: tests) {
            Assert.assertTrue(TableTest.delete(test.getId()), "Record hasn't been deleted");
        }
        SQLUtility.closeConnection();
    }
}