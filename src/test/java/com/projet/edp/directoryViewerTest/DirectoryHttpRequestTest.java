package com.projet.edp.directoryViewerTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DirectoryHttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getEmptyDirectoryShouldReturnDirectoryIdLocalPathNamAndZeroChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=2",
				String.class)).contains("item_id\":\"1\",\"item_local_path\":\"/home/repertoire_vide\",\"item_name\":\"repertoire vide\",\"childrenDTO\":[]");
	}
	
	@Test
	public void getNonEmptyDirectoryShouldReturnDirectoryIdLocalPathNameAndChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=2",
				String.class)).contains("item_id\":\"2\",\"item_local_path\":\"/home/Henri Salvador/\",\"item_name\":\"Henri Salvador\",\"childrenDTO\":[{\"file_id\":\"1\",\"file_name\":\"Dans mon île\",\"binary_content\":\"JVBERi0xLjcKCjQgMCBvYmoKKElkZW50aXR5KQplbmRvYmoKNSAwIG9iagooQWRvYmUpC");
	}

}
