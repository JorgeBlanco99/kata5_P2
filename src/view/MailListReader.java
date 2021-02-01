package view;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Mail;

/**
 *
 * @author jorge
 */
public class MailListReader {
    public static List<Mail> read(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        List<Mail> list = new ArrayList<Mail>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(line);
            if (m.matches()) list.add(new Mail(line));
        }
        return list;
    }

}
