package com.user.domain.user;

import com.user.domain.common.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "user_seq", sequenceName = "sq_user_idt", allocationSize = 1)
public class UserEntity extends BaseEntity implements Serializable {

	@Id
	@Column(name = "idt_user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	Long userId;

	@Column(name = "idt_company", nullable = false)
	Long companyId;

	@Size(max = 255)
	@Column(name = "des_email", nullable = false)
	String email;

	@Column(name = "dat_birth", nullable = false)
	LocalDate birthDate;

}
