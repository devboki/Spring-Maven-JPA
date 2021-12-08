package jpabook.jpashop.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(length = 10) //이런 룰도 공통으로 쓸 수 있는 점이 좋음
	private String city;
	@Column(length = 20)
	private String street;
	@Column(length = 5)
	private String zipcode;
	
	public String fullAddress() {
		return getCity() + " " + getStreet() + " " + getZipcode();
	}
	
	
	
	//setter는 지우거나 private
	public String getCity() {
		return city;
	}
	private void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	private void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
	//값 타입은 equals, hashcode
	//이때 필드city, street, zipcode로 직접 접근하지 않고 get()로 접근하도록 바꿔주기
	//왜? 프록시일 때 계산이 안 됨. 프록시가 진짜 객체에 접근할 때를 생각해서 바꿔주는 것
	@Override
	public int hashCode() {
		return Objects.hash(getCity(), getStreet(), getZipcode());
		//return Objects.hash(city, street, zipcode)
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Address address = (Address) obj;
		return Objects.equals(getCity(), address.getCity())
				&& Objects.equals(getStreet(), address.getStreet())
				&& Objects.equals(getZipcode(), address.getZipcode());
		//return Objects.equals(city, other.city)
	}
}
