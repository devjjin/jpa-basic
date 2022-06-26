package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address copyAddress = new Address("city", "street", "10000");

            Member member2 = new Member();
            member2.setUsername("member2");
            member1.setHomeAddress(address);
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        // 멤버만 조회
        System.out.println("username = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        // 멤버&팀 조회
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = new Team();
        System.out.println("team.getName() = " + team.getName());
    }
}
