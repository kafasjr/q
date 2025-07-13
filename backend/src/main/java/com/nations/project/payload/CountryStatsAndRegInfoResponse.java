package com.nations.project.payload;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatsAndRegInfoResponse {
    private List<CountryStatsAndRegInfoDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalpages;
    private Boolean lastPage;

}
    

   