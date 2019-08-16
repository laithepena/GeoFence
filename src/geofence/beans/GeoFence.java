package geofence.beans;


public class GeoFence
{
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endLat == null) ? 0 : endLat.hashCode());
		result = prime * result + ((endLng == null) ? 0 : endLng.hashCode());
		result = prime * result + ((startLat == null) ? 0 : startLat.hashCode());
		result = prime * result + ((startLng == null) ? 0 : startLng.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoFence other = (GeoFence) obj;
		if (endLat == null) {
			if (other.endLat != null)
				return false;
		} else if (!endLat.equals(other.endLat))
			return false;
		if (endLng == null) {
			if (other.endLng != null)
				return false;
		} else if (!endLng.equals(other.endLng))
			return false;
		if (startLat == null) {
			if (other.startLat != null)
				return false;
		} else if (!startLat.equals(other.startLat))
			return false;
		if (startLng == null) {
			if (other.startLng != null)
				return false;
		} else if (!startLng.equals(other.startLng))
			return false;
		return true;
	}

private String startLat;

private String endLat;

private String endLng;

private String startLng;

public String getStartLat ()
{
return startLat;
}

public void setStartLat (String startLat)
{
this.startLat = startLat;
}

public String getEndLat ()
{
return endLat;
}

public void setEndLat (String endLat)
{
this.endLat = endLat;
}

public String getEndLng ()
{
return endLng;
}

public void setEndLng (String endLng)
{
this.endLng = endLng;
}

public String getStartLng ()
{
return startLng;
}

public void setStartLng (String startLng)
{
this.startLng = startLng;
}

@Override
public String toString()
{
return "ClassPojo [startLat = "+startLat+", endLat = "+endLat+", endLng = "+endLng+", startLng = "+startLng+"]";
}
}

