package com.user.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class PackageOrganizing {

	//TODO: Revisar pacotes de arquitetura

	@Test
	public void incoming_must_not_see_outcoming() {
		JavaClasses classes = new ClassFileImporter().importPackages("com.user");

		ArchRule rule = noClasses().that().resideInAPackage("..user.interfaces.incoming..").and()
				.haveSimpleNameNotEndingWith("TestIT").should().accessClassesThat()
				.resideInAPackage("..user.interfaces.outcoming..")
				.because("Should access package domain first");

		rule.check(classes);

	}

	@Test
	public void domain_must_not_see_incoming() {

		JavaClasses classes = new ClassFileImporter().importPackages("com.user");

		ArchRule rule = noClasses().that().resideInAPackage("..user.domain..").and()
				.haveSimpleNameNotEndingWith("TestIT").should().accessClassesThat()
				.resideInAPackage("..user.interfaces.incoming..")
				.because("Classes are not supposed to be aware of who is calling them");

		rule.check(classes);
	}

	@Test
	public void outcoming_must_not_see_domain_or_incoming() {

		JavaClasses classes = new ClassFileImporter().importPackages("com.user");

		ArchRule rule = noClasses().that().resideInAPackage("..user.interfaces.outcoming..").and()
				.haveSimpleNameNotEndingWith("TestIT").should().accessClassesThat()
				.resideInAnyPackage("..user.interfaces.incoming..", "..user.domain..")
				.because("Classes are not supposed to be aware of who is calling them");

		rule.check(classes);
	}

}
