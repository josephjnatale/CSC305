import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerMain {
  static private FileHandler fileTxt;
  static private SimpleFormatter formatterTxt;

  static private FileHandler fileHTML;
  static private Formatter formatterHTML;

  static public void setup() throws IOException {

    // get the global logger to configure it
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    //suppress the logging output to the console
    //Logger rootLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    Logger rootLogger = Logger.getLogger("");
    Handler[] handlers = rootLogger.getHandlers();
    if (handlers[0] instanceof ConsoleHandler) {
      rootLogger.removeHandler(handlers[0]);
    }
    
//    
//    private String calcDate(long millisecs) {
//    	
//        SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SS");
//        Date resultdate = new Date(millisecs);
//        return date_format.format(resultdate);
//      }
//    
    logger.setLevel(Level.INFO);
    
    fileTxt = new FileHandler("Logging.txt");
    fileHTML = new FileHandler("Logging.html");

    // create a TXT formatter
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);

    // create an HTML formatter
    formatterHTML = new MyHtmlFormatter();
    fileHTML.setFormatter(formatterHTML);
    logger.addHandler(fileHTML);
  }
}
 
