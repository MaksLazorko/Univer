package com;

import com.data.*;

import java.util.*;
import java.util.stream.Stream;


/*
1.Who is head of department {department_name}
Answer: Head of {department_name} department is
{head_of_department_name}

2.Show {department_name} statistic.
Answer: assistans - {assistams_count}.
associate professors - {associate_professors_count}
professors -{professors_count}

3. Show the average salary for department {department_name}.
Answer: The average salary of {department_name} is
{average_salary}

4. Show count of employee for {department_name}.
Answer: {employee_count}

5. Global search by {template}.
Example: Global search by van
Answer: Ivan Petrenko, Petro Ivanov

 */

public class App {
    private static final String WHO_IS_HEAD = "Who is head of department";
    private static final String GLOBAL_SEARCH = "Global search by";
    private static final String SHOW = "Show";
    private static final String STATISTIC = "statistic";
    private static final String SHOW_AVERAGE_SALARY = "Show the average salary for department";
    private static final String SHOW_COUNT_OF_EMPLOYEE = "Show count of employee for";
    private final static String ERROR_MESSAGE = "Something went wrong, please check your parameters.";
    private final static String HELLO_MESSAGE = "Please, enter some request parameters: \n\n" +
            "1.Who is head of department {department_name}.\n" +
            "Answer: Head of {department_name} department is {head_of_department_name}\n" +
            "\n" +
            "2.Show {department_name} statistic.\n" +
            "Answer: assistans - {assistams_count}.\n" +
            "associate professors - {associate_professors_count}\n" +
            "professors -{professors_count}\n" +
            "\n" +
            "3. Show the average salary for department {department_name}.\n" +
            "Answer: The average salary of {department_name} is {average_salary}\n" +
            "\n" +
            "4. Show count of employee for {department_name}.\n" +
            "Answer: {employee_count}\n" +
            "\n" +
            "5. Global search by {template}.\n" +
            "Example: Global search by van\n" +
            "Answer: Ivan Petrenko, Petro Ivanov";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(HELLO_MESSAGE);
            System.exit(0);
        }
        args[args.length - 1] = args[args.length - 1].replace(".", "");
        defineRequest(args);
    }

    private static String getParameter(String searchString, String inputString) {
        int searchingStringEndPosition = searchString.length();
        return inputString.substring(searchingStringEndPosition).trim();
    }

    private static String getParameter(String startSearchString, String endSearchString, String inputString) {
        int startStringEndPosition = startSearchString.length();
        int endStringStartPosition = inputString.indexOf(endSearchString);
        return inputString.substring(startStringEndPosition, endStringStartPosition).trim();
    }


    private static void defineRequest(String[] args) {
        String consoleString = getStringFromArgs(args);

        if (consoleString.contains(WHO_IS_HEAD)) {
            String parameter = getParameter(WHO_IS_HEAD, consoleString);
            String headOfDepartmentName = new GetHeadOfDepartmentQuery(parameter).execute().get(0);
            System.out.println("Head of " + parameter + " department is " + headOfDepartmentName);
            return;
        }

        if (args[0].equals(SHOW) && args[args.length - 1].equals(STATISTIC)) {
            String parameter = getParameter(SHOW, STATISTIC, consoleString);
            List<LinkedHashMap<String, Integer>> result = new GetDegreeStatisticsForDepartmentQuery(parameter).execute();
            result.forEach(a -> a.forEach((key, value) -> System.out.println(key + " - " + value)));
            return;
        }

        if (consoleString.contains(SHOW_AVERAGE_SALARY)) {
            String parameter = getParameter(SHOW_AVERAGE_SALARY, consoleString);
            Double salary = new GetAverageSalaryForDepartmentQuery(parameter).execute().get(0);
            System.out.println(salary);
            return;
        }

        if (consoleString.contains(SHOW_COUNT_OF_EMPLOYEE)) {
            String parameter = getParameter(SHOW_COUNT_OF_EMPLOYEE, consoleString);
            Integer employeeCount = new GetCountOfEmployeeForDepartmentQuery(parameter).execute().get(0);
            System.out.println(employeeCount);
            return;
        }

        if (consoleString.contains(GLOBAL_SEARCH)) {
            String parameter = getParameter(GLOBAL_SEARCH, consoleString);
            List<String> names = new GetAllLectorsByTemplateQuery(parameter).execute();
            names.forEach(x -> System.out.print(x + ", "));
            System.out.println("\b\b");
            return;
        }
        System.out.println(ERROR_MESSAGE);
    }

    private static String getStringFromArgs(String[] args) {
        StringBuilder consoleString = new StringBuilder();
        Stream.of(args).forEach(x -> consoleString.append(x + " "));
        return consoleString.toString();
    }


}
