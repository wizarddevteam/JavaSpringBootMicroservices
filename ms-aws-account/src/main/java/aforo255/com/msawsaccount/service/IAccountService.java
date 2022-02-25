package aforo255.com.msawsaccount.service;

import java.util.List;

import aforo255.com.msawsaccount.model.entity.AccountEntity;

public interface IAccountService {

    public List<AccountEntity> findAll();
    public AccountEntity findById (Integer id );
    public AccountEntity save (AccountEntity account);
}
