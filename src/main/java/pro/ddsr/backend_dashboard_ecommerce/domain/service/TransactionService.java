
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.TransactionDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.PayMethodRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.TransactionRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.PayMethod;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Transaction;

@Service
public class TransactionService {
    // Define service methods here
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PayMethodRepository payMethodRepository;

    @Autowired
    OrderRepository orderRepository;
    
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

    public List<Transaction> findByPayMethod(Long id) {
        return this.transactionRepository.findByPayMethod(id);
    }

    public List<Transaction> findByCustomer(Long id) {
        return this.transactionRepository.findByCustomer(id);
    }

    public Transaction save(TransactionDto transaction) {
        Transaction transactionItem = transaction.toTransaction(payMethodRepository.findById(transaction.getPayMethod()).get(),
                orderRepository.findById(transaction.getOrder()).get());
        return this.transactionRepository.save(transactionItem);
    }

    public Optional<Transaction> update(Long id, TransactionDto transactionDto) {
        Optional<Transaction> optionalTransaction = this.transactionRepository.findById(id);

        if (!optionalTransaction.isPresent()) {
            return Optional.empty();
        }

        // Buscar payMethod y Order
        PayMethod payMethod = payMethodRepository.findById(transactionDto.getPayMethod())
                .orElseThrow(() -> new IllegalArgumentException("PayMethod no encontrado"));
        Order order = orderRepository.findById(transactionDto.getOrder())
                .orElseThrow(() -> new IllegalArgumentException("Order no encontrado"));

        // Crear y actualizar el pago
        Transaction transactionItem = transactionDto.toTransaction(payMethod, order);
        transactionItem.setTransactionId(id); // Asegúrate de que el ID esté establecido para la actualización

        try {
            return Optional.of(this.transactionRepository.save(transactionItem));
        } catch (DataIntegrityViolationException e) {
            // Manejar conflicto de integridad de datos (clave única)
            return Optional.empty();
        }
    }
}
