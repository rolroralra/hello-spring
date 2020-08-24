package com.customer.biz.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.StringJoiner;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7010162711581277657L;
	
	private int id;
	@NotBlank
	@Length(max=40)
	private String name;
	
	@NotBlank
	@Length(max=45)
	private String address;
	
	@NotBlank
	@Email
	private String emailAddress;
	
	@AssertFalse(message="{errors.ngemail}")
	public boolean isNgEmail() {
		return emailAddress.matches(".*@ng.foo.baz$");
	}
	
	public boolean isFreeEmail() {
		return emailAddress.matches(".*@free.foo.baz$");
	}
}
