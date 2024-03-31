import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
        // Create a hash table for counting word occurrences
        Scanner scanner = new Scanner(System.in);
        HashTable wordCount = instantiateTableWithDesiredHash(scanner); // Create the hash table with desired hash function
        readFileAndCountWords(wordCount); // Read file and count words
        
        int choice = 0;
        // Menu loop
        while (choice != 4) {
            System.out.println("Menu: ");
           
            System.out.println("1. View word count for a particular word");
            System.out.println("2. View words in descending order of word count");
            System.out.println("3. View a report of the hash table");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View the count of a particular word
                    viewWordCount(scanner, wordCount);
                    break;
                case 2:
                    // View words in descending order of their counts
                    wordCount.viewWordsDescendingOrderByCount();
                    break;
                case 3:
                    // View a report of the hash table
                    reportHashTable(wordCount);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        // Close the scanner
        scanner.close();
    }

    // Function to instantiate the hash table with desired hash function
    private static HashTable instantiateTableWithDesiredHash(Scanner scanner) {
        HashTable wordCount;
        int choice = 0;
        System.out.println("Choose your hash function: ");
        System.out.println("Select: \n1: Sophisticated \n2: Naive");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice == 1) {
            wordCount = new HashTable(1000, HashFunction.SOPHISTICATED); // Create hash table with sophisticated hash function
            System.out.println("Hash function set to Sophisticated");
        } else if (choice == 2) {
            wordCount = new HashTable(1000, HashFunction.NAIVE); // Create hash table with naive hash function
            System.out.println("Hash function set to Naive");
        } else {
            System.out.println("Invalid choice");
            wordCount = null;
        }
        return wordCount;
    }

    // Function to read the file and count words
    public static void readFileAndCountWords(HashTable wordCount) {
        try {
            File file = new File("TaleOfTwoCities.txt");
            Scanner scanner = new Scanner(file);

            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase(); // Convert to lowercase
                // Split the line into words using regular expressions
                String[] words = line.split("\\s+"); // Split by whitespace
                // Process each word
                for (String word : words) {
                    // Remove punctuation marks
                    word = word.replaceAll("[^a-zA-Z]", "");
                    // If the word is not empty after removing punctuation
                    if (!word.isEmpty()) {
                        // Insert the word into the HashTable and increment its count
                        int count = wordCount.get(word) == null ? 1 : wordCount.get(word) + 1;
                        wordCount.put(word, count);
                    }
                }
            }

            scanner.close();
            System.out.println("File read and words counted successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    // Function to view the count of a particular word
    public static void viewWordCount(Scanner scanner, HashTable wordCount) {
        System.out.print("Enter the word to view its count: ");
        String word = scanner.next().toLowerCase();
        Integer count = wordCount.get(word);
        if (count != null) {
            System.out.println("Count of \"" + word + "\": " + count);
        } else {
            System.out.println("Word \"" + word + "\" not found.");
        }
    }

    // Function to generate a report on the hash table
    private static void reportHashTable(HashTable wordCount) {
        // Number of words per book
        
        int totalWords = wordCount.size();

        // Print out each slot
        System.out.println("Contents of Each Slot:");
        for (int i = 0; i < wordCount.TABLE_SIZE; i++) {
            System.out.print("Slot " + i + ": ");
            LinkedHashEntry<String, Integer> entry = wordCount.table[i];
            while (entry != null) {
                System.out.print("(" + entry.key + ", " + entry.value + ") ");
                entry = entry.next;
            }
            System.out.println();
        }

        // Size of the array
        int arraySize = wordCount.TABLE_SIZE;
        System.out.println("Size of the Array: " + arraySize);

        // Length of the linked list for each hash code
        System.out.println("Length of Linked List for Each Hash Code:");
        for (int i = 0; i < arraySize; i++) {
            LinkedHashEntry<String, Integer> entry = wordCount.table[i];
            int listLength = 0;
            while (entry != null) {
                listLength++;
                entry = entry.next;
            }
            System.out.println("Hash Code " + i + ": " + listLength);
        }

        // Number of unused array slots
        int unusedSlots = arraySize - totalWords;
        System.out.println("Number of Unused Array Slots: " + unusedSlots);

        // Effect of the different hash functions on load factor and linked list length
        // (Load factor is the ratio of the number of elements to the number of buckets)
        double loadFactor = (double) totalWords / arraySize;
        System.out.println("Load Factor: " + loadFactor);

        
    }
}
