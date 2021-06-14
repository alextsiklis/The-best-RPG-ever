public class Character {
    public Character(String name, int health, int strength, int dexterity, int xp, int gold) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.xp = xp;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    String name;
    int level = 1;
    int health;
    int strength;
    int dexterity;
    int xp;
    int gold;

    public int attack() {
        return strength;
    }

    public int LvlUp (){
        if (xp >= 100){
            level++;
            xp-=100;
            strength +=5;
            dexterity +=2;
            health = 100;
            System.out.println("Уровень героя повышен! Теперь герой " + level + " уровня!");
        }
        return level;
    }
}
