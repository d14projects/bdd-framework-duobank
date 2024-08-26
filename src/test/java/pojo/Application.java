package pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //
@AllArgsConstructor
@NoArgsConstructor
@Builder  // Allows object creation using builder pattern
@JsonInclude(Include.NON_NULL)
public class Application {

    private String id;
    private String b_firstName;
    private String b_lastName;
    private String b_middleName;
    private String total_loan_amount;

}
