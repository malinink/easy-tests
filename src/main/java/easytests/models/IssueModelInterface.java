package easytests.models;

import easytests.entities.IssueEntity;

/**
 * @author fortyways
 */

public interface IssueModelInterface extends ModelInterface {

    void setId(Integer id);

    String getName();

    void setName(String name);

    Integer getAuthorId();

    void setAuthorId(Integer authorId);

    void map(IssueEntity issueEntity);
}
