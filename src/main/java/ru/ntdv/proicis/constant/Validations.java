package ru.ntdv.proicis.constant;

public
class Validations {
public static final int LOGIN_SIZE_MIN = 5;
public static final int LOGIN_SIZE_MAX = 32;
public static final String LOGIN_SIZE_MESSAGE =
        "Login must contain at least " + LOGIN_SIZE_MIN + " and no more than " + LOGIN_SIZE_MAX + " characters.";
public static final String LOGIN_PATTERN_REGEXP = "^[\\S]{" + LOGIN_SIZE_MIN + "," + LOGIN_SIZE_MAX + "}$";
public static final String LOGIN_PATTERN_MESSAGE = "Login cannot contain whitespace characters.";
public static final String LOGIN_NOTNULL_MESSAGE = "Login cannot be empty.";

public static final int PASSWORD_SIZE_MIN = 8;
public static final int PASSWORD_SIZE_MAX = 32;
public static final String PASSWORD_SIZE_MESSAGE =
        "Password must contain at least " + PASSWORD_SIZE_MIN + " and no more than " + PASSWORD_SIZE_MAX + " characters.";
public static final String PASSWORD_PATTERN_REGEXP = "^[\\S]{" + PASSWORD_SIZE_MIN + "," + PASSWORD_SIZE_MAX + "}$";
public static final String PASSWORD_PATTERN_MESSAGE = "Password cannot contain whitespace characters.";
public static final String PASSWORD_NOTNULL_MESSAGE = "Password cannot be empty.";

public static final int FIRSTNAME_SIZE_MIN = 1;
public static final int FIRSTNAME_SIZE_MAX = 32;
public static final String FIRSTNAME_SIZE_MESSAGE =
        "First name must contain at least " + FIRSTNAME_SIZE_MIN + " and no more than " + FIRSTNAME_SIZE_MAX + " characters.";
public static final String FIRSTNAME_PATTERN_REGEXP = "^[\\S]{" + FIRSTNAME_SIZE_MIN + "," + FIRSTNAME_SIZE_MAX + "}$";
public static final String FIRSTNAME_PATTERN_MESSAGE = "First name cannot contain whitespace characters.";
public static final String FIRSTNAME_NOTNULL_MESSAGE = "First name cannot be empty.";

public static final int SECONDNAME_SIZE_MIN = 1;
public static final int SECONDNAME_SIZE_MAX = 32;
public static final String SECONDNAME_SIZE_MESSAGE =
        "Second name must contain at least " + SECONDNAME_SIZE_MIN + " and no more than " + SECONDNAME_SIZE_MAX +
        " characters.";
public static final String SECONDNAME_PATTERN_REGEXP = "^[\\S]{" + SECONDNAME_SIZE_MIN + "," + SECONDNAME_SIZE_MAX + "}$";
public static final String SECONDNAME_PATTERN_MESSAGE = "Second name cannot contain whitespace characters.";
public static final String SECONDNAME_NOTNULL_MESSAGE = "Second name cannot be empty.";

public static final int THIRDNAME_SIZE_MIN = 0;
public static final int THIRDNAME_SIZE_MAX = 32;
public static final String THIRDNAME_SIZE_MESSAGE =
        "Third name must contain at least " + THIRDNAME_SIZE_MIN + " and no more than " + THIRDNAME_SIZE_MAX + " characters.";
public static final String THIRDNAME_PATTERN_REGEXP = "^[\\S]{" + THIRDNAME_SIZE_MIN + "," + THIRDNAME_SIZE_MAX + "}$";
public static final String THIRDNAME_PATTERN_MESSAGE = "Third name cannot contain whitespace characters.";
public static final String THIRDNAME_NOTNULL_MESSAGE = "Third name cannot be empty.";

public static final int URLVKONTAKTE_SIZE_MIN = 0;
public static final int URLVKONTAKTE_SIZE_MAX = 120;
public static final String URLVKONTAKTE_SIZE_MESSAGE =
        "VKontakte url must contain at least " + URLVKONTAKTE_SIZE_MIN + " and no more than " + URLVKONTAKTE_SIZE_MAX +
        " characters.";
public static final String URLVKONTAKTE_PATTERN_REGEXP = "^$|((http://|https://)?vk\\.com/|@|(id)).+";
public static final String URLVKONTAKTE_PATTERN_MESSAGE =
        "URL must be in the format: https://vk.com/username or https://vk.com/id123456789 or @username or id123456789";

public static final int URLTELEGRAM_SIZE_MIN = 2;
public static final int URLTELEGRAM_SIZE_MAX = 120;
public static final String URLTELEGRAM_SIZE_MESSAGE =
        "Telegram url must contain at least " + URLVKONTAKTE_SIZE_MIN + " and no more than " + URLVKONTAKTE_SIZE_MAX +
        " characters.";
public static final String URLTELEGRAM_PATTERN_REGEXP = "((?<id>.+)\\.t.me)|((http://|https://)?t\\.me/|@).+";
public static final String URLTELEGRAM_PATTERN_MESSAGE =
        "URL must be in the format: https://t.me/username or https://t.me/id123456789 or @username or id123456789 or username.t.me.";
public static final String URLTELEGRAM_NOTNULL_MESSAGE = "Telegram URL cannot be empty.";

public static final int GROUP_SIZE_MIN = 0;
public static final int GROUP_SIZE_MAX = 9;
public static final String GROUP_SIZE_MESSAGE =
        "Group must contain at least " + GROUP_SIZE_MIN + " and no more than " + GROUP_SIZE_MAX + " characters.";
public static final String GROUP_PATTERN_REGEXP = "^[\\S]{" + GROUP_SIZE_MIN + "," + GROUP_SIZE_MAX + "}$";
public static final String GROUP_PATTERN_MESSAGE = "Group cannot contain whitespace characters.";
public static final String GROUP_NOTNULL_MESSAGE = "Group cannot be empty.";

public static final int ORGANIZATION_SIZE_MIN = 0;
public static final int ORGANIZATION_SIZE_MAX = 120;
public static final String ORGANIZATION_SIZE_MESSAGE =
        "Organization must contain at least " + ORGANIZATION_SIZE_MIN + " and no more than " + ORGANIZATION_SIZE_MAX +
        " characters.";
public static final String ORGANIZATION_NOTNULL_MESSAGE = "Organization cannot be empty.";

public static final String IS_GROUP_OR_ORGANIZATION_EXISTS_MESSAGE = "Group or organization must not be empty.";
}
