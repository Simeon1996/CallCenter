package base;

public class Manager extends Employee {

    public Manager(CallCenter center) {
        super(center);
        rank = Rank.Manager;
    }
}
