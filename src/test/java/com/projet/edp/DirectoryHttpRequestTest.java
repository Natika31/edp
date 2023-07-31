package com.projet.edp;

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
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=1",
				String.class)).contains("directory_id\":1,\"directory_local_path\":\"/home/\",\"directory_name\":\"Henri Salvador\",\"children\":[]");
	}
	
	@Test
	public void getNonEmptyDirectoryShouldReturnDirectoryIdLocalPathNameAndChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=2",
				String.class)).contains("directory_id\":2,\"directory_local_path\":\"/home/\",\"directory_name\":\"Henri Salvador\",\"children\":[{\"file_id\":\"1\",\"file_name\":\"Dans mon île\",\"binary_content\":\"JVBERi0xLjcKCjQgMCBvYmoKKElkZW50aXR5KQplbmRvYmoKNSAwIG9iagooQWRvYmUpC");
	}

}
