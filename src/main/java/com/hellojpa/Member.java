package com.hellojpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
//@Getter, @Setter : Stringboot에서는 lombok으로 간편하게
//@Table(name = "db table명") 클래스명과 db 테이블명이 다를 때 매핑.
//@Table(uniqueConstratints = "unique이름") unique = true로 column 옵션을 줄 수 있지만 이름을 지정해줄 수 없기 때문에 Table에 값을 줌
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임. 주로 MySQL의 AUTO_INCREMENT
	//@GeneratedValue(strategy = GenerationType.SEQUENCE) 데이터마다 다른 시퀀스를 사용하고 싶다면 @SequenceGenerator()
	private Long id;
	
	@Column(name = "name", insertable = true, updatable = true, nullable = true) 
	//객체명과 db 칼럼명이 다를 때, 등록 가능 여부(기본값 true), 변경 가능 여부(기본값 true), null false = not null(기본값 true) 
	//columnDefinition = "varchar(100) default 'EMPTY'" : 문구가 그대로 실행됨 
	private String username;
	
	private Integer age;
	//private BigDecimal age;
	//아주 큰 숫자나 소숫점 
	
	@Enumerated(EnumType.STRING)
	//enum을 사용할 때 쓰는 어노테이션. 기본 : ORDINAL(em 순서를 DB에 저장. 쓰지 않기!), STRING(이름을 DB에 저장)
	private RoleType roleType;
	
	@Temporal(TemporalType.TIMESTAMP)
	//Type(DATE, TIME, TIMESTAMP) LocalDate, LocalDateTime을 사용할 때는 생략 가능
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	//최신 하이버네이트 버전
	private LocalDate testLocalDate; //년월
	private LocalDateTime testLocalDateTime; //년월일
	
	@Lob
	//지정할 수 있는 속성 없음. 필드 타입이 문자면 CLOB, 나버지는 BLOB
	private String description;
	
	@Transient
	//DB와 매핑하지 않는, 메모리에서만 쓰는 데이터일 때
	private int temp;
	
	//JPA는 디폴트 생성자가 있어야 함
	public Member() {
		
	}
	
	
	/*
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
	} */
	
	
}
