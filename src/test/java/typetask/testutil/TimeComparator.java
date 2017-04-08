package typetask.testutil;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import typetask.logic.parser.DateParser;

//@@ author A0139154E
public class TimeComparator implements Comparator<TestTask> {

    @Override
    public int compare(TestTask o1, TestTask o2) {
        boolean o1HasEndDate = !o1.getEndDate().value.equals("");
        boolean o2HasEndDate = !o2.getEndDate().value.equals("");
        if (o1HasEndDate && o2HasEndDate) {
            List<Date> o1Dates = DateParser.parse(o1.getEndDate().value);
            Date o1Date = o1Dates.get(0);
        
            List<Date> o2Dates = DateParser.parse(o2.getEndDate().value);
            Date o2Date = o2Dates.get(0);
            if (o1Date.before(o2Date)) {
                return -1;
            } else if (o1Date.equals(o2Date)) {
                return 0;
            } else {
                return 1;
            }
        } else if (o1HasEndDate && !o2HasEndDate) {
            return -1;
        } else if (!o1HasEndDate && !o2HasEndDate ) {
            return 0;
        } else {
            return 1;
        }
    }

}