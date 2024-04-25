package br.com.erudio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

@Entity
@Table(name = "users")
public class User implements UserDetails ,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;
	
	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;
	
	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER) // assim que eu carregar o usuário, eu carrego as permissões dele (no caso do EAGER)
	@JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
		inverseJoinColumns = {@JoinColumn (name = "id_permission")}) //ja aqui eu digo qual a tabela de junção, assim como quem dessa classe 
	// ira fazer conexão (id_user) e quem da outra tabela faz o mesmo (id_permission). Aqui tbm serve para mapear essa tabela do meio.
	private List<Permission> permissions;
	
	public User() {
	} // JPA precisa de uma construtor padrão
	
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : permissions) {
			roles.add(permission.getAuthority());
		}
		return roles;
	}
	// esse getRoles é uma quebra da conveção, mas é necessário implementar devido ao UserDetails
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public List<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, fullName, id, password,
				permissions, userName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialsNonExpired, other.credentialsNonExpired)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(permissions, other.permissions) && Objects.equals(userName, other.userName);
	}
	
	
}
