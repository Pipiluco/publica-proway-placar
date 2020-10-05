package life.pifrans.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import life.pifrans.models.Role;
import life.pifrans.services.RoleService;

/**
 * Classe Bean para manipulação de dados em páginas web
 */
@Named
@Scope(value = "view")
public class RoleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;

	@Autowired
	private Role role;

	private List<Role> roles;
	private static final String PAGE_ROLES = "/private/roles.jsf";

	@PostConstruct
	public void init() {
		roles = roleService.findAll();
	}

	public List<Role> findAll() {
		roles = new ArrayList<>();
		roles = roleService.findAll();
		return roles;
	}

	public void find() {
		role = roleService.find(role.getId());
	}

	public void renew() {
		role = new Role();
	}

	public String save() {
		roleService.insert(role);
		renew();
		return PAGE_ROLES;
	}

	public String update() {
		roleService.update(role);
		return PAGE_ROLES;
	}

	public String delete(Role role) {
		roleService.delete(role.getId());
		return PAGE_ROLES;
	}

	public String delete() {
		roleService.delete(role.getId());
		renew();
		return PAGE_ROLES;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
