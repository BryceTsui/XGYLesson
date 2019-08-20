package nju.vo.bbs;

/**
 * Created by thinkpad on 2019/3/14.
 */
public class TopicVO {

    int id;
    String author_id;
    String tab;
    String content;
    String title;
    String last_reply_at;

    String avator;

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLast_reply_at() {
        return last_reply_at;
    }

    public void setLast_reply_at(String last_reply_at) {
        this.last_reply_at = last_reply_at;
    }
}
