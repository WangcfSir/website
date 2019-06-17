package com.website.system.model;

/**
 * 协会信息（协会地址、电话、传真、邮箱、邮编）
 * @author yanqb
 * @since 2018-11-18
 */
public class Company {

	private String address;

	private String phone;

	private String email;

	private String zip;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Company(String address, String phone, String email, String zip) {
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Company{" +
				"address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", mail='" + email + '\'' +
				", zip='" + zip + '\'' +
				'}';
	}
}
