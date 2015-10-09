package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.Queue;


public class Parser {
    Queue<Employee> employees = new PriorityQueue<>();
    //Set<Employee> employees = new HashSet<>();

    public Queue<Employee> parseFileTextToSortedEmployeeList(String fileName) throws IOException {
        URL url = this.getClass().getResource(fileName);
        File file = new File(url.getFile());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String result;

        try {
            while ((result = reader.readLine()) != null) {
                String[] row = result.split(";");
                employees.add(parseEmployee(row));
            }
        } finally {
            if(reader!=null)
                reader.close();
        }
        return employees;
    }

    private Employee parseEmployee(String... row) {
        if (row.length == 3) {
            int id = Integer.valueOf(row[0]);
            String name = row[1];
            int managerId = Integer.valueOf(row[2]);
            return new Employee(id, name, managerId);
        }
        return new Employee();
    }
}


