package dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PagingRequest {

private Paging paging;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public static class Paging{
        private Integer itemsPerPage;
        private Integer currentPage;
        private Integer totalItems;
        private Integer totalPages;


}

    public interface PagingBuilder{

        PagingBuilder setItemsPerPage(Integer itemsPerPage);
        PagingBuilder setCurrentPage(Integer currentPage);
        PagingBuilder setTotalItems(Integer totalItems);
        PagingBuilder setTotalPages(Integer totalPages);
        PagingRequest build();

    }


    public static class PagingBuilderImpl implements PagingBuilder{

        Paging paging = new Paging();
        @Override
        public PagingBuilder setItemsPerPage(Integer itemsPerPage) {
            paging.itemsPerPage = itemsPerPage;
            return this;
        }

        @Override
        public PagingBuilder setCurrentPage(Integer currentPage) {
            paging.currentPage = currentPage;
            return this;
        }

        @Override
        public PagingBuilder setTotalItems(Integer totalItems) {
            paging.totalItems = totalItems;
            return this;
        }

        @Override
        public PagingBuilder setTotalPages(Integer totalPages) {
            paging.totalPages = totalPages;
            return this;
        }

        @Override
        public PagingRequest build() {
            return new PagingRequest(paging);
        }
    }




}
