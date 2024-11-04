package telran.employee.dao;

import telran.employee.model.Employee;

public interface Company {
//  public static final   - писать не обязательно.
    String COUNTRY = "Israel";

 //    public abstract  писать не нужно это по дефолту.
    boolean addEmployee(Employee employee);

//    public abstract
    Employee removeEmployee(int id);

//    public abstract
    Employee findEmploy(int id);

// statistics ))
    int quantity();
    double totalSalary();
    double totalSales();
    void printEmployees();

    Employee[] findEmployeesHoursGreaterThan(int hours);
    Employee[] findEmployeesSalaryBetween(int minSalary, int maxSalary);


    default double avgSalary() {
        return totalSalary() / quantity();
    };
}
