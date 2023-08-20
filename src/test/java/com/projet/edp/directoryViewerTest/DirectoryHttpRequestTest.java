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
				String.class)).contains("{\"item_id\":\"2\",\"item_local_path\":\"/home/dir\",\"name\":\"dir\",\"children\":[]}");
	}
	
	@Test
	public void getNonEmptyDirectoryShouldReturnDirectoryIdLocalPathNameAndChildren() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=4",
				String.class)).contains("\"item_id\":\"4\",\"item_local_path\":\"/home/\",\"name\":\"home\",\"children\":[{\"item_id\":\"2\",\"item_local_path\":\"/home/dir\",\"name\":\"dir\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":[]},{\"item_id\":\"3\",\"item_local_path\":\"/home/dir1\",\"name\":\"dir1\",\"item_type\":\"class com.projet.edp.fileTree.domain.Directory\",\"children\":[{\"item_id\":\"1\",\"item_local_path\":\"/home/Dans mon île.pdf\",\"name\":\"Dans mon île\",\"item_type\":\"class com.projet.edp.fileTree.domain.MyFile\",\"children\":[]}]}]}");
	}

}
