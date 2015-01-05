package fh.muenster.web20.resources;

import fh.muenster.web20.model.Article;
import fh.muenster.web20.model.ArticleService;
import fh.muenster.web20.model.IArticleSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("articles")
public class ArticleResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleResource.class);

    private IArticleSearcher searcher;

    public ArticleResource() {
        searcher = ArticleService.getInstance();
    }

    /**
     * Gibt die Liste mit Artikeln zurück.
     * Die Liste kann über die Query-Parameter caption oder token gefiltert werden.
     * Die Kombination beider Filter ist möglich. Beispiele:
     * <p/>
     * Query-Parameter:
     * caption = {c} - String c muss in der Überschrift enthalten sein
     * token = {t} - String t muss im Inhalt enthalten sein
     * <p/>
     * GET  http://localhost:8080/articles?tokens=wohn
     * GET  http://localhost:8080/articles?caption=A
     *
     * @param caption String der in der Überschrift enthalten sein soll
     * @param token   String der im Inhalt enthalten sein soll
     * @return Response mit der Liste von JSON-ELementen, die dem Filter entspricht.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Article> getArticles(@QueryParam("caption") String caption,
                                     @QueryParam("token") String token) {
        LOGGER.debug(String.format("GET /articles: caption=%s , token=%s", caption, token));

        if (caption != null && token != null) {
            throw new WebApplicationException("Query-Parameter caption und token dürfen nicht beide gesetzt sein!",
                    Response.Status.BAD_REQUEST.getStatusCode());
        } else if (caption != null && token == null) {
            return searcher.getByCaption(caption);
        } else if (caption == null && token != null) {
            return searcher.getByContentToken(token);
        }

        return searcher.getArticles();
    }

    /**
     * Gibt den Artikel mit der ID {id} zurück.
     * Beispiel:
     * <p/>
     * GET  http://localhost:8080/articles/wir_sind_helden
     *
     * @param id ID des Artikels
     * @return Response mit dem Artikel der übergebenen ID
     */
    @GET
    @Path("{id: [0-9]+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getArticle(@PathParam("id") String id) {
        LOGGER.debug("GET /articles/{id}");

        Article article = searcher.getById(id);

        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok()
                .entity(article)
                .build();
    }

    /**
     * Löscht den Artikel mit der ID {id}.
     * <p/>
     * Beispiel:
     * DELETE   http://localhost:8080/articles/wir_sind_helden
     *
     * @param id ID des Artikels
     * @return Response, die besagt ob der Request erfolgreich war
     */
    @DELETE
    @Path("{id: [0-9]+}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteArticle(@PathParam("id") String id) {
        LOGGER.debug("DELETE /articles/{id}");

        if (searcher.deleteById(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    /**
     * Aktualisiert den Artikel mit der ID {id}.
     * Beispiel:
     * <p/>
     * PUT   http://localhost:8080/articles/wir_sind_helden
     *
     * @param id      ID des Artikels
     * @param article Enthält den zu speichernden Artikel
     * @return Response, die besagt ob der Request erfolgreich war
     */
    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateArticle(@PathParam("id") String id,
                                  Article article) {
        LOGGER.debug("PUT /articles/{id}");

        article.setId(id);
        if (searcher.updateById(article)) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    /**
     * Legt einen neuen leeren Artikel mit der ID {newid}  an
     * <p/>
     * Beispiel:
     * POST  http://localhost:8080/articles
     *
     * @return Response, die besagt ob der Request erfolgreich war
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createArticle() {
        LOGGER.debug("POST /articles");

        String nextId = searcher.getNextId();

        return Response.status(Response.Status.CREATED)
                .entity(new Article(nextId))
                .build();
    }
}
