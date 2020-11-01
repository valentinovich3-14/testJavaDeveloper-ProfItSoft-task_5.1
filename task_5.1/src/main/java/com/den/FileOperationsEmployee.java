/**
 * Класс для сохранения данных по сотрудникам в фалах.
 *
 * @author    Денис Гончаренко
 */

package com.den;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperationsEmployee {

    /**
     * метот сохраняет сотрудников в текстовый файл JSON формата
     *
     * @param employees лист сохраняемых сотрудников,
     * @param path путь и название файла для сохранения.
     */
    public void saveEmployeesToJSON(List<Employee> employees, String path){
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file);
            employees.forEach(employee -> {
                try {
                    writer.append(employeeToJSON(employee));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * метот загружал данные о сотрудниках из файла и создает обьекты
     *
     * @param path путь и название файла с данными.
     */
    public List<Employee> shapeEmployeesFromJSON(String path){
        List<Employee> employees = new ArrayList<>();

        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                Employee employee = employeeFromJSON(line);
                employees.add(employee);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * метот преобразует обькт типа Employee в JSON формат
     */
    private String employeeToJSON(Employee employee){
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("{ ");

        stringBuffer.append("\"rate\" : \"" + employee.getRate() + "\"");
        stringBuffer.append(" , ");
        stringBuffer.append("\"salary\" : \"" + employee.getSalary() + "\"");
        stringBuffer.append(" , ");
        stringBuffer.append("\"hoursWorked\" : \"" + employee.getHoursWorked() + "\"");

        stringBuffer.append(" }\n");

        return stringBuffer.toString();
    }

    /**
     * метот преобразует строку JSON формата в обькт типа Employee
     */
    private Employee employeeFromJSON(String s){
        Employee employee = new Employee() {
            @Override
            public double calculateSalary() {
                return 0;
            }
        };

        char[] chars = s.toCharArray();
        int[] index = new int[12];
        int indexElements = 0;

        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '"'){
                index[indexElements++] = i;
            }
        }

        employee.setRate(Integer.parseInt(s.substring(index[2]+1, index[3])));
        employee.setSalary(Double.parseDouble(s.substring(index[6]+1, index[7])));
        employee.setHoursWorked(Double.parseDouble(s.substring(index[10]+1, index[11])));


        return employee;
    }
}
