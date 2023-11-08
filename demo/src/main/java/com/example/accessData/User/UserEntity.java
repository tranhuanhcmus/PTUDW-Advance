package com.example.accessData.User;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String iDString;
	
	private String userName;
	
	@JsonIgnore
	@Size(min = 6,message = "password must have six words")
	private String password;

	public String getiDString() {
		return iDString;
	}

	public void setiDString(String iDString) {
		this.iDString = iDString;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public UserEntity() {};

	public UserEntity(String iDString, String userName,
			@Size(min = 6, message = "password must have six words") String password) {
		super();
		this.iDString = iDString;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserEntity [iDString=" + iDString + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
	
	

}
