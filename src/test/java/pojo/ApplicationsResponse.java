package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //
@AllArgsConstructor
@NoArgsConstructor
@Builder  // Allows object creation using builder pattern
@JsonInclude(Include.NON_NULL)
public class ApplicationsResponse {

    private Integer success;

    private Integer status;

    private List<Application> mortagage_applications;

}
