package Practical;

import Practical.entity.Item;
import Practical.entity.Member;
import Practical.entity.Order;
import Practical.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            service(em); //TODO 비즈니스 로직
            tx.commit(); //트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void service(EntityManager em) {
        Member member = new Member();
        member.setName("hello");
        em.persist(member);
        Long id = member.getId();
        System.out.println("memberId is = " + id);

        Order order = new Order();
        order.setMember(member);
        em.persist(order);
        Long id1 = order.getId();
        System.out.println("orderId = " + id1);
        System.out.println("orderMember = " + order.getMember().getName());

        Item item1 = new Item();

        item1.setName("item1");

        em.persist(item1);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item1);
        orderItem.setOrder(order);
        em.persist(orderItem);
        System.out.println("orderItemId = " + orderItem.getId());
        System.out.println("orderItemOrder = " + orderItem.getOrder());
        System.out.println("orderItemItem = " + orderItem.getItem());

        em.remove(orderItem);
        em.remove(item1);
        em.remove(order);
        em.remove(member);
    }

}
