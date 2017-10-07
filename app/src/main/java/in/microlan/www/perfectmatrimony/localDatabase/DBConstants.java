package in.microlan.www.perfectmatrimony.localDatabase;


public interface DBConstants {

    String DB_NAME = "microlan.db";
    int DB_VERSION = 1;

    interface SortType {

        String ASCENDING = " ASC";
        String DESCENDING = " DESC";
    }

    /**
     * Define tables
     */
    interface Tables {

        //tblNotes
        String TBL_NOTES = "tblNotes";
        //tblGoals
        String TBL_GOALS = "tblGoals";
        //tblPlan
        String TBL_PLAN = "tblPlan";
        //tblPlan
        String TBL_USER_INFO = "tblUserInfo";
        //tblGoalNotes
        String TBL_GOAL_NOTES = "tblGoalNotes";

    }

    /**
     * Define Columns Name
     */
    interface Columns {

        String ID = "id"; //authorId
        String CLIENT_ID = "clientId";
        String GOAL_ID = "goalId";
        String GOAL_TITLE = "goalTitle";
        String GOAL_TYPE = "goalType";
        String IS_USER_IN_GOAL_ASSIGNEE = "userInGoalAssignee";
        String IS_USER_IN_GOAL_PARTICIPANTS = "userInGoalParticipants";
        String IS_USER_IN_GOAL_VIEWERS = "userInGoalViewers";
        String IS_USER_IN_PLAN_OWNER_SET = "userInPlanOwnerSet";
        String IS_USER_IN_PLAN_PARTICIPANT_SET = "userInPlanParticipantSet";
        String IS_USER_STREAM_GOAL = "userStreamGoal";

        String AUTHOR_ID = "authorId";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";

        String STATUS = "status";
        String CREATED_DATE = "createdDate";

        String PLAN_ID = "planId";
        String PLAN_TITLE = "planTitle";
        String OWNER_SET = "ownerSet";
        String PARTICIPANT_SET = "participantSet";
        String PROFILE_IMAGE_URL = "profileImageUrl";

        String MESSAGE_BODY = "messageBody";
        String LAST_EVAL_KEY = "lastEvalKey";
        String IS_ARCHIVE = "isArchive";

        String TITLE = "title";
        String GOAL_TYPE_HEADER_ID = "goalTypeHeaderId";
        String ALIGNMENT_GOAL_TYPE = "alignmentGoalType";
        String PERFORMANCE_GOAL_TYPE = "performanceGoalType";
        String DUE_DATE = "dueDate";
        String STATE = "state";
        String GROUP_TITLE = "groupTitle";
        String ROLE_NAME = "roleName";
        String STATE_STATUS = "stateStatus";
        String GOAL_TYPE_ID = "goalTypeId";
        String GROUP_SORT_ID = "groupSortId";

        String PARENT_MESSAGE_ID = "parentMessageId";

        String HIDE_GOAL_FROM_GOAL_LIST = "hideGoalFromGoalList";

        //For Multiple User Login Info Stored
        String USERID = "userID";
        String COMPANYID = "companyID";
        String USERNAME = "userName";
        String USERPASSWORD = "userPassword";
        String FPSTATUS = "fpStatus";
        String LOGGEDIN = "loggedin";

        String FIRSTNAME = "firstName";
        String LASTNAME = "lastName";
        String USERTITLE = "userTitle";
        String AUTHTOKEN = "authToken";
        String REFRESH_TOKEN = "refreshToken";
        String AUTH_KEY = "authKey";

    }

    /**
     * Create Table
     */
    interface CREATE_SQL_TABLE {

        String CREATE_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS "
                + Tables.TBL_NOTES
                + "( " + Columns.ID + " TEXT NOT NULL, "
                + Columns.CLIENT_ID + " TEXT NULL, "
                + Columns.GOAL_ID + " TEXT NULL, "
                + Columns.GOAL_TITLE + " TEXT, "
                + Columns.GOAL_TYPE + " TEXT, "
                + Columns.AUTHOR_ID + " TEXT, "
                + Columns.FIRST_NAME + " TEXT, "
                + Columns.LAST_NAME + " TEXT, "
                + Columns.STATUS + " TEXT, "
                + Columns.CREATED_DATE + " TEXT, "
                + Columns.PLAN_ID + " TEXT, "
                + Columns.PLAN_TITLE + " TEXT, "
                + Columns.PROFILE_IMAGE_URL + " TEXT, "
                + Columns.MESSAGE_BODY + " TEXT, "
                + Columns.LAST_EVAL_KEY + " TEXT, "
                + Columns.IS_ARCHIVE + " INTEGER, "
                + Columns.PARENT_MESSAGE_ID + " TEXT DEFAULT 0, "
                + "PRIMARY KEY(" + Columns.ID + "))";

        String CREATE_GOAL_NOTES_TABLE = "CREATE TABLE IF NOT EXISTS "
                + Tables.TBL_GOAL_NOTES
                + "( " + Columns.ID + " TEXT NOT NULL, "
                + Columns.CLIENT_ID + " TEXT NULL, "
                + Columns.GOAL_ID + " TEXT NULL, "
                + Columns.GOAL_TITLE + " TEXT, "
                + Columns.GOAL_TYPE + " TEXT, "
                + Columns.AUTHOR_ID + " TEXT, "
                + Columns.FIRST_NAME + " TEXT, "
                + Columns.LAST_NAME + " TEXT, "
                + Columns.STATUS + " TEXT, "
                + Columns.CREATED_DATE + " TEXT, "
                + Columns.PLAN_ID + " TEXT, "
                + Columns.PLAN_TITLE + " TEXT, "
                + Columns.PROFILE_IMAGE_URL + " TEXT, "
                + Columns.MESSAGE_BODY + " TEXT, "
                + Columns.LAST_EVAL_KEY + " TEXT, "
                + Columns.IS_ARCHIVE + " INTEGER, "
                + Columns.PARENT_MESSAGE_ID + " TEXT DEFAULT 0, "
                + "PRIMARY KEY(" + Columns.ID + "))";


        String CREATE_GOALS_TABLE = "CREATE TABLE IF NOT EXISTS "
                + Tables.TBL_GOALS
                + "( " + Columns.ID + " TEXT NOT NULL, "
                + Columns.GOAL_TYPE_HEADER_ID + " TEXT NULL, "
                + Columns.ALIGNMENT_GOAL_TYPE + " TEXT NULL, "
                + Columns.PERFORMANCE_GOAL_TYPE + " TEXT NULL, "
                + Columns.TITLE + " TEXT NULL, "
                + Columns.GROUP_TITLE + " TEXT NULL, "
                + Columns.ROLE_NAME + " TEXT NULL, "
                + Columns.STATE + " TEXT NULL, "
                + Columns.STATUS + " TEXT NULL, "
                + Columns.DUE_DATE + " TEXT NULL, "
                + Columns.STATE_STATUS + " TEXT NULL, "
                + Columns.GOAL_TYPE_ID + " TEXT NULL, "
                + Columns.IS_USER_IN_GOAL_ASSIGNEE + " INTEGER DEFAULT 0, "
                + Columns.IS_USER_IN_GOAL_PARTICIPANTS + " INTEGER DEFAULT 0, "
                + Columns.IS_USER_IN_GOAL_VIEWERS + " INTEGER DEFAULT 0, "
                + Columns.GROUP_SORT_ID + " INTEGER, "
                + Columns.HIDE_GOAL_FROM_GOAL_LIST + " INTEGER, "
                + Columns.IS_USER_STREAM_GOAL + " INTEGER, "
                + "PRIMARY KEY(" + Columns.ID + "))";

        String CREATE_PLAN_TABLE = "CREATE TABLE IF NOT EXISTS "
                + Tables.TBL_PLAN
                + "( " + Columns.ID + " TEXT NOT NULL, "
                + Columns.PLAN_ID + " TEXT NULL, "
                + Columns.PLAN_TITLE + " TEXT NULL, "
                + Columns.IS_USER_IN_PLAN_OWNER_SET + " INTEGER DEFAULT 0, "
                + Columns.IS_USER_IN_PLAN_PARTICIPANT_SET + " INTEGER DEFAULT 0, "
                + "PRIMARY KEY(" + Columns.ID + "))";

        // CREATE MULTI USER TABLE
        String CREATE_USERINFO_TABLE = "CREATE TABLE IF NOT EXISTS "
                + Tables.TBL_USER_INFO
                + "( " + Columns.USERID + " TEXT NOT NULL, "
                + Columns.COMPANYID + " TEXT NULL, "
                + Columns.USERNAME + " TEXT NULL, "
                + Columns.USERPASSWORD + " TEXT NULL, "
                + Columns.FPSTATUS + " INTEGER DEFAULT 0, "
                + Columns.LOGGEDIN + " INTEGER DEFAULT 0, "
                + Columns.FIRSTNAME + " TEXT NULL, "
                + Columns.LASTNAME + " TEXT NULL, "
                + Columns.USERTITLE + " TEXT NULL, "
                + Columns.AUTHTOKEN + " TEXT NULL, "
                + Columns.REFRESH_TOKEN + " TEXT NULL, "
                + Columns.AUTH_KEY + " TEXT NULL, "
                + "PRIMARY KEY(" + Columns.USERID + "))";

    }


}
