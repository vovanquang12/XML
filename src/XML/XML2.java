package XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XML2 {
    public static void main(String[] args) {
        try {
            
            Student[] students = {
                new Student("Huy", 20, 3.5),
                new Student("Hiếu", 21, 3.2),
                new Student("Huyền", 19, 3.9)
            };
            
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            
            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);
            
            
            for (Student student : students) {
                Element studentElement = doc.createElement("student");
                rootElement.appendChild(studentElement);
                
                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(nameElement);
                
                Element ageElement = doc.createElement("age");
                ageElement.appendChild(doc.createTextNode(Integer.toString(student.getAge())));
                studentElement.appendChild(ageElement);
                
                Element gpaElement = doc.createElement("gpa");
                gpaElement.appendChild(doc.createTextNode(Double.toString(student.getGpa())));
                studentElement.appendChild(gpaElement);
            }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("students.xml"));
            transformer.transform(source, result);
            
            System.out.println("File XML đã được tạo thành công.");
            
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}
