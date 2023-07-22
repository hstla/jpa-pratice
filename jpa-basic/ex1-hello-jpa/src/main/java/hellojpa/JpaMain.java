package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("helloJPA");

            //영속
            System.out.println("==== BEFORE ====");
            em.persist(member);
            System.out.println("==== AFTER ====");

            Member member1 = em.find(Member.class, 101L);
            System.out.println("member1.getId() = " + member1.getId());  //select 쿼리 안나옴 1차 캐시 저장
            System.out.println("member1.getName() = " + member1.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
