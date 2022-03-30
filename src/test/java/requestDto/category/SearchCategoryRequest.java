package requestDto.category;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class SearchCategoryRequest {

    private Filter filter;
    private Paging paging;

    public SearchCategoryRequest(Filter filter) {
        this.filter = filter;
    }

    public SearchCategoryRequest(Paging paging) {
        this.paging = paging;
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Filter {

        private String name;
        private Integer sportId;

        public Filter(String name, Integer sportId) {
            this.name = name;
            this.sportId = sportId;
        }

        public Filter(String name) {
            this.name = name;
        }

        public Filter(Integer sportId) {
            this.sportId = sportId;
        }

        public Filter() {
        }

        public String getName() {
            return name;
        }

        public Integer getSportId() {
            return sportId;
        }
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

    public static class Paging {
        private Integer currentPage;
        private Integer itemsPerPage;
        private Integer totalItems;
        private Integer totalPages;

        public Paging(Integer currentPage, Integer itemsPerPage, Integer totalItems, Integer totalPages) {
            this.currentPage = currentPage;
            this.itemsPerPage = itemsPerPage;
            this.totalItems = totalItems;
            this.totalPages = totalPages;
        }

    public Paging(){

    }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public Integer getItemsPerPage() {
            return itemsPerPage;
        }

        public Integer getTotalItems() {
            return totalItems;
        }

        public Integer getTotalPages() {
            return totalPages;
        }
    }


}





