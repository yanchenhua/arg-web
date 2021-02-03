package ltd.ontsol.web.resource;

import java.util.List;

/**
 * Created by cn40580 at 2018-06-28 2:29 PM.
 */
public class GlobalResource {
    private String name;
    private List<GlobalCityResource> list;


    public List<GlobalCityAreaResource> getCity() {
        return city;
    }

    public void setCity(List<GlobalCityAreaResource> city) {
        this.city = city;
    }

    private List<GlobalCityAreaResource> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GlobalCityResource> getList() {
        return list;
    }

    public void setList(List<GlobalCityResource> list) {
        this.list = list;
    }
}
