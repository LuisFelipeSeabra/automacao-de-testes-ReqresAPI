package br.df.lseabra.rest.test;

import static io.restassured.RestAssured.given;

import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Test;

import br.df.lseabra.rest.core.BaseTest;


public class UserTest extends BaseTest {
	
	@Test
	public void createUser() {
		
		Map<String, String> cliente = criarElemento("name","luis","job","QA");
		
		given()
			.body(cliente)		
		.when()
			.post("/api/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", Matchers.notNullValue());
	}
	
	@Test
	public void getListUsers() {
				
		given()	
		.when()
			.get("/api/users?page=2")
		.then()
	 		.log().all()
	 		.statusCode(200)
	 		.body("page", Matchers.is(2))
	 		.body("data.$", Matchers.hasSize(6))
	 		.body("data.id", Matchers.hasItems(7,8,9,10,11,12))
	 		.body("data.first_name", Matchers.hasItems("Michael","Lindsay","Tobias","Byron","George","Rachel"));
	}
	
	@Test
	public void getSingleUser() {
				
		given()	
		.when()
			.get("/api/users/2")
		.then()
	 		.log().all()
	 		.statusCode(200)
	 		.body("data.id", Matchers.is(2))
	 		.body("data.email", Matchers.is("janet.weaver@reqres.in"))
	 		.body("data.first_name", Matchers.is("Janet"))
	 		.body("data.last_name", Matchers.is("Weaver"));	
	}
	
	@Test
	public void getSingleUserNotFound() {
				
		given()	
		.when()
			.get("/api/users/50")
		.then()
	 		.log().all()
	 		.statusCode(404);
	}	

	@Test
	public void patchUser() {
		Map<String, String> cliente = criarElemento("name","luis","job","Tester");
		
		given()
			.body(cliente)		
		.when()
			.patch("/api/users/2")
		.then()
			.log().all()
			.statusCode(200)
			.body("updatedAt", Matchers.startsWith(obterData()));
	}
}
