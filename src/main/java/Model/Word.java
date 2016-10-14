package Model;

public class Word {


    public String getKeyword() {
        return keyword;
    }

    public Word() {
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String keyword;

    public Word(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Word{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
