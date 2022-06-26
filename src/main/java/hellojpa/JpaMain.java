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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());

            List<Member> member = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

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
