package easytests.support.meanbean;

import java.time.LocalDateTime;
import org.meanbean.lang.Factory;


/**
 * @author malinink
 */
public class LocalDateTimeFactory implements Factory<LocalDateTime> {

    @Override
    public LocalDateTime create()
    {
        return LocalDateTime.now();
    }
}
