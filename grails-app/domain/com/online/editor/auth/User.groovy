package com.online.editor.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	String username
	String firstname
	String lastname
	String dob
	String street
	String city
	String email
	String password
	String confirm_password
	String gender
    String forgotPasswordUUID

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
	}

	static constraints = {
		password blank: false, password: true/*,matches:'((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})'*/
		username  blank: false,unique: true
		firstname nullable: true
		lastname nullable: true
		email email:true,nullable: true
		street nullable: true
		city nullable:true
		dob nullable: true
		gender nullable: true
        forgotPasswordUUID nullable: true
	}

	static mapping = {
		password column: '`password`'
	}
	static transients = ['confirm_password']
}
