package aforo255.com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aforo255.com.msawsaccount.model.api.AccountResponse;
import aforo255.com.msawsaccount.model.api.CustomerResponse;
import aforo255.com.msawsaccount.model.entity.AccountEntity;
import aforo255.com.msawsaccount.service.IAccountService;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {
    @Autowired
    private IAccountService service;

    @GetMapping
    public List<AccountResponse> listar() {

        return service.findAll()
                .stream()
                .map(this::mapAccountResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponse detalle(@PathVariable Integer id) {

        AccountEntity account = service.findById(id);
        return mapAccountResponse(account);
    }

    private AccountResponse mapAccountResponse(AccountEntity entity) {

        return AccountResponse.builder().IdAccount(entity.getIdAccount())
                .TotalAmount(entity.getTotalAmount())
                .customer(CustomerResponse.of(
                        entity.getCustomer().getIdCustomer(),
                        entity.getCustomer().getFullName()))
                .build();
    }

}
