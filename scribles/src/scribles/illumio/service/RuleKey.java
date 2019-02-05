package scribles.illumio.service;

import java.util.Objects;

class RuleKey {
    private final String direction;
    private final String protocol;

    RuleKey(String direction, String protocol) {
        this.direction = direction;
        this.protocol = protocol;
    }

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleKey ruleKey = (RuleKey) o;
        return Objects.equals(direction, ruleKey.direction) &&
                Objects.equals(protocol, ruleKey.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, protocol);
    }
}
