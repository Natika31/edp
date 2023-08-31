package com.projet.edp.directoryViewerTest;

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
	public void getUserAndRootDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/user?user_id=1",
				String.class)).contains("{\"user_id\":\"1\",\"name\":\"toto\",\"mail\":\"toto@me\",\"item_type\":\"user\",\"root\":{\"item_id\":\"7\",\"name\":\"home\",\"item_local_path\":\"/home\",\"children\":[{\"item_id\":\"5\",\"name\":\"Natacha\",\"item_local_path\":\"/home/natacha\",\"item_type\":\"folder\",\"children\":[{\"item_id\":\"2\",\"name\":\"Henri Salvador\",\"item_local_path\":\"/home/natacha/henri_salavador\",\"item_type\":\"folder\",\"children\":[{\"item_id\":\"1\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"item_type\":\"file\",\"children\":[]}]},{\"item_id\":\"3\",\"name\":\"The Beatles\",\"item_local_path\":\"/home/natacha/the_beatles\",\"item_type\":\"folder\",\"children\":[]}]},{\"item_id\":\"6\",\"name\":\"Jack\",\"item_local_path\":\"/home/jack\",\"item_type\":\"folder\",\"children\":[{\"item_id\":\"4\",\"name\":\"Johnny\",\"item_local_path\":\"/home/natacha/johnny\",\"item_type\":\"folder\",\"children\":[]}]}]}}");
	}
	
	@Test
	public void getFileTreeItem() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/file-tree?item_id=1",
				String.class)).contains("{\"item_id\":\"1\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"item_type\":\"file\",\"children\":[]}");
	}
	
	@Test
	public void getNonEmptyDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=2",
				String.class)).contains("{\"item_id\":\"2\",\"name\":\"Henri Salvador\",\"item_local_path\":\"/home/natacha/henri_salavador\",\"children\":[{\"item_id\":\"1\",\"name\":\"Dans mon île\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"item_type\":\"class com.projet.edp.fileTree.domain.MyFile\",\"children\":[]}]}");
	}
	
	@Test
	public void getEmptyDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/directory?directory_id=4",
				String.class)).contains("{\"item_id\":\"4\",\"name\":\"Johnny\",\"item_local_path\":\"/home/natacha/johnny\",\"children\":[]}");
	}
}
