package com.projet.edp.contact;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SocialHttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void getRecipientUser1() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/recipient?recipient_id=1",
				String.class)).contains("{\"recipient_id\":\"1\",\"name\":\"toto\",\"item_type\":\"user\",\"members\":[]}");
	}
	
	@Test
	public void getRecipientEmptyGroup() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/recipient?recipient_id=3",
				String.class)).contains("{\"recipient_id\":\"3\",\"name\":\"famille\",\"item_type\":\"group\",\"members\":[]}");
	}
	
	@Test
	public void getRecipientGroupContainsUsers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/recipient?recipient_id=4",
				String.class)).contains("{\"recipient_id\":\"4\",\"name\":\"travail\",\"item_type\":\"group\",\"members\":[{\"recipient_id\":\"1\",\"name\":\"toto\",\"item_type\":\"user\",\"members\":[]},{\"recipient_id\":\"2\",\"name\":\"tata\",\"item_type\":\"user\",\"members\":[]}]}");
	}

	@Test
	public void getUser1AndRootDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/user?user_id=1",
				String.class)).contains("{\"user_id\":\"1\",\"item_type\":\"user\",\"name\":\"toto\",\"mail\":\"toto@me\",\"root\":{\"item_id\":\"7\",\"item_type\":\"folder\",\"name\":\"home\",\"item_local_path\":\"/home\",\"children\":[{\"item_id\":\"5\",\"item_type\":\"folder\",\"name\":\"Natacha\",\"item_local_path\":\"/home/natacha\",\"children\":[{\"item_id\":\"2\",\"item_type\":\"folder\",\"name\":\"Henri Salvador\",\"item_local_path\":\"/home/natacha/henri_salvador\",\"children\":[{\"item_id\":\"1\",\"item_type\":\"file\",\"name\":\"Dans mon ile\",\"item_local_path\":\"/home/Dans_mon_ile.pdf\",\"children\":[]}]},{\"item_id\":\"3\",\"item_type\":\"folder\",\"name\":\"The Beatles\",\"item_local_path\":\"/home/natacha/the_beatles\",\"children\":[]}]},{\"item_id\":\"6\",\"item_type\":\"folder\",\"name\":\"Jack\",\"item_local_path\":\"/home/jack\",\"children\":[{\"item_id\":\"4\",\"item_type\":\"folder\",\"name\":\"Johnny\",\"item_local_path\":\"/home/natacha/johnny\",\"children\":[]}]}]}}");
	}
	
	@Test
	public void getUser2AndRootDirectory() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/user?user_id=2",
				String.class)).contains("{\"user_id\":\"2\",\"item_type\":\"user\",\"name\":\"tata\",\"mail\":\"tata@me\",\"root\":{\"item_id\":\"14\",\"item_type\":\"folder\",\"name\":\"home\",\"item_local_path\":\"/home\",\"children\":[{\"item_id\":\"13\",\"item_type\":\"folder\",\"name\":\"ori\",\"item_local_path\":\"/home/ori\",\"children\":[{\"item_id\":\"12\",\"item_type\":\"folder\",\"name\":\"santé\",\"item_local_path\":\"/home/ori/santé\",\"children\":[{\"item_id\":\"9\",\"item_type\":\"folder\",\"name\":\"kiné\",\"item_local_path\":\"/home/ori/santé/kiné\",\"children\":[{\"item_id\":\"8\",\"item_type\":\"file\",\"name\":\"Facture kiné\",\"item_local_path\":\"/home/ori/sante/kine/facture kine.pdf\",\"children\":[]}]},{\"item_id\":\"10\",\"item_type\":\"folder\",\"name\":\"vision\",\"item_local_path\":\"/home/ori/santé/vision\",\"children\":[]},{\"item_id\":\"11\",\"item_type\":\"folder\",\"name\":\"audition\",\"item_local_path\":\"/home/ori/santé/audition\",\"children\":[]}]}]}]}}");
	}
	
	@Test
	public void getEmptyGroup() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/group?group_id=3",
				String.class)).contains("{\"group_id\":\"3\",\"name\":\"famille\",\"item_type\":\"group\",\"members\":[]}");
	}
	
	@Test
	public void getGroupContainsUsers() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/group?group_id=4",
				String.class)).contains("{\"group_id\":\"4\",\"name\":\"travail\",\"item_type\":\"group\",\"members\":[{\"recipient_id\":\"1\",\"name\":\"toto\",\"item_type\":\"user\",\"members\":[]},{\"recipient_id\":\"2\",\"name\":\"tata\",\"item_type\":\"user\",\"members\":[]}]}");
	}
	
	@Test
	public void getAllGroups() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/groups",
				String.class)).contains("[{\"group_id\":\"3\",\"name\":\"famille\",\"item_type\":\"group\",\"members\":[]},{\"group_id\":\"4\",\"name\":\"travail\",\"item_type\":\"group\",\"members\":[{\"recipient_id\":\"1\",\"name\":\"toto\",\"item_type\":\"user\",\"members\":[]},{\"recipient_id\":\"2\",\"name\":\"tata\",\"item_type\":\"user\",\"members\":[]}]}]");
	}
	
}
