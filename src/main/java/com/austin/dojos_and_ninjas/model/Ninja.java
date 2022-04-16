package com.austin.dojos_and_ninjas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
	@Table(name="ninjas")
	public class Ninja {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotNull
	    @Size(min = 1, max = 50, message = "first name is required for a ninja.")
	    private String firstName;
	    
	    @NotNull
	    @Size(min = 2, max = 50, message = "last name is required for a ninja.")
	    private String lastName;
	    
	    @Min(1)
	    private int age;
	    
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    
	    // MANY TO ONE - ONE dojo can have MANY ninjas 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="dojo_id")
	    private Dojo dojo;
	    
	    // MANY TO ONE -- MANY ninjas made by ONE user
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User userThatRegisteredNinja;
	    
	    // CONSTRUCTOR
	    public Ninja() {}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
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

		public Dojo getDojo() {
			return dojo;
		}

		public void setDojo(Dojo dojo) {
			this.dojo = dojo;
		}

		public User getUserThatRegisteredNinja() {
			return userThatRegisteredNinja;
		}

		public void setUserThatRegisteredNinja(User userThatRegisteredNinja) {
			this.userThatRegisteredNinja = userThatRegisteredNinja;
		}

	    // Getters and Setters 
	    
		
	    
	    
	}
