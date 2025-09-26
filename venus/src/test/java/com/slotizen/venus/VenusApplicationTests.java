package com.slotizen.venus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.slotizen.venus.config.TestMailConfig;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestMailConfig.class)
class VenusApplicationTests {

	@Test
	void contextLoads() {
	}

}
