package XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class XML1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập đường dẫn thư mục: ");
        String folderPath = scanner.nextLine();
        File directory = new File(folderPath);

        if (directory.exists() && directory.isDirectory()) {
            generateXML(directory);
            System.out.println("Đã tạo tệp XML thành công.");
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc không phải là thư mục.");
        }
        scanner.close();
    }

    public static void generateXML(File directory) {
        try {
            FileWriter writer = new FileWriter("directoryStructure.xml");
            writer.write("<" + directory.getName() + ">\n");
            traverseDirectory(directory, writer, 1);
            writer.write("</" + directory.getName() + ">\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseDirectory(File directory, FileWriter writer, int level) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    writeXMLTag(file.getName(), writer, level);
                    traverseDirectory(file, writer, level + 1);
                    writeXMLClosingTag(file.getName(), writer, level);
                } else {
                    writeXMLTag(file.getName(), writer, level);
                }
            }
        }
    }

    private static void writeXMLTag(String name, FileWriter writer, int level) throws IOException {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        writer.write(indent.toString() + "<" + name + ">\n");
    }

    private static void writeXMLClosingTag(String name, FileWriter writer, int level) throws IOException {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        writer.write(indent.toString() + "</" + name + ">\n");
    }
}
