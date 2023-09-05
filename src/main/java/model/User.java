package model;


import java.util.Collections;
import java.util.Set;

import io.quarkiverse.renarde.security.RenardeUser;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(columnNames = {"tenantId", "authId"}))
public class User extends PanacheEntity implements RenardeUser {

    @Column(nullable = false)
    public String email;
    public String firstName;
    public String lastName;

    public String tenantId;
    public String authId;

    @Override
    public Set<String> roles() {
        return Collections.emptySet();
    }

    @Override
    public String userId() {
        return tenantId+"/"+authId;
    }

	@Override
	public boolean registered() {
		return true;
	}

    //
    // Helpers

    public static User findByAuthId(String tenantId, String authId) {
        return find("tenantId = ?1 AND authId = ?2", tenantId, authId).firstResult();
    }
}