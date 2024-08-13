package Entities.registry;

import Entities.Employee;

public class EmployeeRegistry {

    private Employee primeiro; 

    public EmployeeRegistry() {
    }

    public Employee getEmployeeRegistry() {
        return this.primeiro;
    }

    public void addEmployeeToRegistry(Employee employee) {
        if (this.primeiro == null) {
            this.primeiro = employee;
        } else {
            addEmployeeRecursively(this.primeiro, employee);
        }
    }

    private void addEmployeeRecursively(Employee current, Employee newEmployee) {
        if (current.getNextInLine() == null) {
            current.setNextInLine(newEmployee);
        } else {
            addEmployeeRecursively(current.getNextInLine(), newEmployee);
        }
    }

    public void removeEmployeeFromRegistry(Employee employeeToRemove) {
        this.primeiro = removeEmployeeRecursively(this.primeiro, employeeToRemove);
    }

    private Employee removeEmployeeRecursively(Employee current, Employee employeeToRemove) {
        if (current == null) {
            return null;
        }

        if (current.equals(employeeToRemove)) {
            return current.getNextInLine();
        }

        current.setNextInLine(removeEmployeeRecursively(current.getNextInLine(), employeeToRemove));
        return current;
    }

    public Employee findEmployeeInRegistry(Employee employeeToFind) {
        return findEmployeeRecursively(this.primeiro, employeeToFind);
    }

    private Employee findEmployeeRecursively(Employee current, Employee employeeToFind) {
        if (current == null) {
            return null;
        }

        if (current.equals(employeeToFind)) {
            return current;
        }

        return findEmployeeRecursively(current.getNextInLine(), employeeToFind);
    }
}
