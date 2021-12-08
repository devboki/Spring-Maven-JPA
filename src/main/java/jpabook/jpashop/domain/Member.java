package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//@Table(indexes = @Index...) 인덱스 설정도 가급적이면 개발시에 적어주기
public class Member extends BaseEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) //생략하면 auto와 같음
	@Column(name = "MEMBER_ID")
	private Long id;
	
	@Column(length = 10) //제약조건은 개발시에 적어두는 것이 좋음
	private String name;
	
	@OneToMany(mappedBy = "member") //양방향 매핑. 읽기만 하는 orders
	private List<Order> orders = new ArrayList<>();
	//예제니까 멤버에서 orders를 양방향 매핑 해주었지만 실제로는 필요하지 않은 매핑
	
	@Embedded
	private Address address;
	
	
	
	//setter는 고민해서 만들 필요가 있음
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
