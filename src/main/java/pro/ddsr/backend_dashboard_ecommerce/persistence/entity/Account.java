package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "account_id")
    private Long acccountId;

    @Column(unique = true)
    String username;
    
    String password;

    @Column(name = "is_enable")
    boolean isEnable;

    @Column(name = "account_no_expired")
    boolean accountNoExpired;

    @Column(name = "account_no_locked")
    boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    boolean credentialNoExpired;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "account_role",
        joinColumns = @JoinColumn ( name = "account_id" ,referencedColumnName = "account_id"),
        inverseJoinColumns = @JoinColumn ( name = "role_id", referencedColumnName = "role_id")
    )
    private Set<Role> roles;

}
