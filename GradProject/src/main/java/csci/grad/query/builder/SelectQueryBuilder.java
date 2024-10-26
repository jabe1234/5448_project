package csci.grad.query.builder;

import csci.grad.Operation;

public class SelectQueryBuilder extends QueryBuilder {
    public SelectQueryBuilder(String table) {
        super(Operation.SELECT, table);
    }
}
