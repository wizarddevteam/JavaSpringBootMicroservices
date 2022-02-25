package aforo255.com.msawsaccount.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Integer IdAccount ;
    private double TotalAmount ;
    private CustomerResponse customer ;
}
