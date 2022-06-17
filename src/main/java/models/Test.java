package models;

import utils.DateFormatUtility;
import utils.SystemDataUtility;
import utils.database.TableAuthor;
import utils.database.TableProject;
import utils.database.TableSession;

import java.util.Date;
import java.util.Objects;

public class Test {

    private int id;
    private String name;
    private int status_id;
    private String method_name;
    private int project_id;
    private int session_id;
    private String start_time;
    private String end_time;
    private String env;
    private String browser;
    private Integer author_id;

    public Test(int id, String name, int status_id, String method_name, int project_id, int session_id, String start_time, String end_time, String env, String browser, Integer author_id) {
        this.id = id;
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }

    public Test(String name, int status_id, String method_name, Date start_time, Date end_time) {
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = TableProject.getThisProject().getId();
        this.session_id = TableSession.get(start_time).getId();
        this.start_time = DateFormatUtility.getFormatDate(start_time);
        this.end_time = DateFormatUtility.getFormatDate(end_time);
        this.env = SystemDataUtility.getThisEnvName();
        this.author_id = TableAuthor.getThisAuthor().getId();
    }

    public Test(Test copied) {
        this.id = copied.getId();
        this.name = copied.getName();
        this.status_id = copied.getStatus_id();
        this.method_name = copied.getMethod_name();
        this.project_id = copied.getProject_id();
        this.session_id = copied.getSession_id();
        this.start_time = copied.getStart_time();
        this.end_time = copied.getEnd_time();
        this.env = copied.getEnv();
        this.browser = copied.getBrowser();
        this.author_id = copied.getAuthor_id();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = DateFormatUtility.getFormatDate(start_time);
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = DateFormatUtility.getFormatDate(end_time);
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && status_id == test.status_id && project_id == test.project_id && session_id == test.session_id && author_id == test.author_id && Objects.equals(name, test.name) && Objects.equals(method_name, test.method_name) && Objects.equals(start_time, test.start_time) && Objects.equals(end_time, test.end_time) && Objects.equals(env, test.env) && Objects.equals(browser, test.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status_id=" + status_id +
                ", method_name='" + method_name + '\'' +
                ", project_id=" + project_id +
                ", session_id=" + session_id +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", env='" + env + '\'' +
                ", browser='" + browser + '\'' +
                ", author_id=" + author_id +
                '}';
    }
}