package com.sigopt.model;

public class MockResource extends StructObject {
    public MockResource() {
        super();
    }

    public MockResource(String id, String superAwesomeName) {
        super();
        this.set("id", id);
        this.set("super_awesome_name", superAwesomeName);
    }

    public String getId() {
        return (String) this.get("id");
    }

    public String getSuperAwesomeName() {
        return (String) this.get("super_awesome_name");
    }
}
