public class Battle {
        public void fight(Character player, Character monster, FightCallback fightCallback) {
            Runnable runnable = () -> {
                int turn = 1;
                boolean isFightEnded = false;
                while (isFightEnded == false) {
                    System.out.println("Ход: " + turn);
                    if (turn % 2 == 1) {
                        isFightEnded = makeHit(monster, player, fightCallback);
                    } else {
                        isFightEnded = makeHit(player, monster, fightCallback);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    turn++;
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();
        }

        private Boolean makeHit(Character defender, Character attacker, FightCallback fightCallback) {
            int hit = attacker.attack();
            if (Math.random() * 100 <= defender.dexterity) {
                hit = 0;
            }
            int defenderHealth = defender.getHealth() - hit;
            if (hit != 0) {
                System.out.println(String.format("%s нанес удар в %d единиц!", attacker.getName(), hit));
                System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
            } else {
                System.out.println(String.format("%s промахнулся!", attacker.getName()));
            }
            if (defenderHealth <= 0 && defender instanceof Player) {
                System.out.println("Наш славный герой был повержен...");
                fightCallback.fightLost();
                return true;
            }
            if (defenderHealth <= 0 && defender instanceof Dragon) {
                System.out.println("Дракон был повержен! После этого нашего героя прозвали Великим охотником на драконов!");
                System.out.println("Среди всех рыцарей он стал самым уважаемым и мудрым. " +
                        "Он нашел любовь своего сердца, а вскоре у них родились прекрасные дети: 3 сына, ставшими в будущем не менее великимим рыцарями, и дочь, чья красота описывалась в тысячах произведений." +
                        "Жили они все долго и счастилво. Так и закончилась его великая история!");
                fightCallback.fightLost();
                return true;
            } else if(defenderHealth <= 0) {
                System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
                attacker.LvlUp();
                attacker.setXp(attacker.getXp() + defender.getXp());
                attacker.setGold(attacker.getGold() + defender.getGold());
                fightCallback.fightWin();
                return true;
            } else {
                defender.setHealth(defenderHealth);
                return false;
            }
        }
    }
