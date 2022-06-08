package yxl.day2.demo1;

public class Country implements Comparable<Country> {
    private String cname;
    private char level;

    private long gold = 1000L;
    private long people = 100L;

    public Country(String cname, char level) {
        this.cname = cname;
        this.level = level;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getPeople() {
        return people;
    }

    public void setPeople(long people) {
        this.people = people;
    }

    @Override
    public int compareTo(Country o) {
        return Integer.compare(this.level, o.level);
    }

    @Override
    public String toString() {
        return "Country{" +
                "cname='" + cname + '\'' +
                ", level=" + level +
                ", gold=" + gold +
                ", people=" + people +
                '}';
    }
}
