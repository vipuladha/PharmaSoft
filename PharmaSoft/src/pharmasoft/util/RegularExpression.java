/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmasoft.util;

/**
 *
 * @author madhawa
 */
public class RegularExpression {

    public static final String MONEY = "^\\d+(?:\\.\\d{1,2})?$";
    //FrmCompanySettings
    public static final String RE_FRM_COMPANY_SETTNGS_CITY = "^[a-zA-Z\\s]*$";
    public static final String RE_FRM_COMPANY_SETTNGS_TEL = "[0][0-9]{9}";
    public static final String RE_FRM_COMPANY_SETTNGS_EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //FrmCaseTypeDetails
    public static final String RE_FRM_CASE_TYPE_DETAILS = "^[a-zA-Z\\s]*$";
    //FrmAddNewMember
    public static final String RE_FRM_ADD_NEW_MEMBER_MEMBERNO = "[0-9]{8}";
    public static final String RE_FRN_ADD_NEW_MEMBER_INITIALS="^[a-zA-Z]*$";
    public static final String RE_FRN_ADD_NEW_MEMBER_NAME="^[a-zA-Z\\s]*$";
    public static final String RE_FRN_ADD_NEW_MEMBER_ADDRESS="^[\\w\\s\"\',/.-]*$";
    public static final String RE_FRN_ADD_NEW_MEMBER_TPNO="[0][0-9]{9}";
    public static final String RE_FRN_ADD_NEW_MEMBER_EMAIL="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String RE_FRN_ADD_NEW_MEMBER_VATREGNO="^[\\w\\s\"\',/.-]*$";

     //FrmLocation
    public static final String RE_FRM_LOCATION_DETAILS_DESCRIPTION = "^[a-zA-Z\\s]*$";
    //FrmFeesTable
    public static final String RE_FRM_FEES_TABLE_APPEARANCE = MONEY;
    public static final String RE_FRM_FEES_TABLE_TRAVELING = MONEY;
    public static final String RE_FRM_FEES_TABLE_SUBS = MONEY;
    //FrmSystemSetting
    public static final String RE_FRM_SYSTM_STTNG_MEMBR_CD = "[0-9]{8}";
    public static final String RE_FRM_SYSTM_STTNG_PRFSSNL_CD = "[0-9]{8}";
    public static final String RE_FRM_SYSTM_STTNG_CAS_TYP_CD = "[0-9]{3}";
    public static final String RE_FRM_SYSTM_STTNG_LOCATN_CD = "[0-9]{3}";
    public static final String RE_FRM_SYSTM_STTNG_JOB_NO = "[0-9]{10}";
    public static final String RE_FRM_SYSTM_STTNG_RCPT_NO = "[0-9]{8}";
    public static final String RE_FRM_SYSTM_STTNG_INVOICE_NO = "[0-9]{8}";
    
    //FrmReciept
    public static final String RE_FRM_SYSTM_STTNG_CHEQUE = "[0-9]{1,20}";
    
    //FrmAddPastInvoice
    public static final String RE_FRM_ADDPASTINVOICENO_INVOICE_NO = "[0-9]{8}";
    public static final String RE_FRM_ADDPASTINVOICENO_MEMBER_NO = "[0-9]{8}";
    public static final String RE_FRM_ADDPASTINVOICENO_MONEY = MONEY;
    public static final String RE_FRM_NUMBER="[0][0-9]{9}";
    public static final String RE_NUMBER="\\d+";
    
}
