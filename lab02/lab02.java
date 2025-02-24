import java.util.ArrayList;
import java.util.Random;

class Player {
    public String name;
    public String title = "";
    public int healthPoints = 100;
    public boolean isOnline;

    public String identify() {
        return "[" + this.healthPoints + "] | " + (title.isEmpty() ? "" : title + " ") + this.name;
    }
}

public class lab02 {
    public static void main(String[] args) {
        ArrayList < Player > players = new ArrayList < Player > ();

        for (int i = 0; i < 30; i++) {
            Player player = new Player();
            player.name = getRandomName();
            player.title = assignRandomTitle();
            player.isOnline = new Random().nextBoolean();
            players.add(player);
        }

        for (Player p: players) {
            if (p.isOnline){
                System.out.println(p.identify());
            }
        }
    }

    protected static String getRandomName() {
        String[] names = {"John", "Jim", "Jack", "George", "Kevin"};
        return names[new Random().nextInt(names.length)];
    }

    protected static String assignRandomTitle() {
        String[] titles = {"Darth", "adm.", "cpt.", "gen.", "cmdr."};
        return new Random().nextInt(2) == 0 ? titles[new Random().nextInt(titles.length)] : "";
    }
}