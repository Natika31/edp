package com.projet.edp.fileViewer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileHttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;


	private static final String ADMIN_NAME = "admin";
	private static final String ADMIN_PASSWORD = "admin";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void viewOneFileShouldReturnFileDataDefaultAccessRight() throws Exception {

		assertThat(this.testRestTemplate.withBasicAuth(ADMIN_NAME, ADMIN_PASSWORD).getForObject("http://localhost:" + port + "/api/v1/file?file_id=8",
				String.class)).contains("{\"item_id\":\"8\",\"item_type\":\"file\",\"name\":\"Facture kiné\",\"item_local_path\":\"/home/ori/sante/kine/facture kine.pdf\",\"file_format\":\"pdf\",\"file_origin_path\":\"C:/Users/Natacha/Documents/cnam/GLG204 - 2023/facture kine.pdf\",\"accessRights\":[],\"binary_content\":\"JVBERi0xLjMKJeLjz9MKMSAwIG9iago8PCAKL0NyZWF0b");
	}

	@Test
	public void viewOneFileShouldReturnFileDataNewAccessRight() throws Exception {

		assertThat(this.testRestTemplate.withBasicAuth(ADMIN_NAME, ADMIN_PASSWORD).getForObject("http://localhost:" + port + "/api/v1/file?file_id=1",
				String.class)).contains("{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon ile\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"file_format\":\"pdf\",\"file_origin_path\":\"C:/Users/Natacha/Documents/cnam/GLG204 - 2023/DANS MON ILE.pdf\",\"accessRights\":[{\"id\":{\"recipient_id\":1,\"item_id\":2},\"sendeurId\":1}],\"binary_content\":\"JVBERi0xLjcKCjQgMCBvYmoKKElkZW50aXR5KQplbmRvYmoKNSAwIG9iagooQWRvYmUpCmVuZG9iago4I");

	}

}
