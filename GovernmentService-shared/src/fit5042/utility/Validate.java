/*
 * This class provides functions to validate 
 */
package fit5042.utility;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
public class Validate
{

    /**
     * *
     * Validate if the String is empty (null or "")
     *
     * @param str String
     * @return ture if the String is empty
     */
    public static Boolean isEmpty(String str)
    {
        if (str == null) {
            return true;
        }
        if (str.trim().equals("")){
            return true;
        }
        return false;
    }
    
}
