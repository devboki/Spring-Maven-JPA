package com.hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		//em은 로딩 시점에 딱 하나만 만들기
		EntityManager em = emf.createEntityManager();
		//트랜잭션 단위 중요
		EntityTransaction tx = em.getTransaction();
		//시작
		tx.begin();
		
		try { 
			
			/* 1) 저장
			Member member = new Member();
			member.setId(2L);
			member.setName("HelloB");
			-- 비영속
			em.persist(member);
			-- 영속 상태. 
			그러나 쿼리는 tansaction을 commit하는 시점에 DB에 보내게 되므로 현재 DB 변동 없음 */
			
			
			
			/* 2) 단순 단건 조회
			Member findMember = em.find(Member.class, 1L);
										//type, value
			System.out.println("findMember.id = " + findMember.getId());
			System.out.println("findMember.name = " + findMember.getName()); */
			
			
			
			/* 3) member 객체 조회
			List<Member> result = em.createQuery("select m from Member as m", Member.class) 
					.setFirstResult(0) //0부터
					.setMaxResults(10) //10까지의 값
					.getResultList();
			
			for (Member member : result) {
				System.out.println("member.name = " + member.getName());
			} */
			
			
			
			/* 4) 수정
			Member findMember = em.find(Member.class, 1L);
			findMember.setName("GoodbyeA"); 
			
			...setName(); 이후에 em.persist(findMember); 안 해도 됨 왜?
			JPA를 통해서 객체를 가져오면 JPA가 커밋 되는 시점에 객체가 변했는지 체크해서 업데이트 쿼리를 날려주기 때문 */
	
			
			
			/* 5) 삭제 
			em.remove(findMember); */
			
			
			
			
			/* 영속성 컨텍스트 이해1)
			Member member = new Member();
			member.setId(101L);
			member.setName("HelloJPA");
			
			System.out.println("=== BEFORE ===");
			em.persist(member);
			System.out.println("=== AFTER ===");
			
			Member findMember = em.find(Member.class, 101L);
			System.out.println("findMember.id = " + findMember.getId());
			System.out.println("findMember.name = " + findMember.getName());
			//이때 em.persist(member)로 인해 저장된 101L이 1차 캐시에 있기 때문에 DB로 SELECT 쿼리를 보내지 않고 조회 가능 */
			
			
			
			/* 영속성 컨텍스트 이해2) 
			Member findMember1 = em.find(Member.class, 101L); //DB에서 조회
			Member findMember2 = em.find(Member.class, 101L); //1차 캐시에서 조회되므로 쿼리는 1번만 실행된다
			
			System.out.println("result = " + (findMember1 == findMember2)); */
			
			
			
			/* 쓰기 지연 
			Member member1 = new Member(150L, "A");
			Member member2 = new Member(160L, "B");
			
			em.persist(member1);
			em.persist(member2);
			System.out.println("====================="); 
			한꺼번에 쿼리 flush
			<!-- <property name="hibernate.jdbc.batch_size" value="10"/> : 10개를 모아서 DB에 flush 하겠다는 뜻 --> */
			
			
			
			/* set + 준영속 상태
			Member member = em.find(Member.class, 150L);
			member.setName("CCCCC");
			//em.persist(member); 저장 필요 없음
			//em.update(member); update 명령 없음 update는 set으로 변경 하면 끝!
			
			em.detach(member);
			//특정 엔티티를 준영속 상태로 바꾸면 JPA에서 관리를 하지 않겠다는 뜻이 됨
			//실행시 update 쿼리 나가지 않음
			
			//em.clear();
			//영속성 컨텍스트 완전히 비우기
			
			System.out.println("=====================");  */
			
			
			
			//정상 작동시 commit
			tx.commit();
			
			
		} catch (Exception e) {
			//예외시 rollback
			tx.rollback();
		} finally {
			//마지막으로 꼭 EntityManager 닫기
			em.close();
		}
		
		//마무리는 Factory 닫기
		emf.close();
	}
}