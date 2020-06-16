package method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetTime {

  public GetTime() {}

  public String getWorkingTime(String start, String end, String breakTime) throws Exception {
    int hour = Integer.parseInt(end.substring(0,2));
    hour = hour - (Integer.parseInt(breakTime)/60);
    SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(hour+":00:00");
    long diffTime = endDate.getTime() - startDate.getTime();
    SimpleDateFormat timeFormatter = new SimpleDateFormat ("HH:mm:ss");
    timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
    String diffTimeStr = timeFormatter.format(new Date(diffTime));
    return diffTimeStr;
  }
}
