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

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setWorkPeriod(new Period());

            em.persist(member);

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
