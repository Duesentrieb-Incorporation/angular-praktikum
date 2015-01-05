package fh.muenster.web20;

import fh.muenster.web20.model.Article;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ArticleResourceTest {

    private HttpServer server;

    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client client = ClientBuilder.newClient();

        target = client.target(Main.BASE_URI);
    }

    /**
     * Testen ob eine Liste mit 10 Artikel zurückgegeben wird bei korrekt Aufruf
     */
    @Test
    public void testGetArticles() {
        Response response = target.path("/articles")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        List<Article> articles = response.readEntity(new GenericType<List<Article>>() {});
        Assert.assertEquals(10, articles.size());
    }

    @Test
    public void testGetId() {
        String id = "0";
        Response response = target.path("/articles").path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

        Article article = response.readEntity(Article.class);
        Assert.assertEquals(id, article.getId());
        Assert.assertEquals("Java", article.getCaption());
        Assert.assertEquals("Java (indonesisch Jawa, nach alter Orthografie Djawa, Aussprache: " +
                "[dʒawa]) ist eine der vier Großen Sundainseln der " +
                "Republik Indonesien neben den weiteren Hauptinseln " +
                "Sumatra, Borneo (Kalimantan) und Sulawesi.", article.getContent());
    }

    @Test
    public void testPut(){
        Response response = target.path("articles").path("new")
                .request(MediaType.APPLICATION_JSON)
                .post(null);

        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Assert.assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());
        Article article = response.readEntity(Article.class);
        Assert.assertEquals("10", article.getId());
        Assert.assertEquals("", article.getCaption());
        Assert.assertEquals("", article.getContent());

        response = target.path("articles").path(article.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(article, MediaType.APPLICATION_JSON));
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCrossOriginHeader() {
        Response response = target.path("articles").request().
                header("Origin", "http://example.com").
                header("Access-Control-Request-Method", "GET").
                get();
        Assert.assertEquals(response.getHeaderString("Access-Control-Allow-Origin"), "http://localhost:8181");
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
