package data;

public class Lessons {
    private String title,content;

    public Lessons(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
