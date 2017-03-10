package seedu.address.model.person;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import seedu.address.commons.exceptions.IllegalValueException;

public class DateTask {

	public static final String MESSAGE_DATE_CONSTRAINTS = "Task's date should be in this format [ddmmyy] [HH:MM] or dd-MM-yy [HH:MM]";
	public static final String DATE_VALIDATION_REGEX = "\\d+";
	public final Calendar calendar;
	public final String value;
	public final Date date;

	/**
	 * Validates given date.
	 *
	 * @throws IllegalValueException if given date string is invalid.
	 */
	public DateTask(String date_task) throws IllegalValueException {
		if(date_task != null){
			if(date_task.equals("")){
				this.calendar = null;
				this.date = null;
				this.value = "";	
			}
			else{
				String [] date_time = date_task.trim().split("\\s+");
				this.value = mutateToDash(date_time[0])  + " " + date_time[1];
				this.date = mutateToDate(this.value);
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.date);
				this.calendar = cal;
			}
		}
		else {
			this.value = "";
			this.date = null;
			this.calendar = null;
		}
	}
	
    /**
     * Returns true if a given string is a valid task's date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

	private Date mutateToDate(String date_task) throws IllegalValueException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
		try{
			return sdf.parse(date_task);
		}
		catch (ParseException pe){
			throw new IllegalValueException(pe.getMessage());
		}
	}

	private String mutateToDash(String date_task) throws IllegalValueException {
		Date date = null;
		DateFormat scanned = new SimpleDateFormat("ddMMyy");
		DateFormat printed = new SimpleDateFormat("dd-MM-yy");
		SimpleDateFormat saved = new SimpleDateFormat("dd-MM-yy");

		if(date_task.length() == 8){
			try{
				date = saved.parse(date_task);
				if(date_task.equals(saved.format(date))){
					return date_task;
				}
			}
			catch (ParseException e1){
				throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
			}
		}
		else if(date_task.length() == 6){
			try{
				String result = printed.format(scanned.parse(date_task));
				return result;
			}
			catch (ParseException e){
				throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
			}
		}
		else{
			throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
		}
		return date_task;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof DateTask // instanceof handles nulls
						&& this.value.equals(((DateTask) other).value)); // state check
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
