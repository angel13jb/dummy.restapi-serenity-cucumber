package com.restapi.dummy.employeeinfo;

import com.restapi.dummy.testbase.TestBase;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class EmployeeCURDTestSteps extends TestBase {
    static String employee_name = "test" ;
    static int employee_salary = Integer.parseInt("45000");
    static int employee_age= Integer.parseInt("36");
    static int employeeId;

    @Steps
    Employeesteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        ValidatableResponse response=employeeSteps.createEmployee(employee_name,employee_salary, Integer.parseInt(String.valueOf(employee_age)));
        response.log().all().statusCode(200);
    }

    @Title("Verify if the employee was added ")
    @Test
    public void test002() {
        employee_name="test";
        HashMap<String, Object> employeeMap = employeeSteps.getEmployeeInfoByEmployeeName(employee_name);
        Assert.assertThat(employeeMap, hasValue(employee_name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);
    }
    @Title("Update the employee information and verify the updated information for ID=5")
    @Test
    public void test003(){
        employee_name = "test1";
        employee_salary= Integer.parseInt("60000");
        employee_age= Integer.parseInt("24");
        employeeId=6695;
        employeeSteps.updateEmployee(employeeId,employee_name, Integer.parseInt(String.valueOf(employee_salary)),employee_age).log().all().statusCode(200);
    }

    @Title("Delete the employee and verify if the employee is deleted! for ID=2")
    @Test
    public void test004(){
        employeeId=2;
        employeeSteps.deleteEmployee(employeeId).statusCode(200).log().status();

    }
}
