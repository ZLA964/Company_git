package telran.employee.dao;

import telran.employee.model.Employee;
import telran.employee.model.SalesManager;

import java.util.Arrays;
import java.util.function.Predicate;

public class CompanyImpl implements Company {
    private Employee[] employees;
    private int size;
    private int capacity;

    public CompanyImpl(int capacity) {
        employees = new Employee[capacity];
        this.capacity = employees.length;
    }

    public CompanyImpl(Integer capacity) {
        if (capacity != null && capacity > 0) {
            employees = new Employee[capacity];
            this.capacity = employees.length;
        } else {
            employees = new Employee[1];
            this.capacity = 1;
            System.out.println("Illegal capacity.\n" +
                    "Capacity must be a positive number.\n " +
                    "So new Company has only 1 employee");
        }
    }

    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null || size == capacity || findEmploy(employee.getId()) != null) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {        //  my variant
            if (employees[i] == null) {             //
                employees[i] = employee;            //
                size++;                             //
                break;                              //
            }                                       //
        }                                           //
//        employees[size] = employee;           // Eduard variant
//        size++;                               //
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee removed = null;
        if (size > 0 && id > 0) {
            for (int i = 0; i < capacity; i++) {
                if (employees[i] != null && employees[i].getId() == id) {
                    removed = employees[i];
                    employees[i] = null;                                                // my variant
                    size--;                                                             //
   //                 System.arraycopy(employees, i+1, employees, i, size-i-1 );    // Eduard variant
   //                 employees[--size] = null;                                     //
                }
            }
        }
        return removed;
    }

    @Override
    public Employee findEmploy(int id) {
        Employee foundEmployee = null;
        for (int i = 0, j = 0; j < size && i < capacity; i++) {
            if (employees[i] != null) {
                j++;
                if (employees[i].getId() == id) {
                    foundEmployee = employees[i];
                    break;
                }
            }
        }
        return foundEmployee;
    }

    @Override
    public int quantity() {
        return size;
    }

    @Override
    public double totalSalary() {
        double total = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                total += employee.calcSalary();
            }
        }
        return total;
    }

    @Override
    public double totalSales() {
        double total = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i] instanceof SalesManager) {
                total += ((SalesManager) employees[i]).getSalesValue();
            }
        }
        return total;
    }

    @Override
    public void printEmployees() {
        Employee employee;
        int number = 1;
        for (int i = 0; i < employees.length; i++) {
            employee = employees[i];
            if (employee != null) {
                System.out.println(number + ". " + employee + " tableNum: " + i);
                number++;
            }
        }
        System.out.println("========================\n");
    }

    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        return findEmployeesByPredicate(e-> e.getHours()> hours);
    }

    @Override
    public Employee[] findEmployeesSalaryBetween(int minSalary, int maxSalary) {
        return findEmployeesByPredicate(
                e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary
        );
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        Employee[] foundEmployees = new Employee[size];
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null && (predicate.test(employee))) {
                foundEmployees[count++] = employee;
            }
            if (count == size) {
                break; // Прерывание цикла, если достигнуто максимальное количество
            }
        }
        return Arrays.copyOf(foundEmployees,  count);
    }
}

