import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br;
    private static Character player = null;
    private static Battle battle = null;

    private static void printNavigation() {
        System.out.println("Куда наш герой отправится в этот раз?");
        System.out.println("1. На рынок к торговцу");
        System.out.println("2. В загадочный лес");
        System.out.println("3. Вернуться в замок героев");
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Zombie(
                "Зомби",
                50,
                20,
                0,
                50,
                20
        );
        else if(random % 3 == 0) return new Skelet(
                "Скелет",
                30,
                15,
                10,
                60,
                20
        );
        else return new Dragon(
                "Дракон",
                200,
                30,
                20,
                90,
                1000
        );
    }

    private static void commitFight() {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getHealth()));
                System.out.println("Желаете продолжить поход? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(
                    string,
                    100,
                    20,
                    30,
                    0,
                    10
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s! Да прибудет с ним сила и удача!", player.getName()));
            printNavigation();
        }

        switch (string) {
            case "1": {
                if (Math.random() * 100 <= 50) {
                    System.out.println("Торговец еще не прибыл в город");
                    command(br.readLine());
                } else {
                    System.out.println("У торговца есть зелье, которое восстановит здоровье (Стоимость: 15 монет). Желаете приобрести? (куплю/не куплю)");
                    command(br.readLine());
                }
            }
                break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
            case "куплю":
                if (player.gold >= 15) {
                    player.health = 100;
                    player.xp += 20;
                    player.gold -= 15;
                    System.out.println("Здоровье восстановлено!");
                }
                printNavigation();
                command(br.readLine());
                break;
            case "не куплю": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Желаете продолжить поход? (да/нет)");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
