package com.mgoode.tsl_timing_api.users.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Data
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(unique = true, nullable = false)
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@CreatedDate
	private LocalDateTime dateCreated;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	private LocalDateTime deletedAt;
	
	private List<RoleType> roles;

	public User() {
		roles = new ArrayList<>();
	}
	
}

