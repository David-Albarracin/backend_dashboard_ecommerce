package pro.ddsr.backend_dashboard_ecommerce.customer.domain.dto;

import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer.DocumentType;
import pro.ddsr.backend_dashboard_ecommerce.persistence.Audit;

/**
 * PROYECCION GENERAL.
 * Esta proyeccion se usa cuando se desee mostrar todos los empleados.
 * Pero no queremos ver todo acerca de cada empleado
*/
public interface CustomerProjection {

    Long getCustomerId();

    String getFirstName();

    String getDocumentNumber();

    DocumentType getDocumentType();

    Audit getAudit();
}
