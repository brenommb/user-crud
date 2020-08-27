package com.user.domain.company;

import com.user.domain.common.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "company")
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "company_seq", sequenceName = "sq_company_idt", allocationSize = 1)
public class CompanyEntity extends BaseEntity implements Serializable {

	@Id
	@Column(name = "idt_company")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	Long companyId;

	@Column(name = "num_company", nullable = false)
	Long companyNumber;

}
