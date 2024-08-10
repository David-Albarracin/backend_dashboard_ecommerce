package pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.Set;

import pro.ddsr.backend_dashboard_ecommerce.domain.dto.ProductProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;

public interface EmployeesWithOrdersDto {

    // informacion del empleado
    Long getEmployeeId();
    String getFirstName();
    String getSecondName();
    String getFirstSurname();
    String getSecondSurname();
    String getDocumentNumber();
    String getPhoneNumber();

    Set<CustomerProjection> getCustomers();

    // informacion (proyectada) del cliente del empleado
    interface CustomerProjection {
         Long getCustomerId();
        String getFirstName();
        String getLastName();
        Set<EmployeeOrder> getOrders();
    }

    // informacion (proyectada -_- ) de las ordenes del cliente
    public interface EmployeeOrder{

        Long getOrderId();
        LocalDate getOrderDate();
        LocalDate getExpectedDate();
        LocalDate getDeliverDate();
        OrderStatus gOrderStatus();
        Set<EmployeeOrderDetail> getOrderDetails();


    }

    // informacion (proyectada -_- , otra vez...) de los detalles de orden del cliente
    public interface EmployeeOrderDetail{

        Long getOrderDetailId();
        Byte getAmount();
        Integer getUnitPrice();
        Integer getTotalPrice();
        Short getOrderLine();
        ProductProjection getProduct();
    }

}
