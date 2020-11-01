/**
 * Тестовый класс класса com.den.FileOperationsEmployee.
 *
 * @author    Денис Гончаренко
 */

import com.den.Developer;
import com.den.Employee;
import com.den.FileOperationsEmployee;
import com.den.Manager;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestFileOperationsEmployee {

    /**
     * тестовый метод метода saveEmployeesToJSON
     */
    @Test
    public void testSaveEmployeesToJSON(){
        String path = "C:\\Users\\Denys\\IdeaProjects\\task_5.1\\testSaveEmployeesToJSON.txt";
        StringBuffer expected = new StringBuffer(
                                "{ \"rate\" : \"1000\" , \"salary\" : \"1000.0\" , \"hoursWorked\" : \"160.0\" }" +
                                "{ \"rate\" : \"1500\" , \"salary\" : \"1875.0\" , \"hoursWorked\" : \"200.0\" }" +
                                "{ \"rate\" : \"450\" , \"salary\" : \"140.625\" , \"hoursWorked\" : \"50.0\" }" +
                                "{ \"rate\" : \"500\" , \"salary\" : \"0.0\" , \"hoursWorked\" : \"300.0\" }" +
                                "{ \"rate\" : \"300\" , \"salary\" : \"281.25\" , \"hoursWorked\" : \"150.0\" }");

        FileOperationsEmployee fileOperationsEmployee = new FileOperationsEmployee();

        List<Employee> employees = new ArrayList<>();

        Developer employee1 = new Developer(1000, 160);
        employee1.setSalary(employee1.calculateSalary());
        Developer employee2 = new Developer(1500, 200);
        employee2.setSalary(employee2.calculateSalary());
        Developer employee3 = new Developer(450, 50);
        employee3.setSalary(employee3.calculateSalary());
        Manager employee4 = new Manager(500, 300);
        /*employee4.setSalary(employee4.calculateSalary());*/
        Manager employee5 = new Manager(300, 150);
        employee5.setSalary(employee5.calculateSalary());

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        fileOperationsEmployee.saveEmployeesToJSON(employees, path);
        StringBuffer actual = new StringBuffer();

        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                actual.append(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    /**
     * тестовый метод метода shapeEmployeesFromJSON
     */
    @Test
    public void testShapeEmployeesFromJSON(){
        String path = "C:\\Users\\Denys\\IdeaProjects\\task_5.1\\testShapeEmployeesFromJSON.txt";

        double[] expected = {800, 450.0, 90.0,
                2500, 2812.5, 180.0,
                4000, 5000.0, 200.0,
                200, 125.5 ,100.0,
                500, 437.5 ,140.0};

        FileOperationsEmployee fileOperationsEmployee = new FileOperationsEmployee();
        List<Employee> actualList = fileOperationsEmployee.shapeEmployeesFromJSON(path);

        int index = 0;
        double[] actual = new double[5*3];

        for (Employee employee: actualList) {
            actual[index++] = employee.getRate();
            actual[index++] = employee.getSalary();
            actual[index++] = employee.getHoursWorked();
        }

        Assert.assertArrayEquals(expected, actual, 0);
    }
}
