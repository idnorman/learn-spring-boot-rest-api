package kelaskoding.restapi.dto;

public class SearchData {
    private String searchKey;
    private String otherSearchKey;
    public String getSearchKey() {
        return searchKey;
    }

    public String getOtherSearchKey() {
        return otherSearchKey;
    }

    public void setOtherSearchKey(String otherSearchKey) {
        this.otherSearchKey = otherSearchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
