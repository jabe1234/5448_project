package csci.grad;

import csci.grad.query.builder.QueryBuilder;

public class NoSqlQueryBuilderAlgorithm extends QueryBuilderAlgorithm {
    @Override
    public String constructQuery(QueryBuilder queryBuilder) {
        return "";
    }

    /*
    We will need to create json objects from the values given so it can be inserted into MongoDB
     */
    private void createJson(QueryBuilder queryBuilder) {
        return;
    }
}
