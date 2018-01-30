package eu.haluzpav.fetests.model;

import java.util.Objects;

public class Entry {

    // TODO expires field

    public String title;
    public String username;
    public String url;
    public String pass;
    public String confPass;
    public String comments;
    public String created;
    public String modified;
    public String accessed;
    public String expires;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(title, entry.title) &&
                Objects.equals(username, entry.username) &&
                Objects.equals(url, entry.url) &&
                Objects.equals(pass, entry.pass) &&
                Objects.equals(confPass, entry.confPass) &&
                Objects.equals(comments, entry.comments) &&
                Objects.equals(created, entry.created) &&
                Objects.equals(modified, entry.modified) &&
                Objects.equals(accessed, entry.accessed);
//                Objects.equals(expires, entry.expires); // not implemented in app
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, username, url, pass, confPass, comments, created, modified, accessed); //, expires);
    }
}
