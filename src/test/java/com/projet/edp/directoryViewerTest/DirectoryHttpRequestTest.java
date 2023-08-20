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
				String.class)).contains("{\"item_id\":\"2\",\"item_local_path\":\"/home/natacha/henri_salavador\",\"name\":\"Henri Salvador\",\"children\":[{\"item_id\":\"1\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"name\":\"Dans mon île\",\"item_type\":\"class com.projet.edp.fileTree.domain.MyFile\",\"children\":[]}]}");
	}
	
	@Test
	public void getNonEmptyDirectoryShouldReturnDirectoryIdLocalPathNameAndChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=4",
				String.class)).contains("{\"item_id\":\"4\",\"item_local_path\":\"/home/natacha/johnny\",\"name\":\"Johnny\",\"children\":[]}");
	}

}
