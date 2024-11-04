package telran.employee.model;

public class Manager extends Employee{
        private double baseSalary;
        private int  bonus;

    public Manager(int id, String firstName, String lastName, double hours, double baseSalary, int bonus) {
        super(id, firstName, lastName, hours);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public double calcSalary() {
        return fixSalary(baseSalary + bonus * hours);
//        double salary = baseSalary + bonus * hours;
//        if(salary < hours * minWage) {
//            salary = hours * minWage;
//        }
//        return salary;
    }

//    @Override
//    public String toString() {
//        return super.toString()+ ", salary=" + calcSalary();
//    }
}
