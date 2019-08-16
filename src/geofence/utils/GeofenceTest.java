package geofence.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class GeofenceTest {

    public static final String appURL ="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations";

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {

    }



    @AfterClass(alwaysRun = true)
    public void tearDownClass()  {
    }
}

