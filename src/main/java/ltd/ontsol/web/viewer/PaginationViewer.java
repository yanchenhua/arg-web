package ltd.ontsol.web.viewer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cn40580 at 2018-06-18 5:56 PM.
 */
public class PaginationViewer {
    private List<?> objs;

    private Integer pageCount;
    private Integer pageSize;

    public PaginationViewer(List<?> objs, Integer pageCount, Integer pageSize) {
        this.objs = objs;
        this.pageCount = pageCount;
        this.pageSize = pageSize;
    }

    public List<?> getObjs() {
        return objs.stream().skip((pageCount - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public void setObjs(List<?> objs) {
        this.objs = objs;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSumCount() {
        return objs == null ? 0 : objs.size();
    }

}
