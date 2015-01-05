package fh.muenster.web20.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleService implements IArticleSearcher {

    private static ArticleService instance;

    private Set<Article> articles;

    private Integer nextId;

    private ArticleService() {
        this.nextId = 0;
        this.articles = new HashSet<Article>();
        this.addArticle(new Article(String.valueOf(nextId++), "Java", "Java (indonesisch Jawa, nach alter Orthografie Djawa, Aussprache: [dʒawa]) ist eine der vier Großen Sundainseln der Republik Indonesien neben den weiteren Hauptinseln Sumatra, Borneo (Kalimantan) und Sulawesi."));
        this.addArticle(new Article(String.valueOf(nextId++), "Informatik", "Informatik ist die „Wissenschaft der systematischen Verarbeitung von Informationen, insbesondere der automatischen Verarbeitung mit Hilfe von Digitalrechnern“.[1] Historisch hat sich die Informatik einerseits als Formalwissenschaft aus der Mathematik entwickelt, andererseits als Ingenieursdisziplin aus dem praktischen Bedarf nach schnellen und insbesondere automatischen Ausführungen von Berechnungen."));
        this.addArticle(new Article(String.valueOf(nextId++), "Fachhochschule Münster", "Die Fachhochschule Münster mit Sitz im westfälischen Münster und einer weiteren Abteilung in Steinfurt ist mit rund 11754 Studenten[2] in zwölf Fachbereichen und zwei zentralen wissenschaftlichen Einrichtungen eine der größten Fachhochschulen in Deutschland. Die Verwaltung befindet sich im Gebäude der Hüfferstiftung."));
        this.addArticle(new Article(String.valueOf(nextId++), "JAX-RS", "Bei der Java API for RESTful Web Services, kurz JAX-RS, handelt es sich um die Spezifikation einer Programmierschnittstelle (API) der Programmiersprache Java, die die Verwendung des Software-Architekturstils Representational State Transfer (REST) im Rahmen von Webservices ermöglicht und vereinheitlicht.\n" +
                "Die in der Spezifikation beschriebenen Funktionalitäten wurden von einem Unternehmenskonsortium rund um Sun Microsystems sowie weiteren unabhängigen Parteien im Rahmen des Java Community Process erarbeitet und im Java Specification Request 311[1] verabschiedet."));
        this.addArticle(new Article(String.valueOf(nextId++), "WWW", "Das World Wide Web [ˌwɜːldˌwaɪdˈwɛb] (englisch für „weltweites Netz“, kurz Web, WWW, selten und vor allem in der Anfangszeit und den USA auch W3) ist ein über das Internet abrufbares System von elektronischen Hypertext-Dokumenten, sogenannten Webseiten. Sie sind durch Hyperlinks untereinander verknüpft und werden im Internet über die Protokolle HTTP oder HTTPS übertragen. Die Webseiten enthalten meist Texte, oft mit Bildern und grafischen Elementen illustriert. Häufig sind auch Videos, Tondokumente und Musikstücke eingebettet."));
        this.addArticle(new Article(String.valueOf(nextId++), "x86-Prozessor", "x86 ist die Abkürzung einer Mikroprozessor-Architektur und der damit verbundenen Befehlssätze, welche unter Anderem von den Chip-Herstellern Intel und AMD entwickelt werden"));
        this.addArticle(new Article(String.valueOf(nextId++), "Programmiersprache", "Eine Programmiersprache ist eine formale Sprache zur Formulierung von Datenstrukturen und Algorithmen, d. h. von Rechenvorschriften, die von einem Computer ausgeführt werden können.[1] Sie setzen sich aus Anweisungen nach einem vorgegebenen Muster zusammen, der sogenannten Syntax."));
        this.addArticle(new Article(String.valueOf(nextId++), "Makro", "Ein Makro ist in der Softwareentwicklung eine unter einer bestimmten Bezeichnung (Makroname) zusammengefasste Folge von Anweisungen oder Deklarationen, um diese (anstelle der Einzelanweisungen, i. d. R. an mehreren Stellen im Programm) mit nur einem einfachen Aufruf ausführen zu können.[1] Alle Anweisungen des Makros werden automatisch an der Programmstelle ausgeführt, an denen das Makro codiert wurde."));
        this.addArticle(new Article(String.valueOf(nextId++), "Webentwicklung", "Web Engineering ist die Softwareentwicklung von Webanwendungen, Webservices oder anderer komplexer Websites, wie beispielsweise Portalsystemen, Shopping-Seiten. In der Regel ist Web Engineering auch die Fortentwicklung und Erweiterung von vormals erstellten Websites."));
        this.addArticle(new Article(String.valueOf(nextId++), "Bachelor", "Der Bachelor ([ˈbætʃə.lɚ] oder [ˈbætʃlɚ], auch Bakkalaureus oder Baccalaureus [m.] bzw. Bakkalaurea oder Baccalaurea [f.]) ist ein akademischer Grad und hierbei üblicherweise der erste eines gestuften Studiums an einer Hochschule oder eine staatliche Abschlussbezeichnung. In den meisten Fällen hat das Studium zum Bachelor eine Regelstudienzeit von drei bis vier Jahren."));
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    @Override
    public List<Article> getArticles() {
        return new ArrayList<Article>(articles);
    }

    @Override
    public List<Article> getByContentToken(String token) {
        List<Article> result = new ArrayList<Article>();

        for (Article a : articles) {
            if (a.getContent().contains(token)) {
                result.add(a);
            }
        }

        return result;
    }

    @Override
    public List<Article> getByCaption(String caption) {
        List<Article> result = new ArrayList<Article>();

        for (Article a : articles) {
            if (a.getCaption().contains(caption)) {
                result.add(a);
            }
        }

        return result;
    }

    @Override
    public Article getById(String id) {
        Article searched = new Article(id);

        for (Article a : articles) {
            if (a.equals(searched))
                return a;
        }

        return null;
    }

    @Override
    public synchronized Boolean deleteById(String id) {
        return articles.remove(new Article(id));
    }

    @Override
    public Boolean updateById(Article article) {
        for (Article a : articles) {
            if (a.equals(article)) {
                a.setCaption(article.getCaption());
                a.setContent(article.getContent());
                return true;
            }
        }
        return false;
    }

    public synchronized Boolean addArticle(Article a) {
        return articles.add(a);
    }

    @Override
    public synchronized String getNextId() {
        articles.add(new Article(Integer.toString(nextId)));
        return Integer.toString(nextId++);
    }

    @Override
    public void close() throws IOException {
    }
}
