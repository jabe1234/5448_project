package csci.grad.query.builder;

import csci.grad.Operation;

public class InsertQueryBuilder extends QueryBuilder {
    public InsertQueryBuilder(String table) {
        super(Operation.INSERT, table);
    }
}
