package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
	
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	/* @Column(name = "MEMBER_ID")
	private Long memberId; */
	//참조 대신 외래 키를 그대로 사용 -> 객체 설계를 테이블 설계에 맞춘 방식. 객체 그래프 탐색이 불가능한 문제점이 있음.
	//id가 아닌 member로 가져와야 객체 지향 모델링
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	//DB관계상 누가 1이고 누가 N인지 잘 생각해서 어노테이션을 적어줘야 함.
	//ManyToOne = N : 1
	//그리고 member 객체와 MEMBER_ID 외래 키를 매핑
	//Member class에서 orders 컬렉션을 @OneToMany(mappedBy = "...")로 선언해주면 양방향 매핑.
	//@OneToMany(mappedBy = "member")
	//private List<Order> orders = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL) 	//이 양방향 매핑은 비즈니스적으로 필요함
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	private LocalDateTime orderDate; //컬럼명은 보통 ORDER_DATE, order_date. 스프링부트를 쓰면 기본 설정으로 order_date 형식으로 바꿔줌
	
	@Enumerated(EnumType.STRING) //꼭 string.
	private OrderStatus status;

	
	
	
		
	public Member getMember() {
		return member;
	}
	
	public Member setMember() {
		return member;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/*
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	 */
	
	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	

	
}
