package com.projet.edp.fileTree;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DirectoryViewerHttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getFileTreeItem() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree?item_id=1",
				String.class)).contains("{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}");
	}
	
	@Test
	public void getFileTreeItemByName() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree/name?name=Henri Salvador",
				String.class)).contains("{\"item_id\":\"2\",\"item_type\":\"folder\",\"name\":\"Henri Salvador\",\"item_local_path\":\"/home/natacha/henri_salvador\",\"children\":[{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon ile\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}]}");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree/name?name=Dans mon ile",
		String.class)).contains("{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon ile\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree/name?name=home",
				String.class)).contains("{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}");
	}
	
	@Test
	public void getNonEmptyDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=2",
				String.class)).contains("{\"item_id\":\"2\",\"item_type\":\"folder\",\"name\":\"Henri Salvador\",\"item_local_path\":\"/home/natacha/henri_salvador\",\"children\":[{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}]}");
	}
	
	@Test
	public void getEmptyDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=4",
				String.class)).contains("{\"item_id\":\"4\",\"item_type\":\"folder\",\"name\":\"Johnny\",\"item_local_path\":\"/home/natacha/johnny\",\"children\":[]}");
	}
}
