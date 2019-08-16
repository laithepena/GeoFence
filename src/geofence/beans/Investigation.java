package geofence.beans;

public class Investigation
{
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creatorAttUid == null) ? 0 : creatorAttUid.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((geoFence == null) ? 0 : geoFence.hashCode());
		result = prime * result + ((geoFenceType == null) ? 0 : geoFenceType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((market == null) ? 0 : market.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submarket == null) ? 0 : submarket.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
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
		Investigation other = (Investigation) obj;
		if (creatorAttUid == null) {
			if (other.creatorAttUid != null)
				return false;
		} else if (!creatorAttUid.equals(other.creatorAttUid))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (geoFence == null) {
			if (other.geoFence != null)
				return false;
		} else if (!geoFence.equals(other.geoFence))
			return false;
		if (geoFenceType == null) {
			if (other.geoFenceType != null)
				return false;
		} else if (!geoFenceType.equals(other.geoFenceType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (market == null) {
			if (other.market != null)
				return false;
		} else if (!market.equals(other.market))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submarket == null) {
			if (other.submarket != null)
				return false;
		} else if (!submarket.equals(other.submarket))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		return true;
	}

	private String market;

    private GeoFence geoFence;

    private String endDate;

    private String geoFenceType;

    private String name;

    private String timeZone;

    private String submarket;

    private String id;

    private String startDate;

    private String creatorAttUid;

    private String status;

    public String getMarket ()
    {
        return market;
    }

    public void setMarket (String market)
    {
        this.market = market;
    }

    public GeoFence getGeoFence ()
    {
        return geoFence;
    }

    public void setGeoFence (GeoFence geoFence)
    {
        this.geoFence = geoFence;
    }

    public String getEndDate ()
    {
        return endDate;
    }

    public void setEndDate (String endDate)
    {
        this.endDate = endDate;
    }

    public String getGeoFenceType ()
    {
        return geoFenceType;
    }

    public void setGeoFenceType (String geoFenceType)
    {
        this.geoFenceType = geoFenceType;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTimeZone ()
    {
        return timeZone;
    }

    public void setTimeZone (String timeZone)
    {
        this.timeZone = timeZone;
    }

    public String getSubmarket ()
    {
        return submarket;
    }

    public void setSubmarket (String submarket)
    {
        this.submarket = submarket;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    public String getCreatorAttUid ()
    {
        return creatorAttUid;
    }

    public void setCreatorAttUid (String creatorAttUid)
    {
        this.creatorAttUid = creatorAttUid;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [market = "+market+", geoFence = "+geoFence+", endDate = "+endDate+", geoFenceType = "+geoFenceType+", name = "+name+", timeZone = "+timeZone+", submarket = "+submarket+", id = "+id+", startDate = "+startDate+", creatorAttUid = "+creatorAttUid+", status = "+status+"]";
    }
}
			