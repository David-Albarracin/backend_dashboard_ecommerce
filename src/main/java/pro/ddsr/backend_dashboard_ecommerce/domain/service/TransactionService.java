
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.TransactionRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Transaction;

@Service
public class TransactionService {
    // Define service methods here
    @Autowired
    TransactionRepository transactionRepository;
    
    @Transactional
    public Optional<Transaction> delete(Long id) {
        Optional<Transaction> optionalTransaction = this.transactionRepository.findById(id);
        optionalTransaction.ifPresent(
            TransactionFound -> {
                this.transactionRepository.delete(TransactionFound);
            }
        );
        return optionalTransaction;
    }
 
    public List<Transaction> findAll() {
        return (List<Transaction>) this.transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return this.transactionRepository.findById(id);
    }

    public Transaction save(Transaction Transaction) {
        return this.transactionRepository.save(Transaction);
    }

    public Optional<Transaction> update(Long id, Transaction transaction) {
        Optional<Transaction> optionalTransaction = this.transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transactionItem = optionalTransaction.orElseThrow();
            //SETS
            
            return Optional.of(this.transactionRepository.save(transactionItem));
        }
        return optionalTransaction;
    }
}
