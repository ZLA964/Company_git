package telran.employee.model;

public abstract class Employee {
    protected static double minWage = 30.61;
    protected final int id;
    protected String firstName;
    protected String lastName;
    protected double hours;

    public Employee(int id, String firstName, String lastName, double hours) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;

        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    //    public Employee() {
//    }

    public static double getMinWage() {
        return minWage;
    }

    public static void setMinWage(double minWage) {
        Employee.minWage = minWage;
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder();
//        sb.append("id=").append(id);
//        sb.append(", firstName='").append(firstName).append('\'');
//        sb.append(", lastName='").append(lastName).append('\'');
//        sb.append(", hours=").append(hours);
//        return sb.toString();
//    }

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hours=" + hours +
                ", salary=" + calcSalary();
    }

    public abstract double calcSalary();

    protected double fixSalary(double salary) {
        if(salary < hours * minWage) {
            salary = hours * minWage;
        }
        return salary;
    }

}

