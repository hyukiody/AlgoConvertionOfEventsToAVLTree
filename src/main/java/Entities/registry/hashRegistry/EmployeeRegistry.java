/*package Entities.registry.hashRegistry;

import Entities.Employee;

public class EmployeeRegistry {

    private Employee raiz;

    public EmployeeRegistry() {
    }

    public Employee getEmployeeRegistry() {
        return this.raiz;
    }

    public void addEmployeeToRegistry(Employee employee) {
        if (this.raiz == null) {
            this.raiz = employee;
        } else {
            addEmployeeRecursively(this.raiz, employee);
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
        this.raiz = removeEmployeeRecursively(this.raiz, employeeToRemove);
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
        return findEmployeeRecursively(this.raiz, employeeToFind);
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
}*/
