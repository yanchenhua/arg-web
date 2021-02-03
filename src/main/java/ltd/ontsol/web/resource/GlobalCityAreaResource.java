package ltd.ontsol.web.resource;

import java.util.List;

public class GlobalCityAreaResource {
    private String name;


    public List<GlobalAreaResource> getArea() {
        return area;
    }

    public void setArea(List<GlobalAreaResource> list) {
        this.area = list;
    }

    private List<GlobalAreaResource> area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
