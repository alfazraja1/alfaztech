package com.alfaztech.authService.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Users implements UserDetails{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String fullName;
	 
	private String fNmae;
	 
	private String lName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private int age;
	private String gender;
	private String mobile;
	private String imageUrl;
	@CreationTimestamp
    @Column(updatable = false, name = "created_at")
	private Date createdAt;
	@UpdateTimestamp
    @Column(name = "updated_at")
	private Date updatedAt;
	public Users(Long id, String fNmae, String lName, String email, String password, int age, String gender,
			String mobile, String imageUrl) {
		super();
		this.id = id;
		this.fNmae = fNmae;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.imageUrl = imageUrl;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getFullName() {
		return fullName;
	}
	public Users setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getfNmae() {
		return fNmae;
	}
	public void setfNmae(String fNmae) {
		this.fNmae = fNmae;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public Users setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Users setPassword(String password) {
		this.password = password;
		return this;
	}
	public int getAge() {
		return age;
	}
	public Users setAge(int age) {
		this.age = age;
		return this;
	}
	public String getGender() {
		return gender;
	}
	public Users setGender(String gender) {
		this.gender = gender;
		return this;
	}
	public String getMobile() {
		return mobile;
	}
	public Users setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  List.of();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
	

}
