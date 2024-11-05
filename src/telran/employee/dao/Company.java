package telran.employee.dao;

import telran.employee.model.Employee;

public interface Company {
    String COUNTRY = "Israel";

    boolean addEmployee(Employee employee);

    Employee removeEmployee(int id);

    Employee findEmploy(int id);

// statistics
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
