package csci.grad.query.builder;

import csci.grad.Operation;

public class DeleteQueryBuilder extends QueryBuilder {
    public DeleteQueryBuilder(String table) {
        super(Operation.DELETE, table);
    }
}
