package com.manpower.portal.utility;


/**
 * @author Dimitar Bakardzhiev
 *
 */

/**
 * 
 * This class verifies if a password meets a minimal security requirement and
 * 
 * generates passwords by specifying the requirements.
 * 
 * <br>
 * Example:
 * 
 * <br>
 * 
 * <pre>
 * 
 * boolean somewhatSafe = PasswordTool.verifyPassword(&quot;mP3EG-LaYe|23&quot;,
 * 
 *  PasswordTools.NUMBERS + PasswordTools.EXTRA_CHARS + 8);
 * 
 * 
 * </pre>
 * 
 * The above example would return <code>true</code> because the password
 * 
 * contains numbers, low ASCII characters and is longer than eight bytes.
 * 
 * <br>
 * 
 * Example: <br>
 * 
 * <pre>
 * 
 * boolean somewhatSafe = PasswordTool.verifyPassword(&quot;hB;&quot;, PasswordTools.
 * 
 * MIXED_CASE + PasswordTools.EXTRA_CHARS);
 * 
 * 
 * </pre>
 * 
 * The above would return true because
 * 
 * the password contains both upper case characters
 * 
 * and low ASCII characters. <br>
 * <br>
 * 
 * This class also generates passwords that may be specified in the same way.
 * 
 * <br>
 * Example:
 * 
 * <br>
 * 
 * <pre>
 * 
 * String newPassword = PasswordTool.generatePassword(PasswordTools.NUMBERS +
 * 
 *  6);
 * 
 * 
 * </pre>
 * 
 * The above example would create a new password that consists only of numbers
 * 
 * and is 6 characters long.
 * 
 * 
 * 
 * @version 1.0
 * 
 * @author Dimitar Bakardzhiev
 *  
 */

public final class PasswordTool
{

    /**
     * 
     * No instances needed.
     *  
     */

    private PasswordTool()
    {
    }

    /**
     * 
     * Password must contain alpha characters (a-z).
     *  
     */

    public static final int ALPHA = 1 << 8;

    /**
     * 
     * Password must contain numbers (0-9).
     *  
     */

    public static final int NUMBERS = 1 << 9;
    
    public static final int ONLY_NUMBERS=1<<13;

    /**
     * 
     * Password must have mixed case characters ("jKmN").
     *  
     */

    public static final int MIXED_CASE = 1 << 10 + 1 << 8;

    /**
     * 
     * Password must have ASCII chars from 32 to 47.
     *  
     */

    public static final int EXTRA_CHARS = 1 << 11;

    /**
     * 
     * Generate a new password. If no length is added to the feature list, a
     * 
     * default password length of eight characters is assumed.
     * 
     * 
     * 
     * @param int
     *            features
     * 
     * @return String
     *  
     */

    public static String generatePassword(int features)
    {

        int len = features & 255;

        if (len == 0)
        {

            len = 8;

        }

        if ((features & 0x00) == 0)
        {

            features |= MIXED_CASE;

        }

        byte[] out = new byte[len];

        int i = 0;

        int rnd;

        while (i < len)
        {

            rnd = (int) Math.round(Math.random() * 4);

            switch (rnd)
            {

            case 3:

                if ((features & ALPHA) == ALPHA)
                {

                    out[i++] = createLowerAlpha();

                }

                break;

            case 2:

                if ((features & MIXED_CASE) == MIXED_CASE)
                {

                    out[i++] = createMixedAlpha();

                }

                break;

            case 1:

                if ((features & NUMBERS) == NUMBERS)
                {

                    out[i++] = createNumber();

                }

                break;

            case 0:

                if ((features & EXTRA_CHARS) == EXTRA_CHARS)
                {

                    out[i++] = createExtraChar();

                }

                break;

            }

        }

        return new String(out);

    }

    private static byte createLowerAlpha()
    {

        return (byte) (97 + Math.round(Math.random() * 25));

    }

    private static byte createUpperAlpha()
    {

        return (byte) (65 + Math.round(Math.random() * 25));

    }

    private static byte createMixedAlpha()
    {

        if (Math.round(Math.random()) == 1)
        {

            return createUpperAlpha();

        }

        else
        {

            return createLowerAlpha();

        }

    }

    private static byte createNumber()
    {

        return (byte) (48 + Math.round(Math.random() * 9));

    }

    private static byte createExtraChar()
    {

        return (byte) (33 + Math.round(Math.random() * 14));

    }

    /**
     * 
     * Verify a given password.
     * 
     * 
     * 
     * @param password
     *            the password to verify
     * 
     * @param features
     *            the features the password must have in order to validate
     * 
     * @return the password matches the security requirements
     *  
     */

    public static boolean verifyPassword(String password, int features)
    {

        int len = features & 255;

        if ((password == null) || (len > 0 && password.length() < len))
        {

            return false;

        }

        if ((features & ALPHA) == ALPHA)
        {

            if (!hasAlpha(password))
            {

                return false;

            }

        }

        if ((features & NUMBERS) == NUMBERS)
        {

            if (!hasNumbers(password))
            {

                return false;

            }

        }

        if ((features & ONLY_NUMBERS) == ONLY_NUMBERS)
        {

            if (!hasNumbers(password))
            {

                return false;

            }
            
            if (hasAlpha(password))
            {

                return false;

            }            

        }
        
        if ((features & EXTRA_CHARS) == EXTRA_CHARS)
        {

            if (!hasExtraChars(password))
            {

                return false;

            }

        }

        if ((features & MIXED_CASE) == MIXED_CASE)
        {

            if (!hasMixedAlpha(password))
            {

                return false;

            }

        }

        return true;

    }

    /**
     * 
     * Checks if the password contains
     * 
     * a-z or A-Z.
     * 
     * 
     * 
     * @param password
     *            the password to check
     * 
     * @return boolean
     *  
     */

    private static boolean hasAlpha(String password)
    {

        return (hasUpperAlpha(password)) || (hasLowerAlpha(password));

    }

    /**
     * 
     * Checks if the password contains
     * 
     * a-z and A-Z.
     * 
     * 
     * 
     * @param String
     *            the password
     * 
     * @return boolean
     *  
     */

    private static boolean hasMixedAlpha(String password)
    {

        return (hasUpperAlpha(password)) && (hasLowerAlpha(password));

    }

    /**
     * 
     * Checks if the password contains
     * 
     * A-Z.
     * 
     * 
     * 
     * @param String
     *            the password
     * 
     * @return boolean
     *  
     */

    private static boolean hasUpperAlpha(String password)
    {

        return stringContains(password, 64, 91);

    }

    /**
     * 
     * Checks if the password contains
     * 
     * a-z.
     * 
     * 
     * 
     * @param String
     *            the password
     * 
     * @return boolean
     *  
     */

    private static boolean hasLowerAlpha(String password)
    {

        return stringContains(password, 96, 123);

    }

    /**
     * 
     * Checks if the password contains
     * 
     * 0-9.
     * 
     * 
     * 
     * @param String
     *            the password
     * 
     * @return boolean
     *  
     */

    private static boolean hasNumbers(String password)
    {

        return stringContains(password, 47, 58);

    }

    /**

     * Checks if the password contains the 

     * ASCII characters from 32 to 47.

     * 

     * @param String the password

     * @return boolean

     */

    private static boolean hasExtraChars(String password)
    {

        return stringContains(password, 31, 48);

    }

    /**

     * Checks if a String contains characters

     * within (not including) a given lower and

     * upper limit of the charCode. The method will return true if

     * one character that is between the limits

     * has been found

     * 

     * @param String the string to search through

     * @param int the lower limit

     * @param int the upper limit

     * @return boolean the string contains characters in the limit

     */

    private static boolean stringContains(String str, int lower, int upper)
    {

        int l = str.length();

        for (int i = 0; i < l; i++)
        {

            if ((str.charAt(i) > lower) && (str.charAt(i) < upper))
            {

                return true;

            }

        }

        return false;

    }

}
