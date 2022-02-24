package aforo255.com.msawswithdrawal.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Integer id;
    private Integer accountId;
    private double amount;
    private String type ;

}
