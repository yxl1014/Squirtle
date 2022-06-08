package yxl.day2.demo1;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class CountryServiceImpl {
    private final Map<String, Country> CountryMapper;

    public CountryServiceImpl() {
        this.CountryMapper = new HashMap<>();
    }

    public boolean addCountry(String cname, char level) {
        if (this.CountryMapper.containsKey(cname)) {
            //log info country is exist
            System.out.println("country is exist");
            return false;
        }

        CountryMapper.put(cname, new Country(cname, level));
        return true;
    }

    public int immigrant(String from, String to) {
        @NotNull Country f = this.CountryMapper.get(from);
        @NotNull Country t = this.CountryMapper.get(to);
        if (Objects.isNull(f) || Objects.isNull(t)) {
            //log info country is not exist
            System.out.println("country is not exist");
            return -1;
        }

        if (t.getLevel() == 'A') {
            //log info can not immigrant to level A
            System.out.println("can not immigrant to level A");
            return -1;
        }

        switch (Integer.compare(f.getLevel(), t.getLevel())) {
            case -1:
            case 0: {
                if (f.getPeople() <= 0) {
                    //log info f have non people
                    System.out.println("f have non people");
                    return -1;
                }
                f.setPeople(f.getPeople() - 1);
                t.setPeople(t.getPeople() + 1);
                return 0;
            }
            case 1: {
                if (f.getPeople() <= 0) {
                    //log info f have non people
                    System.out.println("f have non people");
                    return -1;
                }
                if (f.getGold() < 500) {
                    //log info f have non enough gold
                    System.out.println("f have non enough gold");
                    return -1;
                }
                f.setPeople(f.getPeople() - 1);
                t.setPeople(t.getPeople() + 1);
                f.setGold(f.getGold() - 500);
                t.setGold(t.getGold() + 500);
                return 0;
            }
        }
        return -1;
    }

    public void display() {
        Collection<Country> list = this.CountryMapper.values();
        for (Country c : list) {
            System.out.print(c.toString() + " ");
        }
        System.out.println();
    }
}
