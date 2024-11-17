import java.util.Scanner;

class PlayerNode {
    int score;
    String playerName;
    PlayerNode left, right;

    public PlayerNode(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
}

public class SimpleLeaderboard {
    private PlayerNode root;

    // Add a new player to the leaderboard
    public void addPlayer(String playerName, int score) {
        root = insert(root, playerName, score);
    }

    private PlayerNode insert(PlayerNode node, String playerName, int score) {
        if (node == null) {
            return new PlayerNode(playerName, score);
        }
        if (score < node.score) {
            node.left = insert(node.left, playerName, score);
        } else if (score > node.score) {
            node.right = insert(node.right, playerName, score);
        }
        return node;
    }

    // Display the leaderboard in descending order
    public void displayLeaderboard() {
        System.out.println("Leaderboard:");
        displayDescending(root);
    }

    private void displayDescending(PlayerNode node) {
        if (node == null) return;
        displayDescending(node.right);
        System.out.println("Player: " + node.playerName + ", Score: " + node.score);
        displayDescending(node.left);
    }

    public static void main(String[] args) {
        SimpleLeaderboard leaderboard = new SimpleLeaderboard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Player");
            System.out.println("2. Display Leaderboard");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 1) {
                System.out.print("Enter player name: ");
                String playerName = scanner.nextLine();
                System.out.print("Enter player score: ");
                int score = scanner.nextInt();
                leaderboard.addPlayer(playerName, score);
            } else if (choice == 2) {
                leaderboard.displayLeaderboard();
            } else if (choice == 3) {
                System.out.println("Exiting...");
                scanner.close();
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}