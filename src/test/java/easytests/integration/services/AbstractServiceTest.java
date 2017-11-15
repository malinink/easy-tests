package easytests.integration.services;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("checkstyle:multiplestringliterals")
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Transactional
public abstract class AbstractServiceTest {

}
