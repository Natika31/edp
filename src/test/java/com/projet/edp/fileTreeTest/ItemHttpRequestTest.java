package com.projet.edp.fileTreeTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemHttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getEmptyDirectoryShouldReturnItemIdLocalPathNamAndZeroChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree?item_id=1",
				String.class)).contains("{\"item_id\":\"1\",\"item_local_path\":\"/home/Dans mon île.pdf\",\"name\":\"Dans mon île\",\"item_type\":\"file\",\"children\":[]}");
	}

}
