
    drop table MPNETADMINDV.ADDRESSES cascade constraints;

    drop table MPNETADMINDV.ADVERTISEMENTCONTACTS cascade constraints;

    drop table MPNETADMINDV.ADVERTISEMENTS cascade constraints;

    drop table MPNETADMINDV.CANDIDATECOVERLETTERS cascade constraints;

    drop table MPNETADMINDV.CANDIDATEEDUCATIONS cascade constraints;

    drop table MPNETADMINDV.CANDIDATEJOBAGENTS cascade constraints;

    drop table MPNETADMINDV.CANDIDATEJOBAPPLICATIONS cascade constraints;

    drop table MPNETADMINDV.CANDIDATEJOBHISTORIES cascade constraints;

    drop table MPNETADMINDV.CANDIDATEPREFERENCES cascade constraints;

    drop table MPNETADMINDV.CANDIDATEREFERENCES cascade constraints;

    drop table MPNETADMINDV.CANDIDATERESUMES cascade constraints;

    drop table MPNETADMINDV.CANDIDATES cascade constraints;

    drop table MPNETADMINDV.CANDIDATESAVEJOBS cascade constraints;

    drop table MPNETADMINDV.CANDIDATESKILLS cascade constraints;

    drop table MPNETADMINDV.CAREERHARMONY cascade constraints;

    drop table MPNETADMINDV.CONSENT cascade constraints;

    drop table MPNETADMINDV.EDUCATIONDEGREES cascade constraints;

    drop table MPNETADMINDV.EMAILS cascade constraints;

    drop table MPNETADMINDV.GEOLOCATION cascade constraints;

    drop table MPNETADMINDV.LOSTCANDIDATES cascade constraints;

    drop table MPNETADMINDV.OTHER_CANDIDATE_DETAILS cascade constraints;

    drop table MPNETADMINDV.PERSONS cascade constraints;

    drop table MPNETADMINDV.PHONES cascade constraints;

    drop table MPNETADMINDV.POSTAL_CODE_CAMPUS cascade constraints;

    drop table MPNETADMINDV.REGIONS cascade constraints;

    drop table MPNETADMINDV.SITES cascade constraints;

    drop table MPNETADMINDV.SKILLS cascade constraints;

    drop table MPNETADMINDV.STEERING_QUESTIONS cascade constraints;

    drop table MPNETADMINDV.USERLOGINHISTORIES cascade constraints;

    drop table MPNETADMINDV.USERS cascade constraints;

    drop table MPNETADMINDV.V_BRANCHES cascade constraints;

    drop table MPNETADMINDV.V_CANDIDATEAPPLICATIONS cascade constraints;

    drop table MPNETADMINDV.V_CANDIDATERESUMES cascade constraints;

    drop table MPNETADMINDV.V_CONFIGURATION cascade constraints;

    drop table MPNETADMINDV.V_LOOKUP_VALUES cascade constraints;

    drop table MPNETADMINDV.V_RECRUITER_LOCATION_REPORT cascade constraints;

    drop table MPNETADMINDV.V_RECRUITER_REPORT cascade constraints;

    drop sequence MPNETADMINDV.ADDRESSES_SEQ;

    drop sequence MPNETADMINDV.ADVERTISEMENTCONTACTS_SEQ;

    drop sequence MPNETADMINDV.ADVERTISEMENTS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATECOVERLETTERS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEEDUCATIONS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEJOBAGENTS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEJOBAPPLICATIONS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEJOBHISTORIES_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEPREFERENCES_SEQ;

    drop sequence MPNETADMINDV.CANDIDATEREFERENCES_SEQ;

    drop sequence MPNETADMINDV.CANDIDATERESUMES_SEQ;

    drop sequence MPNETADMINDV.CANDIDATESAVEJOBS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATESKILLS_SEQ;

    drop sequence MPNETADMINDV.CANDIDATES_SEQ;

    drop sequence MPNETADMINDV.CONSENT_SEQ;

    drop sequence MPNETADMINDV.EMAILS_SEQ;

    drop sequence MPNETADMINDV.GEOLOCATION_SEQ;

    drop sequence MPNETADMINDV.LOSTCANDIDATES_SEQ;

    drop sequence MPNETADMINDV.OTHER_CANDIDATE_DETAILS_SEQ;

    drop sequence MPNETADMINDV.PHONES_SEQ;

    drop sequence MPNETADMINDV.SITES_SEQ;

    drop sequence MPNETADMINDV.SKILLS_SEQ;

    drop sequence MPNETADMINDV.STEERING_QUESTIONS_SEQ;

    create table MPNETADMINDV.ADDRESSES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        addressType varchar2(255 char),
        ADDRESS1 varchar2(255 char),
        ADDRESS2 varchar2(255 char),
        ADDRESS3 varchar2(255 char),
        POBOX varchar2(255 char),
        CITY varchar2(255 char),
        CODE varchar2(255 char),
        STATE varchar2(255 char),
        COUNTRY varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.ADVERTISEMENTCONTACTS (
        ADVERTCONTACTID number(19,0) not null,
        SITE_ID number(19,0),
        NAME varchar2(255 char),
        ADDRESS1 varchar2(255 char),
        ADDRESS2 varchar2(255 char),
        POBOX varchar2(255 char),
        CITY varchar2(255 char),
        STATE varchar2(255 char),
        POSTALCODE varchar2(255 char),
        COUNTRY varchar2(255 char),
        BRANCH_ID number(19,0),
        ADVERTISEMENTID number(19,0),
        PHONENUMBER varchar2(255 char),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        EXTERNALID varchar2(255 char),
        primary key (ADVERTCONTACTID)
    );

    create table MPNETADMINDV.ADVERTISEMENTS (
        ADVERTISEMENTID number(19,0) not null,
        SITE_ID number(19,0),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        JOBTITLE varchar2(255 char),
        JOBDESCRIPTION varchar2(255 char),
        CANDIDATEPROFILE varchar2(255 char),
        CANDIDATESKILLS varchar2(255 char),
        INDUSTRYSECTOR varchar2(255 char),
        LOCATION varchar2(255 char),
        JOBCOUNTRY varchar2(255 char),
        LON_COORDINATE float,
        LAT_COORDINATE float,
        CONTRACTTYPE varchar2(255 char),
        PUBLICATIONDATE timestamp,
        CLIENTNAME varchar2(255 char),
        PAYRANGE varchar2(255 char),
        REFERENCENUMBER varchar2(255 char),
        EXPIRATIONDATE timestamp,
        REG_LEVEL varchar2(255 char),
        ASSESSMENT_TEST varchar2(255 char),
        ADVERTCONTACTID number(19,0) not null,
        HOURSWORKED varchar2(255 char),
        LANGUAGE varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        EXTERNALID varchar2(255 char),
        BUSINESSARREA varchar2(255 char),
        CAREERHARMONY_ID number(19,0),
        LOC_FREEFORM varchar2(255 char),
        primary key (ADVERTISEMENTID)
    );

    create table MPNETADMINDV.CANDIDATECOVERLETTERS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        TITLE varchar2(255 char),
        DATECREATED timestamp,
        letter clob,
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEEDUCATIONS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        SCHOOLNAME varchar2(255 char),
        DEGREECODE varchar2(255 char),
        CITY varchar2(255 char),
        REGION varchar2(255 char),
        COUNTRY varchar2(255 char),
        COMPLETIONDATE timestamp,
        DESCRIPTION varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEJOBAGENTS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        AGENTNAME varchar2(255 char),
        JOBINDUSTRY varchar2(255 char),
        JOBTITLE varchar2(255 char),
        JOBCOUNTRY varchar2(255 char),
        JOBPROXIMITY varchar2(255 char),
        JOBPROXIMITYUNIT varchar2(255 char),
        CONTRACTTYPE varchar2(255 char),
        CONTENTTYPE varchar2(255 char),
        FREQUENCY varchar2(255 char),
        LASTACTIVE timestamp,
        USESKILL varchar2(255 char),
        EXACTMATCH varchar2(255 char),
        QUERY varchar2(255 char),
        SENDEMAIL varchar2(255 char),
        MATCHINGJOBS varchar2(255 char),
        CANDIDATESKILLS varchar2(255 char),
        JOBLOCATION varchar2(255 char),
        JOBLANGUAGE varchar2(255 char),
        HOURSWORKED varchar2(255 char),
        BUSINESSARREA varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEJOBAPPLICATIONS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        TESTTAKEN varchar2(255 char),
        DATEAPPLIED timestamp,
        ADVERTISEMENT_ID number(19,0) not null,
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        COVERLETTER varchar2(255 char),
        CVNAME varchar2(255 char),
        CVUPDATEDON timestamp,
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        CH_SCORE varchar2(255 char),
        BG_SCORE varchar2(255 char),
        CH_LINK varchar2(255 char),
        CH_TEST_LINK varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEJOBHISTORIES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        STARTDATE timestamp,
        ENDDATE timestamp,
        COMPANY varchar2(255 char),
        LOCATION varchar2(255 char),
        INDUSTRY varchar2(255 char),
        JOBTITLE varchar2(255 char),
        DESCRIPTION varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEPREFERENCES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        CONTACTMETHOD varchar2(255 char),
        CONTRACTTYPE varchar2(255 char),
        JOBTITLE varchar2(255 char),
        POSITIONTYPE varchar2(255 char),
        DISTANCEUNITS varchar2(255 char),
        INDUSTRYSECTOR varchar2(255 char),
        WORKSHIFTS varchar2(255 char),
        FLEXHOURS varchar2(255 char),
        INTROTOMANPOWER varchar2(255 char),
        WORKTYPE varchar2(255 char),
        STARTDATE timestamp,
        HOURSPERWEEK varchar2(255 char),
        RELOCATEINTERNATIONAL varchar2(255 char),
        RELOCATECOUNTRY varchar2(255 char),
        PREFEREDLOCATION varchar2(255 char),
        PROXIMITY varchar2(255 char),
        ROLEDESC varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATEREFERENCES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        NAME varchar2(255 char),
        PHONE varchar2(255 char),
        EMAIL varchar2(255 char),
        COMPANY varchar2(255 char),
        TITLE varchar2(255 char),
        REFERENCETYPE varchar2(255 char),
        RELATIONSHIP varchar2(255 char),
        CONTACT varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATERESUMES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        DATECREATED timestamp,
        RESUME blob,
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        NAME varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        LENS_ID varchar2(255 char),
        MIME_TYPE varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        STATUS varchar2(255 char),
        WORKPERMITEXPDATE timestamp,
        WORKPERMITS varchar2(255 char),
        NATIONALITY varchar2(255 char),
        RESIDENCESTATUS varchar2(255 char),
        RESIDENCEPERMIT varchar2(255 char),
        RESIDENCEPERMINTEXPDATE timestamp,
        PASSPORTEXPDATE timestamp,
        BRANCH_ID number(19,0),
        PERSON_ID number(19,0),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        FIRSTNAME varchar2(255 char),
        MIDDLENAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        NATIONALNUMBER varchar2(255 char),
        BIRTHDATE timestamp,
        BIRTHPLACE varchar2(255 char),
        GENDER varchar2(255 char),
        MARITIALSTATUS varchar2(255 char),
        EMAIL varchar2(255 char),
        PASSWORD varchar2(255 char),
        PASSWORDHINTQUESTION varchar2(255 char),
        PASSWORDHINTANSWER varchar2(255 char),
        USERAPPLICATIONLANGUAGE varchar2(255 char),
        CONTACT_BY_EMAIL varchar2(255 char),
        REG_LEVEL varchar2(255 char),
        CORRESPONDEMAIL varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        NATIVE_LANGUAGE varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATESAVEJOBS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        DATESAVED timestamp,
        ADVERTISEMENT_ID number(19,0),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CANDIDATESKILLS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        LASTUSED timestamp,
        SKILLLEVEL varchar2(255 char),
        YEARSOFEXP varchar2(255 char),
        SKILLS_ID number(19,0),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CAREERHARMONY (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        SECURITYKEY varchar2(255 char),
        PROJECT_ID varchar2(255 char),
        COMPANY_ID varchar2(255 char),
        LANGUAGE_ID varchar2(255 char),
        NOTIFICATION number(19,0),
        UPDATED_ON timestamp,
        UPDATED_BY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.CONSENT (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        CONSENT_TYPE varchar2(255 char),
        CONSENT_VALUE varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        primary key (ID)
    );

    create table MPNETADMINDV.EDUCATIONDEGREES (
        ID number(19,0) not null,
        LANG varchar2(255 char),
        COUNTRY varchar2(255 char),
        DEGREE_CODE varchar2(255 char),
        DEGREE_DESC varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.EMAILS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        FRIENDS_EMAIL varchar2(255 char),
        FRIENDS_NAME varchar2(255 char),
        MESSAGE varchar2(255 char),
        TYPE varchar2(255 char),
        STATE varchar2(255 char),
        CREATED_ON timestamp,
        CREATED_BY varchar2(255 char),
        CHANGED_ON timestamp,
        CHANGED_BY varchar2(255 char),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        SUBJECT varchar2(255 char),
        SENDER_EMAIL varchar2(255 char),
        FROM_LINE varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.GEOLOCATION (
        GEOLOCATIONID number(19,0) not null,
        COUNTRYNAME varchar2(255 char),
        REGION varchar2(255 char),
        MUNICIPALITY varchar2(255 char),
        POSTALCODE varchar2(255 char),
        LATITUDE varchar2(255 char),
        LONGITUDE varchar2(255 char),
        CREATED_ON timestamp,
        CREATED_BY varchar2(255 char),
        CHANGED_ON timestamp,
        CHANGED_BY varchar2(255 char),
        SITE_ID varchar2(255 char),
        primary key (GEOLOCATIONID)
    );

    create table MPNETADMINDV.LOSTCANDIDATES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        REASON varchar2(255 char),
        REASON_COMMENTS varchar2(255 char),
        UPDATED_ON timestamp,
        UPDATED_BY varchar2(255 char),
        EMAIL varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.OTHER_CANDIDATE_DETAILS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        MARKETING_QUESTION_CODE varchar2(255 char),
        MARKETING_QUESTION_ANSWER varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.PERSONS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        FIRSTNAME varchar2(255 char),
        MIDDLENAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        NATIONALNUMBER varchar2(255 char),
        BIRTHDATE timestamp,
        BIRTHPLACE varchar2(255 char),
        GENDER varchar2(255 char),
        NATIONALITY varchar2(255 char),
        MARITIALSTATUS varchar2(255 char),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        USER_ID number(19,0),
        primary key (ID)
    );

    create table MPNETADMINDV.PHONES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        PHONETYPE varchar2(255 char),
        PHONENUMBER varchar2(255 char),
        PRIMARY varchar2(255 char),
        CANDIDATE_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.POSTAL_CODE_CAMPUS (
        ID number(19,0) not null,
        LANG varchar2(255 char),
        COUNTRYCODE varchar2(255 char),
        POSTALCODE varchar2(255 char),
        STATE varchar2(255 char),
        CITY varchar2(255 char),
        REMARKS varchar2(255 char),
        UPDATED_ON timestamp,
        UPDATED_BY varchar2(255 char),
        CREATED_ON timestamp,
        CHANGED_ON timestamp,
        primary key (ID)
    );

    create table MPNETADMINDV.REGIONS (
        ID number(19,0) not null,
        LANG varchar2(255 char),
        country_code varchar2(255 char),
        region_code varchar2(255 char),
        region_name varchar2(255 char),
        location_code varchar2(255 char),
        prefered_location varchar2(255 char),
        LONGITUDE double precision,
        LATITUDE double precision,
        CREATED_BY varchar2(255 char),
        CREATED_ON timestamp,
        UPDATE_ON timestamp,
        UPDATED_BY varchar2(255 char),
        CHANGED_ON timestamp,
        CHANGED_BY varchar2(255 char),
        primary key (ID)
    );
    
--I added everything after created_by to REGIONS table

    create table MPNETADMINDV.SITES (
        ID number(19,0) not null,
        SITECODE varchar2(255 char),
        SITENAME varchar2(255 char),
        SITEOWNER varchar2(255 char),
        SITEFIRSTLANG varchar2(255 char),
        SITESECONDLANG varchar2(255 char),
        SITETHIRDLANG varchar2(255 char),
        COUNTRYCODE varchar2(255 char),
        SITESTATUS varchar2(255 char),
        DEFAULT_LEVEL varchar2(255 char),
        INTEGRATIONFLAG varchar2(255 char),
        DISTANCE_UNIT varchar2(255 char),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        BG_FLAG varchar2(255 char),
        resource_prefix varchar2(255 char),
        
        CREATED_BY varchar2(255 char),
        CREATED_ON timestamp,
        UPDATE_ON timestamp,
        UPDATED_BY varchar2(255 char),
        CHANGED_ON timestamp,
        CHANGED_BY varchar2(255 char),
        primary key (ID)
    );

--I added sitename to SITES table

    create table MPNETADMINDV.SKILLS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        SKILLNAME varchar2(255 char),
        SKILLDESCRIPTION varchar2(255 char),
        PARENT_ID number(19,0),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        LANG varchar2(255 char),
        
        CREATED_BY varchar2(255 char),
        CREATED_ON timestamp,
        UPDATE_ON timestamp,
        UPDATED_BY varchar2(255 char),
        CHANGED_ON timestamp,
        CHANGED_BY varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.STEERING_QUESTIONS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        QUESTION varchar2(255 char),
        ANSWER1 varchar2(255 char),
        ANSWER2 varchar2(255 char),
        DECISION1 varchar2(255 char),
        DECISION2 varchar2(255 char),
        LANG varchar2(255 char),
        QUESTIONID number(19,0),
        QUESTIONTYPE varchar2(255 char),
        UPDATED_ON timestamp,
        UPDATED_BY varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.USERLOGINHISTORIES (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        LOGINDATE timestamp,
        SUCCESS varchar2(255 char),
        USER_ID number(19,0) not null,
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.USERS (
        ID number(19,0) not null,
        SITE_ID number(19,0),
        EMAIL varchar2(255 char),
        PASSWORD varchar2(255 char),
        PASSWORDHINTQUESTION varchar2(255 char),
        PASSWORDHINTANSWER varchar2(255 char),
        USERAPPLICATIONLANGUAGE varchar2(255 char),
        UPDATEDON timestamp,
        UPDATEDBY varchar2(255 char),
        USERROLE_ID number(19,0),
        FIRSTNAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        MIDDLENAME varchar2(255 char),
        NATIONALNUMBER varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.V_BRANCHES (
        BRANCH_ID number(19,0) not null,
        SITE_ID number(19,0),
        LANG varchar2(255 char),
        CODE varchar2(255 char),
        STREET varchar2(255 char),
        ADDRESS1 varchar2(255 char),
        ADDRESS2 varchar2(255 char),
        BRANCHNAME varchar2(255 char),
        STATE varchar2(255 char),
        PREFERED_LOCATION varchar2(255 char),
        SPECIALITY varchar2(255 char),
        EMAIL varchar2(255 char),
        PHONENUMBER varchar2(255 char),
        FAXNUMBER varchar2(255 char),
        POSTALCODE varchar2(255 char),
        COUNTRY varchar2(255 char),
        CITY varchar2(255 char),
        LOCATION_INFO varchar2(255 char),
        OPERATION_HOURS varchar2(255 char),
        ADDITIONAL_INFO varchar2(255 char),
        primary key (BRANCH_ID)
    );

    create table MPNETADMINDV.V_CANDIDATEAPPLICATIONS (
        APPLICAION_ID number(19,0) not null,
        CANDIDATE_ID number(19,0),
        RESUME_ID number(19,0),
        FIRSTNAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        TESTTAKEN varchar2(255 char),
        TEST_SCORE varchar2(255 char),
        LENS_SCORE varchar2(255 char),
        CONTACT_BY_EMAIL varchar2(255 char),
        CANDIDATE_EMAIL varchar2(255 char),
        BRANCH_ID number(19,0),
        BRANCH_CITY varchar2(255 char),
        BRANCHNAME varchar2(255 char),
        BRANCH_ADDRESS1 varchar2(255 char),
        BRANCH_ADDRESS2 varchar2(255 char),
        BRANCH_STATE varchar2(255 char),
        BRANCH_COUNTRY varchar2(255 char),
        BRANCH_POSTALCODE varchar2(255 char),
        BRANCH_PHONE varchar2(255 char),
        BRANCH_FAX varchar2(255 char),
        BRANCH_EMAIL varchar2(255 char),
        BRANCH_LANGUAGE varchar2(255 char),
        SITE_COUNTRY varchar2(255 char),
        site_id number(19,0),
        RESUME_NAME varchar2(255 char),
        LENS_ID varchar2(255 char),
        RESUME blob,
        ADVERTISEMENT_NUMBER varchar2(255 char),
        ADVERTISEMENT_ID number(19,0),
        DATEAPPLIED timestamp,
        primary key (APPLICAION_ID)
    );

    create table MPNETADMINDV.V_CANDIDATERESUMES (
        CANDIDATE_ID number(19,0) not null,
        RESUME_ID number(19,0),
        FIRSTNAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        CONTACT_BY_EMAIL varchar2(255 char),
        CANDIDATE_EMAIL varchar2(255 char),
        BRANCH_CITY varchar2(255 char),
        BRANCHNAME varchar2(255 char),
        BRANCH_ADDRESS1 varchar2(255 char),
        BRANCH_ADDRESS2 varchar2(255 char),
        BRANCH_STATE varchar2(255 char),
        BRANCH_COUNTRY varchar2(255 char),
        BRANCH_POSTALCODE varchar2(255 char),
        BRANCH_PHONE varchar2(255 char),
        BRANCH_FAX varchar2(255 char),
        BRANCH_EMAIL varchar2(255 char),
        SITE_COUNTRY varchar2(255 char),
        RESUME_NAME varchar2(255 char),
        RESUME blob,
        primary key (CANDIDATE_ID)
    );

    create table MPNETADMINDV.V_CONFIGURATION (
        ID number(19,0) not null,
        SITECODE varchar2(255 char),
        CONDIDIONAL_BINDING varchar2(255 char),
        VIEWABLE varchar2(255 char),
        primary key (ID)
    );

    create table MPNETADMINDV.V_LOOKUP_VALUES (
        LOOKUP_ID number(19,0) not null,
        SITECODE varchar2(255 char),
        LANG varchar2(255 char),
        LOOKUP_NAME varchar2(255 char),
        VALUE_CODE varchar2(255 char),
        LOOKUP_DESCRIPTION varchar2(255 char),
        SORT_CODE varchar2(255 char),
        primary key (LOOKUP_ID)
    );

    create table MPNETADMINDV.V_RECRUITER_LOCATION_REPORT (
        CANDIDATE_ID number(19,0) not null,
        FIRSTNAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        MIDDLENAME varchar2(255 char),
        RESUME_ID number(19,0),
        PREFERED_LOCATION varchar2(255 char),
        LOCATION_CODE varchar2(255 char),
        REGION_CODE varchar2(255 char),
        LANG varchar2(255 char),
        SITE_ID number(19,0),
        BRANCHNAME varchar2(255 char),
        UPDATEDON timestamp,
        RESUME blob,
        primary key (CANDIDATE_ID)
    );

    create table MPNETADMINDV.V_RECRUITER_REPORT (
        RESUME_ID number(19,0) not null,
        CANDIDATE_ID number(19,0) not null,
        ADVERTISEMENT_ID number(19,0) not null,
        SITE_ID number(19,0) not null,
        FIRSTNAME varchar2(255 char),
        LASTNAME varchar2(255 char),
        MIDDLENAME varchar2(255 char),
        BRANCHNAME varchar2(255 char),
        DATEAPPLIED timestamp,
        primary key (RESUME_ID, CANDIDATE_ID, ADVERTISEMENT_ID, SITE_ID)
    );

    alter table MPNETADMINDV.ADDRESSES 
        add constraint FKDA388B8247B15375 
        foreign key (ID) 
        references MPNETADMINDV.PERSONS 
        on delete cascade;

    alter table MPNETADMINDV.ADDRESSES 
        add constraint FKDA388B82122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES 
        on delete cascade;

    alter table MPNETADMINDV.ADVERTISEMENTS 
        add constraint FK9161530E3F55D087 
        foreign key (ADVERTCONTACTID) 
        references MPNETADMINDV.ADVERTISEMENTCONTACTS;

    alter table MPNETADMINDV.CANDIDATECOVERLETTERS 
        add constraint FK90F45259122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEEDUCATIONS 
        add constraint FK147BFF6E122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEJOBAGENTS 
        add constraint FKF09B01E8122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEJOBAPPLICATIONS 
        add constraint FKE881B17D122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEJOBAPPLICATIONS 
        add constraint FKE881B17D434C4CF5 
        foreign key (ADVERTISEMENT_ID) 
        references MPNETADMINDV.ADVERTISEMENTS;

    alter table MPNETADMINDV.CANDIDATEJOBHISTORIES 
        add constraint FK5292B9F8122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEPREFERENCES 
        add constraint FK2EA195122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATEREFERENCES 
        add constraint FK80471C2B122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATERESUMES 
        add constraint FKDDADDF03122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATESAVEJOBS 
        add constraint FK709219B6122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CANDIDATESAVEJOBS 
        add constraint FK709219B6434C4CF5 
        foreign key (ADVERTISEMENT_ID) 
        references MPNETADMINDV.ADVERTISEMENTS;

    alter table MPNETADMINDV.CANDIDATESKILLS 
        add constraint FK7CC84365122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.CONSENT 
        add constraint FK63824BFA122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.OTHER_CANDIDATE_DETAILS 
        add constraint FKA7294BB7122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES;

    alter table MPNETADMINDV.PHONES 
        add constraint FK8C9F64C547B15375 
        foreign key (ID) 
        references MPNETADMINDV.PERSONS 
        on delete cascade;

    alter table MPNETADMINDV.PHONES 
        add constraint FK8C9F64C5122999B5 
        foreign key (CANDIDATE_ID) 
        references MPNETADMINDV.CANDIDATES 
        on delete cascade;

--    alter table MPNETADMINDV.SKILLS 
--        add constraint FK91E57442C886DC17 
--        foreign key (PARENT_ID) 
--        references MPNETADMINDV.SKILLS;

    alter table MPNETADMINDV.USERLOGINHISTORIES 
        add constraint FKBB2FFF749548821F 
        foreign key (USER_ID) 
        references MPNETADMINDV.USERS;

    create sequence MPNETADMINDV.ADDRESSES_SEQ;

    create sequence MPNETADMINDV.ADVERTISEMENTCONTACTS_SEQ;

    create sequence MPNETADMINDV.ADVERTISEMENTS_SEQ;

    create sequence MPNETADMINDV.CANDIDATECOVERLETTERS_SEQ;

    create sequence MPNETADMINDV.CANDIDATEEDUCATIONS_SEQ;

    create sequence MPNETADMINDV.CANDIDATEJOBAGENTS_SEQ;

    create sequence MPNETADMINDV.CANDIDATEJOBAPPLICATIONS_SEQ;

    create sequence MPNETADMINDV.CANDIDATEJOBHISTORIES_SEQ;

    create sequence MPNETADMINDV.CANDIDATEPREFERENCES_SEQ;

    create sequence MPNETADMINDV.CANDIDATEREFERENCES_SEQ;

    create sequence MPNETADMINDV.CANDIDATERESUMES_SEQ;

    create sequence MPNETADMINDV.CANDIDATESAVEJOBS_SEQ;

    create sequence MPNETADMINDV.CANDIDATESKILLS_SEQ;

    create sequence MPNETADMINDV.CANDIDATES_SEQ;

    create sequence MPNETADMINDV.CONSENT_SEQ;

    create sequence MPNETADMINDV.EMAILS_SEQ;

    create sequence MPNETADMINDV.GEOLOCATION_SEQ;

    create sequence MPNETADMINDV.LOSTCANDIDATES_SEQ;

    create sequence MPNETADMINDV.OTHER_CANDIDATE_DETAILS_SEQ;

    create sequence MPNETADMINDV.PHONES_SEQ;

    create sequence MPNETADMINDV.SITES_SEQ;

    create sequence MPNETADMINDV.SKILLS_SEQ;

    create sequence MPNETADMINDV.STEERING_QUESTIONS_SEQ;
