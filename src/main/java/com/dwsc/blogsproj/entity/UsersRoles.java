//package com.dwsc.blogsproj.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="users_roles")
//public class UsersRoles {
//	
//	@Column(name="user_id")
//	@ManyToMany(cascade=)
//	private long userId;
//	
//	@Column(name="role_id")
//	private long roleId;
//	
//	public UsersRoles() {}
//
//	public long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
//
//	public long getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(long roleId) {
//		this.roleId = roleId;
//	}
//
//	@Override
//	public String toString() {
//		return "UsersRoles [userId=" + userId + ", roleId=" + roleId + "]";
//	}
//	
//	
//}
