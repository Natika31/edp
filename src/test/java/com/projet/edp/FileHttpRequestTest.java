package com.projet.edp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileHttpRequestTest {


	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void viewOneFileShouldReturnFileData() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file?file_id=1",
				String.class)).contains("\"file_id\":1,\"file_path\":\"/home/\",\"file_name\":");
	}

}
