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
            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(new Address("home1", "street", "zi"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street", "zi"));
            member.getAddressHistory().add(new Address("old2", "street", "zi"));
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("==========================");
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("=============값 타입 조회=============");
            for (String favoriteFood : findMember.getFavoriteFoods()) {
                System.out.println(favoriteFood);
            }
            System.out.println("==========================");

            System.out.println("=============값 타입 변경=============");
            Address homeAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("changeCity", homeAddress.getStreet(), homeAddress.getZipcode()));

            //치킨 -> 백숙
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("백숙");

            findMember.getAddressHistory().remove(new Address("old1", "street", "zi"));
            findMember.getAddressHistory().add(new Address("new1", "street", "zi"));


            System.out.println("==========================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
