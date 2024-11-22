package appDomain;

import implementations.*;
import java.io.*;
import java.util.regex.*;

public class XMLParser {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java XMLParser <xml-file-path>");
            return;
        }
        
        String xmlFilePath = args[0];
        try {
            String xmlContent = readFile(xmlFilePath);
            parseXML(xmlContent);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        // Debug: print the content read from file
        return content.toString();
    }

    private static void parseXML(String xmlContent) {
        MyStack<String> tagStack = new MyStack<>();
        MyQueue<String> errorQueue = new MyQueue<>();
        
        // Split content into lines to track line numbers
        String[] lines = xmlContent.split("\n");
        int lineNumber = 0; // Track line numbers

        // Regular expression for detecting tags
        Pattern tagPattern = Pattern.compile("<(/?\\w+)(.*?)\\s*/?>");

        // Iterate over each line in the content
        for (String line : lines) {
            lineNumber++;
            Matcher matcher = tagPattern.matcher(line);

            while (matcher.find()) {
                String fullTag = matcher.group(0).trim();
                String tagName = matcher.group(1);
                boolean isClosingTag = tagName.startsWith("/");

                if (fullTag.endsWith("/>")) {
                    continue; // Ignore self-closing tags
                }

                if (isClosingTag) {
                    // Closing tag logic
                    String closingTag = tagName.substring(1);

                    if (!tagStack.isEmpty() && tagStack.peek().equals(closingTag)) {
                        tagStack.pop(); // Matched opening tag
                    } else {
                        // Invalid closing tag
                        errorQueue.enqueue("Error at line " + lineNumber + ": Invalid close tag - " + fullTag);
                    }
                } else {
                    // Opening tag logic
                    tagStack.push(tagName);
                }
            }
        }

        // After parsing all lines, check for any unclosed tags in the stack
        while (!tagStack.isEmpty()) {
            String unclosedTag = tagStack.pop();
            errorQueue.enqueue("Error: Missing closing tag for <" + unclosedTag + ">");
        }

        // Print the error messages
        System.out.println("\n====================ERROR LOG====================");

        // If no errors were found, print "No errors found"
        if (errorQueue.isEmpty()) {
            System.out.println("No errors found.");
        } else {
            // Print each error in the queue
            while (!errorQueue.isEmpty()) {
                System.out.println(errorQueue.dequeue());
            }
        }
    }
}
