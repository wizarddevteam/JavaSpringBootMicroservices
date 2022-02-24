package aforo255.com.msawswithdrawal.model.api;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponse {
    private Integer id;
    private Integer accountId;
    private double amount;
    private String type ;

}
