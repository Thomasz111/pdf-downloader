import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer thesisId;

    @NotNull
    private String title;
    private Integer citationNo;

    @ManyToMany(mappedBy = "theses")
    private Set<Author> authors = new HashSet<>();

    public Thesis() {
    }

    public Thesis(String title, Integer citationNo, Set<Author> authors) {
        this.title = title;
        this.citationNo = citationNo;
        this.authors = authors;
    }

    public Thesis(String title) {

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCitationNo() {
        return citationNo;
    }

    public void setCitationNo(Integer citationNo) {
        this.citationNo = citationNo;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
