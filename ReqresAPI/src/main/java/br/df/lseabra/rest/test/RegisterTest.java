package br.df.lseabra.rest.test;

import static io.restassured.RestAssured.given;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Test;
import br.df.lseabra.rest.core.BaseTest;

public class RegisterTest extends BaseTest {

	@Test
	public void Register() {

		Map<String, String> registro = criarElemento("email", "eve.holt@reqres.in", "password", "teste");

		given()
			.body(registro)	
		.when()
			.post("/api/register")
		.then()
			.log().all()
			.statusCode(200)
			.body("id",Matchers.notNullValue());
	}

	@Test
	public void RegisterUnsuccessful() {

		Map<String, String> registro = criarElemento("email", "sydney@fife");

		given()
			.body(registro)
		.when()
			.post("/api/register")
		.then()
			.log().all()
			.statusCode(400)
			.body("error",Matchers.is("Missing password"));
	}

}
