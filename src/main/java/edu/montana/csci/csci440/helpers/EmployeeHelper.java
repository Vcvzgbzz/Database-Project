package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

public class EmployeeHelper {
    static Map<Long, List<Employee>> employeeMap = new HashMap<>();
    public static String makeEmployeeTree() {
        List<Employee> employee = Employee.all(); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure
        for(Employee emp: employee){
            Long reportsTo = emp.getReportsTo();
            List<Employee> employees= employeeMap.get(reportsTo);
            if(employees == null){
                employees = new LinkedList<>();
            }
            employees.add(emp);
            employeeMap.put(reportsTo, employees);
        }
        return "<ul>" + makeTree(employee.get(0), employeeMap) + "</ul>";
    }
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";
        List<Employee> reports = employeeMap.get(employee.getEmployeeId());

        if(reports !=null){
            for(Employee emp: reports){
                list+=makeTree(emp, employeeMap);
            }
        }

        return list + "</ul></li>";
    }
}
