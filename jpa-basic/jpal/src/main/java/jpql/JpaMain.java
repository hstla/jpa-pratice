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
            Team team1 = new Team();
            Team team2 = new Team();
            Team team3 = new Team();
            team1.setName("팀A");
            team2.setName("팀B");
            team3.setName("팀C");
            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            Member member1 = new Member("회원1", 10);
            member1.changeTeam(team1);
            Member member2 = new Member("회원2", 10);
            member2.changeTeam(team1);
            Member member3 = new Member("회원3", 10);
            member3.changeTeam(team2);
            Member member4 = new Member("회원4", 10);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);

            em.flush();
            em.clear();

            String query = "select m from Member as m where m = :member";
            Member findMember = em.createQuery(query, Member.class)
                    .setParameter("member", member1)
                    .getSingleResult();
            System.out.println("findMember = " + findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
