import java.util.ArrayList;
import java.util.Scanner;

class Undead {
    protected String name;
    protected int hp;
    protected boolean isAlive;

    public Undead(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
    }

    public void display() {
        System.out.println(name + "\nHP: " + hp + "\nState: " + (isAlive ? "alive" : "dead"));
    }

    public void takeDamage(int damage) {
        if (isAlive) {
            hp -= damage;
            if (hp <= 0) {
                hp = 0;
                isAlive = false;
            }
        }
    }

    public void revive() {
        if (!isAlive) {
            hp = 100; // Default HP for all undead
            isAlive = true;
        }
    }
}

class Zombie extends Undead {
    public Zombie(String name) {
        super(name + " - Zombie", 100);
    }

    public void eat(Undead target) {
        if (isAlive && target.isAlive) {
            int eatenHP = target.hp / 2;
            hp += eatenHP;
            target.takeDamage(eatenHP);
        }
    }

    public void attack(Undead target) {
        if (isAlive && hp > 50 && target.isAlive) {
            int damage = hp / 2;
            target.takeDamage(damage);
        }
    }
}

class Vampire extends Undead {
    public Vampire(String name) {
        super(name.isEmpty() ? "Vampire" : name + " - Vampire", 120);
    }

    public void bite(Undead target) {
        if (isAlive && target.isAlive) {
            int bittenHP = (int) (target.hp * 0.8);
            hp += bittenHP;
            target.takeDamage(bittenHP);
        }
    }

    public void attack(Undead target) {
        if (isAlive && target.isAlive) {
            target.takeDamage(hp);
        }
    }
}

class Skeleton extends Undead {
    public Skeleton(String name) {
        super(name + " - Skeleton", 80);
    }

    public void attack(Undead target) {
        if (isAlive && target.isAlive) {
            int damage = (int) (hp * 0.7);
            target.takeDamage(damage);
        }
    }
}

class Ghost extends Undead {
    public Ghost(String name) {
        super(name + " - Ghost", 50);
    }

    public void attack(Undead target) {
        if (isAlive && target.isAlive) {
            int damage = (int) (hp * 0.2);
            target.takeDamage(damage);
        }
    }

    public void haunt(Undead target) {
        if (isAlive && target.isAlive) {
            int hauntedHP = (int) (target.hp * 0.1);
            hp += hauntedHP;
            target.takeDamage(hauntedHP);
        }
    }
}

class Lich extends Undead {
    public Lich(String name) {
        super(name + " - Lich", 70);
    }

    public void castSpell(Undead target) {
        if (isAlive && target.isAlive) {
            int spellHP = (int) (target.hp * 0.1);
            hp += spellHP;
            target.takeDamage(spellHP);
        }
    }

    public void attack(Undead target) {
        if (isAlive && target.isAlive) {
            int damage = (int) (hp * 0.7);
            target.takeDamage(damage);
        }
    }
}

class Mummy extends Undead {
    public Mummy(String name) {
        super(name + " - Mummy", 100);
    }

    public void attack(Undead target) {
        if (isAlive && target.isAlive) {
            int damage = (int) (hp * 0.5 + target.hp * 0.1);
            target.takeDamage(damage);
        }
    }
}

public class UndeadGame {
    private static ArrayList<Undead> undeadList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Undead Game Menu:");
            System.out.println("1. Create Undead");
            System.out.println("2. Command Undead");
            System.out.println("3. Display Undead");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createUndead();
                    break;
                case 2:
                    commandUndead();
                    break;
                case 3:
                    displayUndead();
                    break;
                case 4:
                    System.out.println("Exiting the game.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUndead() {
        System.out.println("Create Undead Menu:");
        System.out.println("1. Zombie");
        System.out.println("2. Vampire");
        System.out.println("3. Skeleton");
        System.out.println("4. Ghost");
        System.out.println("5. Lich");
        System.out.println("6. Mummy");
        System.out.print("Enter the type of undead you want to create: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter a custom name for the undead (leave empty for default): ");
        String customName = scanner.nextLine();

        Undead undead;
        switch (choice) {
            case 1:
                undead = new Zombie(customName);
                break;
            case 2:
                undead = new Vampire(customName);
                break;
            case 3:
                undead = new Skeleton(customName);
                break;
            case 4:
                undead = new Ghost(customName);
                break;
            case 5:
                undead = new Lich(customName);
                break;
            case 6:
                undead = new Mummy(customName);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        undeadList.add(undead);
        System.out.println("Undead created: " + undead.name);
    }

    private static void commandUndead() {
        System.out.println("Command Undead Menu:");
        if (undeadList.isEmpty()) {
            System.out.println("No undead to command. Create undead first.");
            return;
        }

        for (int i = 0; i < undeadList.size(); i++) {
            System.out.println((i + 1) + ". " + undeadList.get(i).name);
        }

        System.out.print("Enter the index of the undead you want to command: ");
        int index = scanner.nextInt() - 1;
        if (index < 0 || index >= undeadList.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Undead undead = undeadList.get(index);
        if (!undead.isAlive) {
            System.out.println(undead.name + " is dead and cannot be commanded.");
            return;
        }

        System.out.println("Command Menu for " + undead.name + ":");
        if (undead instanceof Zombie) {
            System.out.println("1. Eat");
            System.out.println("2. Attack");
        } else if (undead instanceof Vampire) {
            System.out.println("1. Bite");
            System.out.println("2. Attack");
        } else if (undead instanceof Skeleton) {
            System.out.println("1. Attack");
        } else if (undead instanceof Ghost) {
            System.out.println("1. Attack");
            System.out.println("2. Haunt");
        } else if (undead instanceof Lich) {
            System.out.println("1. Cast Spell");
            System.out.println("2. Attack");
        } else if (undead instanceof Mummy) {
            System.out.println("1. Attack");
            System.out.println("2. Revive");
        }

        System.out.print("Enter the action you want to perform: ");
        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (actionChoice) {
            case 1:
                if (undead instanceof Zombie) {
                    System.out.print("Enter the index of the undead to eat: ");
                    int targetIndex = scanner.nextInt() - 1;
                    if (targetIndex >= 0 && targetIndex < undeadList.size() && targetIndex != index) {
                        ((Zombie) undead).eat(undeadList.get(targetIndex));
                        System.out.println(undead.name + " ate " + undeadList.get(targetIndex).name);
                    } else {
                        System.out.println("Invalid target index.");
                    }
                } else if (undead instanceof Vampire) {
                    System.out.print("Enter the index of the undead to bite: ");
                    int targetIndex = scanner.nextInt() - 1;
                    if (targetIndex >= 0 && targetIndex < undeadList.size() && targetIndex != index) {
                        ((Vampire) undead).bite(undeadList.get(targetIndex));
                        System.out.println(undead.name + " bit " + undeadList.get(targetIndex).name);
                    } else {
                        System.out.println("Invalid target index.");
                    }
                } else if (undead instanceof Skeleton) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Skeleton) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                } else if (undead instanceof Ghost) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Ghost) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                } else if (undead instanceof Lich) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Lich) undead).castSpell(undeadList.get(targetIndex));
                        System.out.println(undead.name + " cast a spell on " + undeadList.get(targetIndex).name);
                    }
                } else if (undead instanceof Mummy) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Mummy) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                }
                break;
            case 2:
                if (undead instanceof Zombie) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Zombie) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                } else if (undead instanceof Vampire) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Vampire) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                } else if (undead instanceof Lich) {
                    int targetIndex = selectTargetUndead(index);
                    if (targetIndex != -1) {
                        ((Lich) undead).attack(undeadList.get(targetIndex));
                        System.out.println(undead.name + " attacked " + undeadList.get(targetIndex).name);
                    }
                }
                break;
            default:
                System.out.println("Invalid action choice.");
        }
    }

    private static int selectTargetUndead(int currentIndex) {
        System.out.println("Select a target undead:");
        for (int i = 0; i < undeadList.size(); i++) {
            if (i != currentIndex && undeadList.get(i).isAlive) {
                System.out.println((i + 1) + ". " + undeadList.get(i).name);
            }
        }

        System.out.print("Enter the index of the target undead: ");
        int targetIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume the newline character

        if (targetIndex < 0 || targetIndex >= undeadList.size() || targetIndex == currentIndex || !undeadList.get(targetIndex).isAlive) {
            System.out.println("Invalid target index.");
            return -1;
        }

        return targetIndex;
    }

    private static void displayUndead() {
        System.out.println("Display Undead:");
        for (Undead undead : undeadList) {
            undead.display();
            System.out.println();
        }
    }
}
