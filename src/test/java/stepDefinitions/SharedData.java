package stepDefinitions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data
public class SharedData {

private RequestSpecification requestSpecification = given(); // initialize an empty requestSpec obj
private Response response;
private String JWToken;

// Yana your lines are from 17 to 47
private String firstName;
private String middleName;
private String lastName;
private String email;
private String dob;
private String ssn;
private String marital;
private String cellPhone;
private String homePhone;
    private String firstName2;
    private String middleName2;
    private String lastName2;
    private String email2;
    private String dob2;
    private String ssn2;
    private String marital2;
    private String cellPhone2;
    private String homePhone2;













// Zina your lines are from 48 to 78






























// Kenan your lines are from 79 to 109
private String emailDBUS1;
private String firstNameDBUS1;
private String lastNameDBUS1;
private String passwordDBUS1;
private Integer id_DBUS1;
private Integer zoneID;
private Integer type;
private String phone;
private String modifiedAt;
private String image;
private String createdAt;
private Integer countryID;
private Integer churchID;
private Integer active;
private Object fieldValue;















}
