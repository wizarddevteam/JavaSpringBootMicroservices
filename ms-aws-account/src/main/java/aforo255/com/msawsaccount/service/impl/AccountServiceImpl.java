package aforo255.com.msawsaccount.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aforo255.com.msawsaccount.model.entity.AccountEntity;
import aforo255.com.msawsaccount.repository.AccountRepository;
import aforo255.com.msawsaccount.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository _accountRepo;

    @Override
    public List<AccountEntity> findAll() {
        return (List<AccountEntity>) _accountRepo.findAll();
    }

    @Override
    public AccountEntity findById(Integer id) {
        return _accountRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public AccountEntity save(AccountEntity account) {
        return _accountRepo.save(account);
    }
}
