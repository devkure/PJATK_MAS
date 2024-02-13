public class Test extends ObjectPlus{
    private String test;
    private String description;
    private String status;
    private String priority;
    private String testResults;

    public Test(String test, String description, String status, String priority, String testResults) {
        super();
        this.test = test;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.testResults = testResults;
    }
}
