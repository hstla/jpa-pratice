package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setAge(300);

            Member member2 = new Member();
            member2.setUsername("hello2");
            member2.setAge(400);

            em.persist(member1);
            em.persist(member2);


            List<Member> resultList = em.createQuery("select m from Member as m where m.username=:username", Member.class)
                    .setParameter("username", "hello2")
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member username = " + member.getUsername());
            }


//            em.persist(member1);
//            em.persist(member2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
