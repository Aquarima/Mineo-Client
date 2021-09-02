package com.exalow.mineo.models.projects;

import java.sql.Timestamp;
import java.util.List;

public interface Project {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<String> getTags();

    void setTags(List<String> tags);

    boolean isArchived();

    void setArchived(boolean archived);

    Timestamp getCreationTime();
}
