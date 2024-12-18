package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * UserResource의 @Valid 설정 
 * -> @Size, @Past
 * 
 * @Entity : JPA 설정
 */
@Entity(name = "user_details")//테이블 이름과 같아야함
public class User {
	
	protected User() {
		
	}
	
	@Id //식별 
	@GeneratedValue //sequence
	private Integer id;
	
	@Size(min=2, message = "Name should have atleast 2 characters")
	//@JsonProperty("user_name") -> Json으로 반환 시 키 값 설정 (Bean 이름과 다르게 하고 싶은 경우 사용)
	private String name;
	
	@Past(message = "Birth Date should be in the past")
	//@JsonProperty("birth_date")
	private LocalDate birthDate;
	/**
	 * 일대다 관계 
	 * mappedBy 에는 Post.java의 User user
	 * @JsonIgnore : 응답 json에 포함 X
	 */
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
 	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}


