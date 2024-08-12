
package pro.ddsr.backend_dashboard_ecommerce.role.persistence;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.ToString;
import pro.ddsr.backend_dashboard_ecommerce.permission.persistence.Permission;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "role_id")
    Long roleId;

    @Column(name = "name", nullable = false, unique =  true)
    @Enumerated(EnumType.STRING)
    RoleEnum name;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permissionId")
    )
    private Set<Permission> permissions;
}