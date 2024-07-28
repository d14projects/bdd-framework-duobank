package utilities;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {

    public static void writeSignUpDataToFile(String fileSignUp, String fileLogIn, int quantity) {

        List<String> email = new ArrayList<>();
        List<String> password = new ArrayList<>();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileSignUp))) {
            for (int i = 0; i < quantity; i++) {
                Faker faker = new Faker();
                email.add(faker.internet().emailAddress());
                password.add(faker.internet().password(10, 20, true, false, true));
                bw.write(faker.name().firstName());
                bw.write(",");
                bw.write(faker.name().lastName());
                bw.write(",");
                bw.write(email.get(i));
                bw.write(",");
                bw.write(password.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLogIn))) {
            for (int i = 0; i < quantity; i++) {
                bw.write(email.get(i));
                bw.write(",");
                bw.write(password.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInvalidLoginDataToFile(String fileName, int quantity){

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < quantity; i++) {
                Faker faker = new Faker();
                bw.write(faker.internet().emailAddress());
                bw.write(",");
                bw.write(faker.internet().password(7, 10, false, false, false));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
