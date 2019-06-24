package com.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="role_name")
	private String name;
	
	@Column(name="domain")
	private String domain;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_Dt")
	private Date createdDate;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role", 
				joinColumns=@JoinColumn(name="role_id", referencedColumnName="id")
				,inverseJoinColumns=@JoinColumn(name="user_id", referencedColumnName="id"))
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Role(){}

	public Role(String name, String domain, Date createdDate) {
		super();
		this.name = name;
		this.domain = domain;
		this.createdDate = createdDate;
	}
	
	
}
