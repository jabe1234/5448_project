package csci.grad.query.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertQueryBuilderTest {
    @Test
    public void testBasicInsertQuery() {
        InsertQueryBuilder queryBuilder = new InsertQueryBuilder("table");
        queryBuilder.addValue("column1", "value1");
        queryBuilder.addValue("column2", "value2");
        queryBuilder.addValue("column3", "value3");

        String query = queryBuilder.createQuery();

        assertTrue(query.contains("INSERT INTO"));
        assertTrue(query.contains("VALUES"));
        assertTrue(query.contains("column1") && query.contains("value1"));
        assertTrue(query.contains("column2") && query.contains("value2"));
        assertTrue(query.contains("column3") && query.contains("value3"));
        System.out.println(query);
    }
}
