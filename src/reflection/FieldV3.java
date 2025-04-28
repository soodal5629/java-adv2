package reflection;

import reflection.data.Team;
import reflection.data.User;

/**
 * 리플렉션을 사용하는 이유 - 객체 필드에 null을 저장하면 안될 때
 */
public class FieldV3 {
    public static void main(String[] args) {
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);

        System.out.println("===== before =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);

        // 객체가 많아질수록 코드가 무식하고 드러워진다.
        if(user.getId() == null) {
            user.setId("");
        }

        if(user.getName() == null) {
            user.setName("");
        }

        if(user.getAge() == null) {
            user.setAge(0);
        }

        if(team.getId() == null) {
            team.setId("");
        }

        if(team.getName() == null) {
            team.setName("");
        }
        System.out.println("===== after =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
    }
}
