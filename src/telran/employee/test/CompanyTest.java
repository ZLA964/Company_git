package telran.employee.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.employee.dao.Company;
import telran.employee.dao.CompanyImpl;
import telran.employee.model.Employee;
import telran.employee.model.Manager;
import telran.employee.model.SalesManager;
import telran.employee.model.WageEmployee;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private Company company;
    private Employee[] employees;
    private final int capacity = 5;

    @BeforeEach
    void SetUp() {
        company = new CompanyImpl(capacity);
        employees = new Employee[4];
            employees[0] = new Manager(1000, "John", "Smith", 182, 20_000, 20 );
            employees[1] = new WageEmployee(2000,"Mary", "Smith", 182, 40 );
            employees[2] = new SalesManager(3000, "Peter", "Jackson", 182, 40_000, 0.1);
            employees[3] = new SalesManager(4000, "Rsbindranate", "Agrawal", 91, 80_000, 0.1 );
        for(int i=0; i <employees.length; i++){
            company.addEmployee(employees[i]);
        }
    }

    @Test
    void testAddEmployee() {
        assertFalse(company.addEmployee(null));
        assertFalse(company.addEmployee(employees[2]));
        Employee employee = new SalesManager(5000, "Peter", "Jackson", 182, 40_000, 0.1);
        assertTrue(company.addEmployee(employee));
        assertEquals(capacity, company.quantity());
        employee = new SalesManager(6000, "Peter", "Jackson", 182, 40_000, 0.1);
        assertFalse(company.addEmployee(employee));
    }

    @Test
    void testRemoveEmployee() {
       Employee employee = company.removeEmployee(3000);
       assertEquals(employees[2], employee);
        assertEquals(3, company.quantity());
        employee = company.removeEmployee(2000);
        employee = company.removeEmployee(1000);
        assertEquals(employees[0], employee);
        assertEquals(1, company.quantity());
        assertTrue(company.addEmployee(employees[1]));
        assertEquals(employees[1], company.findEmploy(2000));
        assertEquals(2, company.quantity());
        assertNull(company.removeEmployee(3000));
        company.printEmployees();

    }

    @Test
    void testFindEmploy() {
        assertEquals(employees[2], company.findEmploy(3000));
        assertNull(company.findEmploy(5000));
    }

    @Test
    void testQuantity() {
        assertEquals(4, company.quantity());
    }

    @Test
    void testTotalSalary() {
        assertEquals(44491.02, company.totalSalary(), 0.01);
        Employee employee = company.removeEmployee(2000);
        assertEquals(44491.02-7280.0, company.totalSalary(), 0.01);
    }

    @Test
    void testAvgSalary() {
        assertEquals(44491.02 / 4, company.avgSalary(), 0.01);
    }

    @Test
    void testTotalSales() {
        assertEquals(120_000, company.totalSales(), 0.01);
    }

    @Test
    void testPrintEmployees() {
        company.printEmployees();
    }

    @Test
    void testFindEmployeesHoursGreaterThan(){
        System.out.println("=== Hours test start ====");
        Employee[] actual = company.findEmployeesHoursGreaterThan(100);
        Employee[] expected = {employees[0],employees[1],employees[2]};
        assertEquals(expected.length, actual.length);
        for(int i = 0; i <expected.length; i++){
            assertEquals(expected[i],actual[i]);
        }
        assertArrayEquals(expected,actual);
        Employee employee = company.removeEmployee(2000);
        Employee[] expected1 = {employees[0],employees[2]};
        Employee[] actual1 = company.findEmployeesHoursGreaterThan(100);
        assertEquals(expected1.length, actual1.length);
        assertArrayEquals(expected1,actual1);
        System.out.println("=== Hours test stop ====");
    }

    @Test
    void testFindEmployeesSalaryBetween(){
        Employee[] actual = company.findEmployeesSalaryBetween(5000, 8000);
        Employee[] expected = {employees[1],employees[2]};
        assertEquals(expected.length, actual.length);
        assertArrayEquals(expected,actual);

    }
}