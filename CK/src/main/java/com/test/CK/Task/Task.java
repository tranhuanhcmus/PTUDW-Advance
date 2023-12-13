package com.test.CK.Task;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Short id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private boolean checked;

    public Task(){

    }

    public Task(Short id, String title, String content, boolean checked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.checked = checked;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
