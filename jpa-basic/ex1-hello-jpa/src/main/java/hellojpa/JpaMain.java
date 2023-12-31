package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUserName("helloWorld");
            em.persist(member);
            String jpql = "select m from Member m where m.userName like '%hello%'" ;
            List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println(member1.getId());
                System.out.println(member1.getUserName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
