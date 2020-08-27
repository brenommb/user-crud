package com.user.domain.company;

import com.user.domain.common.exceptions.NotFoundException;
import com.user.infrastructure.MessageErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyEntity findByCompanyNumber(final Long companyNumber) {
		return companyRepository.findByCompanyNumber(companyNumber)
				.orElseThrow(() -> new NotFoundException(MessageErrorCode.COMPANY_NOT_FOUND, singletonList(companyNumber.toString())));

	}

}
