package view;

import java.util.List;
import model.Mail;
import model.Histogram;

/**
 *
 * @author jorge
 */
public class MailHistogramBuilder {
    public static Histogram<String> build(List<Mail> list){
        Histogram<String> histogram = new Histogram();
        for (Mail element : list) histogram.increment(element.getDomain());
        
        return histogram;
    }

}
