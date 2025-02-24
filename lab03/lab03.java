package lab03;

import java.util.ArrayList;
import java.util.Random;

class Player {
    private String name;
    private int healthPoints = 100;

    public Player(String name) {
        this.name = name;
    }

    public int attack(Player player) {
        int hitPoints = (new Random().nextInt(3) + 1) * 10;
        hitPoints -= hitPoints * (new Random().nextInt(31)) / 100;
        player.takeHit(hitPoints);
        return hitPoints;
    }

    private void takeHit(int hitPoints) {
        this.healthPoints = Math.max(this.healthPoints - hitPoints, 0);
    }

    public boolean isDead() {
        return this.healthPoints <= 0;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String identify() {
        return "[" + this.healthPoints + "] " + this.name;
    }
}

public class lab03 {
    public static void main(String[] args) {
        ArrayList<Player> players = generatePlayers(10);

        int round = 1;
        while (players.size() > 1) {
            Player attacker;
            Player target;
            do {
                attacker = getRandomPlayer(players);
                target = getRandomPlayer(players);
            } while (attacker == target);

            int hitPoints = attacker.attack(target);
            System.out.println("Round " + round + ": " + attacker.getName() + " dealt " + hitPoints + " damage to " + target.getName());
            
            players.removeIf(Player::isDead);
            displayLeaderboard(players);
            round++;
        }
        System.out.println("Winner: " + players.get(0).getName());
    }

    static ArrayList<Player> generatePlayers(int count) {
        String[] names = {"Luke", "Vader", "Obi-Wan", "Yoda", "Maul", "Dooku", "Anakin", "Leia", "Revan", "Thrawn"};
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(new Player(names[i % names.length]));
        }
        return players;
    }

    static Player getRandomPlayer(ArrayList<Player> players) {
        return players.get(new Random().nextInt(players.size()));
    }

    static void displayLeaderboard(ArrayList<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - i - 1; j++) {
                if (players.get(j).getHealthPoints() < players.get(j + 1).getHealthPoints()) {
                    Player temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
        System.out.println("Leaderboard:");
        for (Player p : players) {
            System.out.println(p.identify());
        }
        System.out.println("--------------------------------");
    }
}
