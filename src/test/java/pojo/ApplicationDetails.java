package pojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //
@AllArgsConstructor
@NoArgsConstructor
@Builder  // Allows object creation using builder pattern
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDetails {

    private String id;
    private String realtor_status;
    private String realtor_info;
    private String loan_officer_status;
    private String purpose_loan;
    private String est_purchase_price;
    private String down_payment;
    private String down_payment_percent;
    private String total_loan_amount;
    private String src_down_payment;
    private String add_fund_available;
    private String apply_co_borrower;
    private String b_firstName;
    private String b_middleName;
    private String b_lastName;
    private String b_suffix;
    private String b_email;
    private String b_dob;
    private String b_ssn;
    private String b_marital;
    private String b_cell;
    private String b_home;
    private String c_firstName;
    private String c_middleName;
    private String c_lastName;
    private String c_suffix;
    private String c_email;
    private String c_dob;
    private String c_ssn;
    private String c_marital;
    private String c_cell;
    private String c_home;
    private String rent_own_status;
    private String monthly_rental_payment;
    private String first_mortagage_total_payment;
    private String employer_name;
    private String position;
    private String city;
    private String state;
    private String start_date;
    private String end_date;
    private String current_job;
    private String co_employer_name;
    private String co_position;
    private String co_city;
    private String co_state;
    private String co_start_date;
    private String co_end_date;
    private String co_current_job;
    private String gross_monthly_income;
    private String monthly_over_time;
    private String monthly_bonuses;
    private String monthly_commision;
    private String monthly_dividents;
    private String c_gross_monthly_income;
    private String c_monthly_over_time;
    private String c_monthly_bonuses;
    private String c_monthly_commision;
    private String c_monthly_dividents;
    private String add_belong;
    private String income_source;
    private String amount;
    private String eConsent_declarer;
    private String eConsent_declarer_FirstName;
    private String eConsent_declarer_LastName;
    private String eConsent_declarer_Email;
    private String created_on;
    private String modified_on;
    private String loan_status;
    private String user_id;
    private String active;
}
