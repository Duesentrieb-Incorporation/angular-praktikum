package fh.muenster.web20.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Article
{
    private String id;

    private String title;

    private String content;

    public Article() {
    }

    public Article(String id) {
        this.id = id;
        this.title = "";
        this.content = "";
    }

    public Article(String id, String caption, String content) {
        this.id = id;
        this.title = caption;
        this.content = content;
    }

    public String getCaption() {
        return title;
    }

    public void setCaption(String caption) {
        this.title = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!id.equals(article.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
