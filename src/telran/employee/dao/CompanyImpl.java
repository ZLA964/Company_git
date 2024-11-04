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
        for (int i = 0; i < capacity; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                size++;
                break;
            }
        }
        return true;
    }

    @Override
    public Employee removeEmployee(int id) {
        Employee removed = null;
        if (size > 0 && id > 0) {
            for (int i = 0; i < capacity; i++) {
                if (employees[i] != null && employees[i].getId() == id) {
                    removed = employees[i];
                    employees[i] = null;
                    size--;
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

    //          return null;  only if no holes in array.
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
//    @Override
//    public double avgSalary() {
//        return totalSalary() / size;
//    }

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
        //       Predicate<Employee> predicate = new HoursPredicate(hours);
        Predicate<Employee> predicate = new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getHours() > hours;
            }
        };
        return findEmployeesByPredicate(predicate);
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
            if (employee != null &&
                    (predicate.test(employee))) {
                foundEmployees[count++] = employee;
            }
            if (count == size) {
                break; // Прерывание цикла, если достигнуто максимальное количество
            }
        }
        return Arrays.copyOfRange(foundEmployees, 0, count);
    }


}


//        Employee[] findEmployees = new Employee[count]
//        for(int i=0; i<count; i++){
//            findEmployees[i]=foundEmployees[i];
//        }
//        return findEmployees;


//        int i = 0;
//        while (employees[i] != null) {
//            i++;
//        }
//        employees[i] = employee;
//        size++;
 /*
       employees[size++] = employee;
        size++;
*/



//        Employee[] findedEmployee = new Employee[size];
//        int count = 0;
//        for (Employee employee : employees) {
//            if (employee != null && employee.getHours() > hours) {
//                findedEmployee[count++] = employee;
//            }
//        }
//        return Arrays.copyOfRange(findedEmployee, 0, count);

//        Predicate<Employee> predicate = e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary;
//        return findEmployeesByPredicate(predicate);

//        Employee[] findedEmployee = new Employee[size];
//        int count = 0;
//        for (Employee employee : employees) {
//            if (employee != null &&
//                    (employee.calcSalary() >= minSalary && employee.calcSalary() < maxSalary)) {
//                findedEmployee[count++] = employee;
//            }
//        }
//        return Arrays.copyOfRange(findedEmployee, 0, count);
