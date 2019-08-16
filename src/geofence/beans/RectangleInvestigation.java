package geofence.beans;

import java.util.Map;

public class RectangleInvestigation extends Investigation{

    private Map<String, Float> geoFence;

    public Map<String, Float> getRectangleLatLong() {
        return geoFence;
    }
    public void setRectangleLatLong(Map<String, Float> RectangleLatLong) {
        this.geoFence = RectangleLatLong;
    }

}

