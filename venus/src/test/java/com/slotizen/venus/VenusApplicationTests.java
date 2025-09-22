package com.slotizen.venus;

import com.slotizen.venus.config.TestMailConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestMailConfig.class)
class VenusApplicationTests {

	@Test
	void contextLoads() {
	}

}
