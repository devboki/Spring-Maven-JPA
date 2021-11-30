package com.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Getter, @Setter : Stringboot에서는 lombok으로 간편하게
//@Table(name = "db table명") 클래스명과 db 테이블명이 다를 때 매핑
public class Member {
	
	@Id
	private Long id;
	
	//@Column(name = "username") 객체명과 db 칼럼명이 다를 때 매핑
	private String name;
	
	public Member() {
		//JPA는 디폴트 생성자가 있어야 함
	}
	
	public Member(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	
}
