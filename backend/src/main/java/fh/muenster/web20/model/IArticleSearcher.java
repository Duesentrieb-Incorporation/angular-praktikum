package fh.muenster.web20.model;

import java.io.IOException;
import java.util.List;

public interface IArticleSearcher {


    /**
     * Gibt alle vorhandenen Artikel zurück
     *
     * @return Liste mit Artikeln
     */
    public List<Article> getArticles();

    /**
     * Liefert eine Liste von Artikeln zurück, die im Inhalt den String {token} enthalten.
     *
     * @param token String, der im Inhalt enthalten sein muss
     * @return Liste von Artikeln, die der Suche entsprechen
     */
    public List<Article> getByContentToken(String token)
    ;

    /**
     * Liefert eine Liste von Artikeln zurück, die in der Überschrift den String {caption} enthalten.
     *
     * @param caption String, der in der Überschrift enthalten sein muss
     * @return Liste von Artikeln, die der Suche entsprechen
     */
    public List<Article> getByCaption(String caption)
    ;

    /**
     * Gibt den Artikel mit der ID {id} zurück.
     *
     * @param id ID des Artikels
     * @return Artikel mit der ID {id}, null falls ID nicht vorhanden
     */
    public Article getById(String id);

    /**
     * Löscht den Artikel mit der ID {id}.
     *
     * @param id ID des zu löschenden Artikels
     * @return True wenn der Artikel gefunden und gelöscht wurde, sonst false
     */
    public Boolean deleteById(String id);

    /**
     * Aktualisiert die Daten des Artikels mit der ID {id}. Es müssen immer Überschrift UND Content übergeben werden.
     *
     * @param a Artikel der aktualisiert werden soll inkl. der aktualisierten Daten
     * @return true wenn der Artikel gespeichert wurde, false falls der Artikel nicht vorhanden ist
     */
    public Boolean updateById(Article a);

    /**
     * Gibt die nächste freie ID für einen Artikel zurück
     *
     * @return ID für den neuen Artikel
     */
    public String getNextId();

    /**
     * Beendet den Sucher.
     *
     * @throws IOException
     */
    public void close() throws IOException;
}
