package easytests.support.meanbean;

import org.meanbean.lang.Factory;
import java.time.LocalDateTime;


public class LocalDateTimeFactory implements Factory<LocalDateTime> {

    @Override
    public LocalDateTime create()
    {
        return LocalDateTime.now();
    }

}
