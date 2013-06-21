/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.parameter;

public class PayoneParameters
{
    //region Default parameters
    public static String MID = "mid";
    public static String PORTALID = "portalid";
    public static String MODE = "mode";
    public static String REQUEST = "request";
    public static String RESPONSETYPE = "responsetype";
    public static String HASH = "hash";
    public static String SUCCESSURL = "successurl";
    public static String ERRORURL = "errorurl";
    public static String BACKURL = "backurl";
    public static String EXITURL = "exiturl";

    //region parameters
    public static String AID = "aid";
    public static String CARDPAN = "cardpan";
    public static String CARDTYPE = "cardtype";
    public static String CARDEXPIREDATE = "cardexpiredate";
    public static String CARDCVC2 = "cardcvc2";
    public static String CARDHOLDER = "cardholder";
    public static String ENCODING = "encoding";
    public static String STORECARDDATA = "storecarddata";
    public static String LANGUAGE = "language";
    public static String CLEARINGTYPE = "clearingtype";
    public static String REFERENCE = "reference";
    public static String AMOUNT = "amount";
    public static String CURRENCY = "currency";
    public static String PARAM = "param";
    public static String CUSTOMERID = "customerid";
    public static String USERID = "userid";
    public static String SALUTATION = "salutation";
    public static String TITLE = "title";
    public static String FIRSTNAME = "firstname";
    public static String LASTNAME = "lastname";
    public static String COMPANY = "company";
    public static String STREET = "street";
    public static String ADDRESSADDITION = "addressaddition";
    public static String ZIP = "zip";
    public static String CITY = "city";
    public static String COUNTRY = "country";
    public static String EMAIL = "email";
    public static String TELEPHONENUMBER = "telephonenumber";
    public static String BIRTHDAY = "birthday";
    public static String VATID = "Vatid";
    public static String IP = "ip";
    public static String PSEUDOCARDPAN = "pseudocardpan";
    public static String TRUNCATEDCARDPAN = "truncatedcardpan";
    public static String SHIPPING_FIRSTNAME = "shipping_firstname";
    public static String SHIPPING_LASTNAME = "shipping_lastname";
    public static String SHIPPING_COMPANY = "shipping_company";
    public static String SHIPPING_STREET = "shipping_street";
    public static String SHIPPING_ZIP = "shipping_zip";
    public static String SHIPPING_CITY = "shipping_city";
    public static String SHIPPING_COUNTRY = "shipping_country";
    public static String BANKCOUNTRY = "bankcountry";
    public static String BANKACCOUNT = "bankaccount";
    public static String BANKCODE = "bankcode";
    public static String BANKACCOUNTHOLDER = "Bankaccountholder";
    public static String ONLINEBANKTRANSFERTYPE = "onlinebanktransfertype";
    public static String BANKGROUPTYPE = "bankgrouptype";
    public static String WALLETTYPE = "wallettype";
    public static String TXID = "txid";
    public static String REDIRECTURL = "redirecturl";
    public static String CLEARING_BANKACCOUNTHOLDER = "clearing_bankaccountholder";
    public static String CLEARING_BANKCOUNTRY = "clearing_bankcountry";
    public static String CLEARING_BANKACCOUNT = "clearing_bankaccount";
    public static String CLEARING_BANKCODE = "clearing_bankcode";
    public static String CLEARING_BANKIBAN = "clearing_bankiban";
    public static String CLEARING_BANKBIC = "clearing_bankbic";
    public static String CLEARING_BANKCITY = "clearing_bankcity";
    public static String CLEARING_BANKNAME = "clearing_bankname";

    public static String NARRATIVE_TEXT = "narrative_text";
    public static String VREFERENCE = "vreference";
    public static String VACCOUNTNAME = "vaccountname";
    public static String SETTLETIME = "settletime";
    public static String SETTLEPERIOD = "settleperiod";
    public static String ACCESS_VAT = "access_vat";
    public static String ACCESS_PERIOD = "access_period";
    public static String ACCESS_ABOPERIOD = "access_aboperiod";
    public static String ACCESS_PRICE = "access_price";
    public static String ACCESS_ABOPRICE = "access_aboprice";
    public static String ACCESS_STARTTIME = "access_starttime";
    public static String ACCESS_CANCELTIME = "access_canceltime";
    public static String ACCESS_EXPIRETIME = "access_expiretime";
    public static String ACCESSCODE = "accesscode";
    public static String ACCESSNAME = "accessname";
    public static String PRODUCTID = "productid";
    public static String ECI = "eci";
    public static String INVOICE_DELIVERYMODE = "invoice_deliverymode";
    public static String INVOICEAPPENDIX = "invoiceappendix";
    public static String INVOICEID = "invoiceid";
    public static String CONSUMERSCORETYPE = "consumerscoretype";
    public static String ADDRESSCHECKTYPE = "addresschecktype";
    public static String DUE_TIME = "due_time";        
    public static String CHECKTYPE = "checktype";

    //region Response parameters
    public static String STATUS = "status";
    public static String ERRORCODE = "errorcode";
    public static String ERRORMESSAGE = "errormessage";
    public static String CUSTOMERMESSAGE = "customermessage";

    //region Modul invoicing placeholder parameter
    public static String ID_REGEX_PLACEHOLDER = "id\\[(\\d+)\\]";
    public static String PR_REGEX_PLACEHOLDER = "pr\\[(\\d+)\\]";
    public static String NO_REGEX_PLACEHOLDER = "no\\[(\\d+)\\]";
    public static String DE_REGEX_PLACEHOLDER = "de\\[(\\d+)\\]";
    public static String VA_REGEX_PLACEHOLDER = "va\\[(\\d+)\\]";

    public static String ID_PLACEHOLDER = "id[%d]";
    public static String PR_PLACEHOLDER = "pr[%d]";
    public static String NO_PLACEHOLDER = "no[%d]";
    public static String DE_PLACEHOLDER = "de[%d]";
    public static String VA_PLACEHOLDER = "va[%d]";

    public static class ResponseErrorCodes
    {
        public static String VALID = "VALID";
        public static String INVALID = "INVALID";
        public static String APPROVED = "APPROVED";
        public static String REDIRECT = "REDIRECT";
        public static String ERROR = "ERROR";
    } 

    public static class CreditCardVariations
    {
        public static String MASTERCARD = "M";
        public static String VISA = "V";
        public static String AMEX = "A";
        public static String DINERS = "D";
        public static String JCB = "J";
        public static String MAESTRO_INTERNATIONAL = "O";
        public static String MAESTRO_UK = "U";
        public static String DISCOVER = "C";
        public static String CARTE_BLEUE = "B";
    }

    public static class RequestParameter
    {
        public static String CREDITCARDCHECK = "creditcardcheck";
        public static String PREAUTHORIZATION = "preauthorization";
        public static String AUTHORIZATION = "authorization";
    }

    public static class ResponseTypeParameter
    {
        public static String JSON = "JSON";
        public static String REDIRECT = "REDIRECT";
    }

    public static class EncodingParameter
    {
        public static String UTF_8 = "UTF-8";
        public static String ISO_8859_1 = "ISO-8859-1";
    }

    public static class ModeParameter
    {
        public static String TEST = "test";
        public static String LIVE = "live";
    }

    public static class StoreCardDataParameter
    {
        public static String YES = "yes";
        public static String NO = "no";
    }

    public static class ClearingTypeParameter
    {
        public static String DEBIT = "elv";
        public static String CREDITCARD = "cc";
        public static String PREPAYMENT = "vor";
        public static String BILL = "rec";
        public static String ONLINETRANSFER = "sb";
        public static String EWALLET = "wlt";
    }

    public static class BankCountryParameter
    {
        public static String DE = "DE";
        public static String AT = "AT";
        public static String NL = "NL";            
    }

    public static class OnlineBankTransferTypeParameter
    {
        public static String DIRECT_TRANSFER = "PNT";
        public static String GIROPAY = "GPY";
        public static String EPS_ONLINETRANSFER = "EPS";
        public static String POSTFINANCE_EFINANCE = "PFF";
        public static String POSTFINANCE_CARD = "PFC";
        public static String IDEAL = "IDL";
    }

    public static class WalletTypeParameter
    {
        public static String PAYPAL_EXPRESS = "PPE";
    }

    public static class InvoiceDeliveryMode
    {
        public static String MAIL = "M";
        public static String PDF = "P";
    }

    public static class BankgroupType
    {
        //austria types
        public static String ARZ_OVB = "ARZ_OVB";
        public static String ARZ_BAF = "ARZ_BAF";
        public static String ARZ_NLH = "ARZ_NLH";
        public static String ARZ_VLH = "ARZ_VLH";
        public static String ARZ_BCS = "ARZ_BCS";
        public static String ARZ_HTB = "ARZ_HTB";
        public static String ARZ_HAA = "ARZ_HAA";
        public static String ARZ_IKB = "ARZ_IKB";
        public static String ARZ_OAB = "ARZ_OAB";
        public static String ARZ_IMB = "ARZ_IMB";
        public static String ARZ_GRB = "ARZ_GRB";
        public static String ARZ_HIB = "ARZ_HIB";
        public static String BA_AUS = "BA_AUS";
        public static String BAWAG_BWG = "BAWAG_BWG";
        public static String BAWAG_PSK = "BAWAG_PSK";
        public static String BAWAG_ESY = "BAWAG_ESY";
        public static String BAWAG_SPD = "BAWAG_SPD";
        public static String SPARDAT_EBS = "SPARDAT_EBS";
        public static String SPARDAT_BBL = "SPARDAT_BBL";
        public static String RAC_RAC = "RAC_RAC";
        public static String HRAC_OOS = "HRAC_OOS";
        public static String HRAC_SLB = "HRAC_SLB";
        public static String HRAC_STM = "HRAC_STM";

        // netherland types
        public static String ABN_AMRO_BANK = "ABN_AMRO_BANK";
        public static String RABOBANK = "RABOBANK";
        public static String FRIESLAND_BANK = "FRIESLAND_BANK";
        public static String ASN_BANK = "ASN_BANK";
        public static String SNS_BANK = "SNS_BANK";
        public static String TRIODOS_BANK = "TRIODOS_BANK";
        public static String SNS_REGIO_BANK = "SNS_REGIO_BANK";
        public static String ING_BANK = "ING_BANK";
    }
}