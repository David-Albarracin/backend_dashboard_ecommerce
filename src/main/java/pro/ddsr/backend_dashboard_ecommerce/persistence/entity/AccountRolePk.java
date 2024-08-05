package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountRolePk implements Serializable {
    
    @Column(name="account_id")
    private Long accountId;

    @Column(name="role_id")
    private Long roleId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}