package com.riscovirtual.marvel;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MarvelInfoService {
	// private static final Logger log =
	Logger log = LogManager.getLogger(MarvelInfoService.class);

	private HashMap<Integer, CharacterInfo> charactersList = new HashMap<Integer, CharacterInfo>();

	private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/characters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCharactersIds() {
		// [ 1009718, 1017100, 1009144, 1010699, 1016823, 1009148 ]
		// String pattern = "[ 1009718, 1017100, 1009144, 1010699, 1016823, 1009148 ]";
		// return pattern;
		try {
			return mapper.writeValueAsString(charactersList.keySet());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/characters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCharacterInfo(@PathVariable Integer id) {
		/*
		 * { "id": 1009718, "name": "Wolverine", "description": "Born with super-human
		 * senses and the power to heal from almost any wound, Wolverine was captured by
		 * a secret Canadian organization and given an unbreakable skeleton and claws.
		 * Treated like an animal, it took years for him to control himself. Now, he's a
		 * premiere member of both the X-Men and the Avengers.", "thumbnail": { "path":
		 * "http://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf", "extension":
		 * "jpg" } }
		 */
		String pattern = "{\r\n" + "		 	\"id\": %s,\r\n" + "			\"name\": \"Wolverine\",\r\n"
				+ "			\"description\": \"Born with super-human senses and the power to heal from almost any\r\n"
				+ "			wound, Wolverine was captured by a secret Canadian organization and given an\r\n"
				+ "			unbreakable skeleton and claws. Treated like an animal, it took years for him to\r\n"
				+ "			control himself. Now, he's a premiere member of both the X-Men and the Avengers.\",\r\n"
				+ "			\"thumbnail\": {\r\n"
				+ "				\"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf\",\r\n"
				+ "				\"extension\": \"jpg\"\r\n" + "			}\r\n" + "		}";
		// return String.format(pattern, id);

		CharacterInfo c = (CharacterInfo) charactersList.get(id);
		try {
			return (mapper.writerWithView(View.Public.class).writeValueAsString(c));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				return (mapper.writerWithView(View.Public.class).writeValueAsString(null));
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
		}
	}

	// Read all info from Marvel and save in class
	private void cacheInfo() {
		log.info("Start of caching.");

		// timestamp just to generate a diferente hash every time it executes
		String ts = null;

		// hash md5(ts+privateKey+publicKey)
		String hash = null;

		JsonNode rootNode = null;
		String url = null;
		JsonNode results = null;
		JsonParser parser = null;
		List<CharacterInfo> listChar = null;

		String urlPattern = "http://gateway.marvel.com/v1/public/characters?ts=%s&apikey=%s&hash=%s&offset=%s&limit=100";

		MarvelInfoSecurity security = new MarvelInfoSecurity(new MarvelInfoConfiguration().getKeysFile());

		// public Marvel API Key
		String publicKey = security.getPublicKey();

		// loop until no more

		URL u = null;

		int i = 0;
		while (i < 20) {

			log.info("i=" + i);

			// get new hash
			ts = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			hash = security.getHash(ts);

			url = String.format(urlPattern, ts, publicKey, hash, (i * 100 + 1));
			log.info("2");

			try {
				u = new URL(url);
				log.info("2.1");
				rootNode = mapper.readTree(u);
				log.info("2.2");

			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Get node that matters to me
			results = rootNode.get("data").get("results");

			// No more results exit loop
			if (results.size() == 0) {
				// System.out.println("Results is null.");
				break;
			}

			parser = mapper.treeAsTokens(results);

			try {
				listChar = parser.readValueAs(new TypeReference<List<CharacterInfo>>() {
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// fill hashMap
			for (CharacterInfo characterInfo : listChar) {
				charactersList.put(characterInfo.getId(), characterInfo);
			}

			i++;
		}

		//
		//
		// testes

		log.info("Cached " + charactersList.keySet().size() + " elements");

		// return of ids
		// Set<Integer> arr = charactersList.keySet();

		/*
		 * try { System.out.println(mapper.writeValueAsString(arr)); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		//
		/*
		 * CharacterInfo c = (CharacterInfo) charactersList.get(1009149); try {
		 * System.out.println(mapper.writerWithView(View.Public.class).
		 * writeValueAsString(c)); } catch (JsonProcessingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 */
	}

	public MarvelInfoService() {
		super();

		// para esconder o objecto wiki quando se escreve o json
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		// init character list
		cacheInfo();
	}

}
