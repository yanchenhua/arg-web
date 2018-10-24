package ltd.ontsol.web.resource;

import java.util.List;

/**
 * Created by cn40580 at 2018-06-28 2:31 PM.
 */
public class GlobalCityResource {
    private String name;
    private Double longitude;
    private Double latitude;
    private List<GlobalShop> shops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<GlobalShop> getShops() {
        return shops;
    }

    public void setShops(List<GlobalShop> shops) {
        this.shops = shops;
    }
}
