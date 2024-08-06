package stepDefinitions.db;

public class NewClass {

    public static void main(String[] args) {
        String str = "Hello world:)";

        System.out.println(str.replaceAll("[^a-zA-Z]","")); //keeps only letters

        System.out.println(str.replaceAll("[a-zA-Z]","")); //removes all letters
    }
}
