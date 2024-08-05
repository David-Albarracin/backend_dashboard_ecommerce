
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

/* import jakarta.persistence.Column;
import jakarta.persistence.Embeddable; */
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
/* import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; */
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="account_role")
public class AccountRole {

    @EmbeddedId
    AccountRolePk id;
    
    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
    
    public AccountRolePk getId() {
        return id;
    }

    public void setId(AccountRolePk id) {
        this.id = id;
    }
}
