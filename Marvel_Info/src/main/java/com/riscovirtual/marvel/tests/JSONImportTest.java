package com.riscovirtual.marvel.tests;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.riscovirtual.marvel.CharacterInfo;
import com.riscovirtual.marvel.View;
import com.riscovirtual.marvel.View.Public;

public class JSONImportTest {

	String jsonExample = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"© 2019 MARVEL\",\"attributionText\":\"Data provided by Marvel. © 2019 MARVEL\",\"attributionHTML\":\"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2019 MARVEL</a>\",\"etag\":\"52407f427f42bb78d891515ef7fc5ae61e1f2cf7\",\"data\":{\"offset\":0,\"limit\":2,\"total\":1491,\"count\":2,\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"2014-04-29T14:18:17-0400\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784\",\"extension\":\"jpg\"},\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1011334\",\"comics\":{\"available\":12,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1011334/comics\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/21366\",\"name\":\"Avengers: The Initiative (2007) #14\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/24571\",\"name\":\"Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/21546\",\"name\":\"Avengers: The Initiative (2007) #15\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/21741\",\"name\":\"Avengers: The Initiative (2007) #16\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/21975\",\"name\":\"Avengers: The Initiative (2007) #17\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/22299\",\"name\":\"Avengers: The Initiative (2007) #18\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/22300\",\"name\":\"Avengers: The Initiative (2007) #18 (ZOMBIE VARIANT)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/22506\",\"name\":\"Avengers: The Initiative (2007) #19\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/8500\",\"name\":\"Deadpool (1997) #44\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/10223\",\"name\":\"Marvel Premiere (1972) #35\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/10224\",\"name\":\"Marvel Premiere (1972) #36\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/10225\",\"name\":\"Marvel Premiere (1972) #37\"}],\"returned\":12},\"series\":{\"available\":3,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1011334/series\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/1945\",\"name\":\"Avengers: The Initiative (2007 - 2010)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/2005\",\"name\":\"Deadpool (1997 - 2002)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/2045\",\"name\":\"Marvel Premiere (1972 - 1981)\"}],\"returned\":3},\"stories\":{\"available\":21,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1011334/stories\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19947\",\"name\":\"Cover #19947\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19948\",\"name\":\"The 3-D Man!\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19949\",\"name\":\"Cover #19949\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19950\",\"name\":\"The Devil's Music!\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19951\",\"name\":\"Cover #19951\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/19952\",\"name\":\"Code-Name:  The Cold Warrior!\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47184\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #14\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47185\",\"name\":\"Avengers: The Initiative (2007) #14 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47498\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #15\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47499\",\"name\":\"Avengers: The Initiative (2007) #15 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47792\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #16\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/47793\",\"name\":\"Avengers: The Initiative (2007) #16 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/48361\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #17\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/48362\",\"name\":\"Avengers: The Initiative (2007) #17 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/49103\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #18\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/49104\",\"name\":\"Avengers: The Initiative (2007) #18 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/49106\",\"name\":\"Avengers: The Initiative (2007) #18, Zombie Variant - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/49888\",\"name\":\"AVENGERS: THE INITIATIVE (2007) #19\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/49889\",\"name\":\"Avengers: The Initiative (2007) #19 - Int\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/54371\",\"name\":\"Avengers: The Initiative (2007) #14, Spotlight Variant - Int\",\"type\":\"interiorStory\"}],\"returned\":20},\"events\":{\"available\":1,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1011334/events\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/events/269\",\"name\":\"Secret Invasion\"}],\"returned\":1},\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=90c94d87d6e4fcbfed1bbb2874d54b1f\"},{\"type\":\"wiki\",\"url\":\"http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=90c94d87d6e4fcbfed1bbb2874d54b1f\"},{\"type\":\"comiclink\",\"url\":\"http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=90c94d87d6e4fcbfed1bbb2874d54b1f\"}]},{\"id\":1017100,\"name\":\"A-Bomb (HAS)\",\"description\":\"Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction! \",\"modified\":\"2013-09-18T15:54:04-0400\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\",\"extension\":\"jpg\"},\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1017100\",\"comics\":{\"available\":3,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40632\",\"name\":\"Hulk (2008) #53\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40630\",\"name\":\"Hulk (2008) #54\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/40628\",\"name\":\"Hulk (2008) #55\"}],\"returned\":3},\"series\":{\"available\":1,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/series\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/3374\",\"name\":\"Hulk (2008 - 2012)\"}],\"returned\":1},\"stories\":{\"available\":7,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92078\",\"name\":\"Hulk (2008) #55\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92079\",\"name\":\"Interior #92079\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92082\",\"name\":\"Hulk (2008) #54\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92083\",\"name\":\"Interior #92083\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92086\",\"name\":\"Hulk (2008) #53\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/92087\",\"name\":\"Interior #92087\",\"type\":\"interiorStory\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/105929\",\"name\":\"cover from Free Comic Book Day 2013 (Avengers/Hulk) (2013) #1\",\"type\":\"cover\"}],\"returned\":7},\"events\":{\"available\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/characters/1017100/events\",\"items\":[],\"returned\":0},\"urls\":[{\"type\":\"detail\",\"url\":\"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=90c94d87d6e4fcbfed1bbb2874d54b1f\"},{\"type\":\"comiclink\",\"url\":\"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=90c94d87d6e4fcbfed1bbb2874d54b1f\"}]}]}}";

	public JSONImportTest() {
		super();

	}

	public void test1() {

		// dirt hack to get to array
		int beginIndex = jsonExample.indexOf("\"results\":");
		int endIndex = jsonExample.lastIndexOf("]");
		String s = jsonExample.substring(beginIndex + 10, endIndex + 1);

		System.out.println("index of results:" + beginIndex);
		System.out.println("index of ]:" + endIndex);
		// System.out.println(s);

		//
		ObjectMapper objectMapper = new ObjectMapper();

		// HashMap<Integer, CharacterInfo> charactersList = new HashMap<Integer,
		// CharacterInfo>();
		List<CharacterInfo> listChar = null;
		try {
			listChar = objectMapper.readValue(s, new TypeReference<List<CharacterInfo>>() {
			});
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

		for (CharacterInfo characterInfo : listChar) {
			System.out.println(">>" + characterInfo.getName() + " " + characterInfo.getThumbnail().getPath());
		}
	}

	public void test2() {

		// String input = The JSON data from your question
		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootNode = null;
		try {
			rootNode = mapper.readValue(jsonExample.getBytes(), JsonNode.class);
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

		// can also use ArrayNode here, but JsonNode allows us to get(index) line an
		// array:
		JsonNode results = rootNode.get("results");

		System.out.println(results.asText());

		// can also use ObjectNodes here:
		JsonNode oneHit = null;
		JsonNode dataObj = null;

		int idx = 0;
		/*
		 * Data data = null;
		 * 
		 * 
		 * if (hits. != null) { hits = hits.get("hit");
		 * 
		 * if (hits != null) { while ((oneHit = hits.get(idx)) != null) { dataObj =
		 * oneHit.get("data"); System.out.println("Data[" + idx + "]: " + dataObj);
		 * idx++; } } }
		 */
	}

	/*
	 * Funcionou!
	 */
	public void test3() {

		ObjectMapper mapper = new ObjectMapper();
		// para esconder o objecto wiki quando se escreve o json
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(jsonExample.getBytes());
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
		JsonNode results = rootNode.get("data").get("results");

		if (results == null) {
			System.out.println("Results is null.");
		}

		JsonParser parser = mapper.treeAsTokens(results);
		List<CharacterInfo> listChar = null;

		try {
			listChar = parser.readValueAs(new TypeReference<List<CharacterInfo>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fill hashMap
		HashMap<Integer, CharacterInfo> h = new HashMap<Integer, CharacterInfo>();

		for (CharacterInfo characterInfo : listChar) {
			// adicionar a HashMap<characterInfo.getid(), characterInfo>
			h.put(characterInfo.getId(), characterInfo);
			System.out.println(">>" + characterInfo.getName() + " " + characterInfo.getThumbnail().getPath());
		}

		//
		CharacterInfo c = listChar.get(0);
		try {
			System.out.println(mapper.writeValueAsString(c));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return of ids
		Set<Integer> arr = h.keySet();
		try {
			System.out.println(mapper.writeValueAsString(arr));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// id=1011334
		c = (CharacterInfo) h.get(1011334);
		try {
			System.out.println(mapper.writerWithView(View.Public.class).writeValueAsString(c));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test4FromURL() {
		// timestamp just to generate a diferente hash every time it executes
		String ts = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		// public Marvel API Key
		String publicKey = "90c94d87d6e4fcbfed1bbb2874d54b1f"; 
		// hash  md5(ts+privateKey+publicKey) 
		/*
		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(string.getBytes(Charset.forName("UTF8")));
		final byte[] resultByte = messageDigest.digest();
		final String result = new String(Hex.encodeHex(resultByte));
		 */
		String hash = "acbc2c5d689f933040e6d09b3fceb0bd"; 
		// start in 1 and increment until all loaded
		int startIdx = 1;

		String urlPattern = "http://gateway.marvel.com/v1/public/characters?ts=%s&apikey=%s&hash=%s&offset=%s&limit=10";

		//String url = String.format(urlPattern, new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()), apiKey, hash, startIdx);
		String url = String.format(urlPattern, 1, publicKey, hash, startIdx);

		ObjectMapper mapper = new ObjectMapper();
		// para esconder o objecto wiki quando se escreve o json
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		JsonNode rootNode = null;
		try {
			System.out.println("antes readTree");

			rootNode = mapper.readTree(new URL(url));
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
		JsonNode results = rootNode.get("data").get("results");

		if (results.size() == 0) {
			System.out.println("Results is empty.");
		}

		JsonParser parser = mapper.treeAsTokens(results);
		List<CharacterInfo> listChar = null;

		try {
			listChar = parser.readValueAs(new TypeReference<List<CharacterInfo>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fill hashMap
		HashMap<Integer, CharacterInfo> h = new HashMap<Integer, CharacterInfo>();
		for (CharacterInfo characterInfo : listChar) {
			h.put(characterInfo.getId(), characterInfo);
		}

		// return of ids
		Set<Integer> arr = h.keySet();
		try {
			System.out.println(mapper.writeValueAsString(arr));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		CharacterInfo c = (CharacterInfo) h.get(1009149);
		try {
			System.out.println(mapper.writerWithView(View.Public.class).writeValueAsString(c));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Funcionou
	 */
	public void testMD5() {
		
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.reset();
		String original = "1e190e57190b1b74211efd32481b884adca38913890c94d87d6e4fcbfed1bbb2874d54b1f";
		messageDigest.update(original.getBytes());
		byte[] resultByte = messageDigest.digest();
		
		StringBuffer sb = new StringBuffer();
		for (byte b : resultByte) {
			sb.append(String.format("%02x", b & 0xff));
		}

		System.out.println("original:" + original);
		// acbc2c5d689f933040e6d09b3fceb0bd
		System.out.println("digested(hex):" + sb.toString());
		
	}

	public static void main(String[] args) {
		JSONImportTest a = new JSONImportTest();

		a.test4FromURL();
		
		//a.testMD5();
	}

}
